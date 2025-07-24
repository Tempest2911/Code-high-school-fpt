package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.HashSet;
import java.util.Set;

public class DeepSeekScheduler {

    private static final String DB_URL = "jdbc:sqlserver://Tempest:1433;databasename=Coach_Me_AI;trustServerCertificate=true;user=sa;password=123456;charset=UTF-8";
    private static final String MODEL = "mistral:7b";
    private static final String API_URL = "http://localhost:11434/api/generate";
    private static final int MAX_RETRIES = 3;
    private static final int TIMEOUT_SECONDS = 30;
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS)
            .build();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Logger logger = Logger.getLogger(DeepSeekScheduler.class.getName());
    
    public static void main(String[] args) {
        logger.info("Application started");
        System.out.println("Hello");

        System.setProperty("file.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        try (Connection testConn = DriverManager.getConnection(DB_URL)) {
            logger.info("Connected to the database successfully!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection error: " + e.getMessage(), e);
            return;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scanner.close();
            System.out.println("\nShutting down the application safely...");
        }));

        while (true) {
            try {
                System.out.print("You: ");
                String prompt = scanner.nextLine().trim();
                if (prompt.isEmpty()) {
                    System.out.println("\u274C Please enter content!");
                    continue;
                }

                if (prompt.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting chatbot...");
                    break;
                }

                String response = getChatbotResponse(prompt);
                if (response != null && !response.isEmpty()) {
                    System.out.println("Bot: " + response);
                    
                    // Only create a schedule if the prompt contains "create schedule"
                    if (prompt.toLowerCase().contains("create schedule")) {
                        insertScheduleTask(prompt, response);
                        displaySchedule();
                    }
                } else {
                    System.out.println("Bot: I didn't understand that. Please specify 'create schedule' to create a schedule.");
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error: " + e.getMessage(), e);
            }
        }
    }

    private static String getChatbotResponse(String prompt) {
        int retryCount = 0;
        while (retryCount < MAX_RETRIES) {
            try {
                JSONObject requestJson = new JSONObject()
                        .put("model", MODEL)
                        .put("prompt",
                                "You are an AI assistant helping students create study schedules. Please create a schedule for the requested subject.\n" +
                                        "Respond in the exact format as follows (keep the keywords intact):\n\n" +
                                        "Title: [What to study]\n" +
                                        "Content: [Details of the study session]\n" +
                                        "Notes: [Additional information; do not include the teacher or school supplies]\n" +
                                        "Start Time: [Specify start time; use 'yyyy-MM-dd HH:mm']\n" +
                                        "End Time: [Specify end time; use 'yyyy-MM-dd HH:mm']\n\n" +
                                        "Request: " + prompt)
                        .put("stream", false)
                        .put("max_tokens", 300);

                RequestBody body = RequestBody.create(requestJson.toString(), JSON);
                Request request = new Request.Builder()
                        .url(API_URL)
                        .post(body)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful() || response.body() == null) {
                        throw new IOException("\u274C API Error: " + response.code());
                    }

                    String responseBody = response.body().string();
                    JSONObject responseJson = new JSONObject(responseBody);
                    String aiResponse = responseJson.optString("response", "");

                    int startIndex = aiResponse.indexOf("Bot:");
                    if (startIndex != -1) {
                        aiResponse = aiResponse.substring(startIndex + "Bot:".length()).trim();
                    }

                    if (!aiResponse.contains("Title:") || !aiResponse.contains("Content:") || 
                            !aiResponse.contains("Notes:") || !aiResponse.contains("Start Time:") || 
                            !aiResponse.contains("End Time:")) {
                        throw new IOException("Response format is incorrect");
                    }

                    insertScheduleTask(prompt, aiResponse);
                    return aiResponse;
                }
            } catch (Exception e) {
                retryCount++;
                logger.log(Level.WARNING, "API error, retry " + retryCount + "/" + MAX_RETRIES + ": " + e.getMessage(),
                        e);
                if (retryCount < MAX_RETRIES) {
                    System.out.println(
                            "\u274C API Error, retrying " + retryCount + "/" + MAX_RETRIES + ": " + e.getMessage());
                    try {
                        Thread.sleep(1000L * retryCount);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                } else {
                    System.out.println("\u274C API Error after " + MAX_RETRIES + " attempts: " + e.getMessage());
                    return null;
                }
            }
        }
        return null;
    }

    private static void insertScheduleTask(String prompt, String response) {
        if (response == null || response.trim().isEmpty()) {
            System.out.println("\u274C Cannot create a schedule with empty content!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);

            try {
                String tieuDe = extractField(response, "Title:");
                String noiDung = extractField(response, "Content:");
                String ghiChu = extractField(response, "Notes:");
                String startTimeString = extractField(response, "Start Time:");
                String endTimeString = extractField(response, "End Time:");
                
                // Parse the strings to Timestamps
                Timestamp thoiGianBatDau = parseTimestamp(startTimeString);
                Timestamp thoiGianKetThuc = parseTimestamp(endTimeString);

                // Validate parsed timestamps
                if (thoiGianBatDau == null || thoiGianKetThuc == null) {
                    System.out.println("\u274C Invalid start or end time format. Please use 'yyyy-MM-dd HH:mm'.");
                    return; // Exit the method if timestamps are invalid
                }

                // Debugging output
                System.out.println("Start Time: " + thoiGianBatDau);
                System.out.println("End Time: " + thoiGianKetThuc);

                String trangThai = "Pending"; // Default status, can be modified as needed

                // Update the SQL insert statement
                String sqlInsert = "INSERT INTO Nhiem_Vu (ID_Tai_Khoan, ID_Mon_Hoc, Tieu_De, Noi_Dung, Trang_Thai, Thoi_Gian_Bat_Dau, Thoi_Gian_Ket_Thuc) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                    stmt.setInt(1, 1); // Default account ID
                    stmt.setInt(2, getMonHocId(conn, prompt)); // Get the subject ID based on the prompt
                    stmt.setString(3, tieuDe);
                    stmt.setString(4, noiDung);
                    stmt.setString(5, trangThai);
                    stmt.setTimestamp(6, thoiGianBatDau); // Set start time
                    stmt.setTimestamp(7, thoiGianKetThuc); // Set end time

                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        conn.commit();
                        System.out.println("\u2705 Schedule created successfully!");
                    } else {
                        throw new SQLException("Cannot create schedule!");
                    }
                }
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("{1F6A8} Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("{1F6A8} Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to get the subject ID based on the prompt
    private static int getMonHocId(Connection conn, String prompt) throws SQLException {
        // Implement logic to extract the subject ID based on the prompt
        // This is a placeholder; you should implement the actual logic
        return 1; // Replace with actual logic to get the subject ID
    }

    private static String extractField(String response, String fieldName) {
        try {
            response = response.replaceAll("^[\\d,\\s]+", "");
            Pattern pattern = Pattern.compile(fieldName + "\\s*(.+?)(?=\\n|$)");
            Matcher matcher = pattern.matcher(response);
            if (matcher.find()) {
                String value = matcher.group(1).trim();
                value = value.replaceAll("[^\\p{L}\\p{N}\\s.,:-]", "");
                return value;
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    private static Timestamp parseTimestamp(String timeString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date parsedDate = format.parse(timeString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null; // Handle this case as needed
        }
    }

    private static void displaySchedule() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT nv.Tieu_De, nv.Noi_Dung, mh.Ten, nv.Thoi_Gian_Bat_Dau, nv.Thoi_Gian_Ket_Thuc " +
                    "FROM Nhiem_Vu nv " +
                    "JOIN Mon_Hoc mh ON nv.ID_Mon_Hoc = mh.ID_Mon_Hoc " +
                    "WHERE nv.ID_Tai_Khoan = 1 " +
                    "ORDER BY nv.Tieu_De DESC";

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {

                boolean hasRecords = false;
                System.out.println("\n=== SCHEDULE IN THE DATABASE ===");

                // Use a Set to track printed entries
                Set<String> printedEntries = new HashSet<>();

                while (rs.next()) {
                    String entry = "Subject: " + rs.getString("Ten") +
                            "\nTitle: " + rs.getString("Tieu_De") +
                            "\nContent: " + rs.getString("Noi_Dung") +
                            "\nStart Time: " + rs.getString("Thoi_Gian_Bat_Dau") +
                            "\nEnd Time: " + rs.getString("Thoi_Gian_Ket_Thuc") +
                            "\n----------------------------------------";

                    // Check if this entry has already been printed
                    if (!printedEntries.contains(entry)) {
                        printedEntries.add(entry);
                        hasRecords = true;
                        System.out.println(entry);
                    }
                }

                if (!hasRecords) {
                    System.out.println("No schedules have been created yet!");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error displaying schedule: " + e.getMessage(), e);
            System.out.println("{1F6A8} Error displaying schedule: " + e.getMessage());
        }
    }
}