
package com.mycompany.coachmeai;

import javax.swing.*;
import java.awt.*;

import java.awt.geom.Ellipse2D;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import javax.swing.text.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.awt.image.BufferedImage;

import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.*;
import java.util.HashSet;
import java.util.Set;

public class ChatTool extends JFrame {
    private int idTaiKhoan;
    private int idMonHoc;
    private String tieuDe;
    private String noiDung;
    private String trangThai;
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;

    private static final String DB_URL = "jdbc:sqlserver://Tempest:1433;databasename=Coach_Me_AI;trustServerCertificate=true;user=sa;password=123456;charset=UTF-8";
    private static final String MODEL = "mistral:7b";
    private static final String API_URL = "http://localhost:11434/api/generate";
    private static final int MAX_RETRIES = 3;
    private static final int TIMEOUT_SECONDS = 30;
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS).build();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Logger logger = Logger.getLogger(ChatTool.class.getName());

    String avatarPath = "D:\\CODE\\DuAnTotNghiep\\CoachMeAI\\src\\main\\java\\com\\mycompany\\Images\\1.png";

    String defaultUserAvatar = "D:\\meme\\DreamyBull Vest.jpg";
    private JTextPane chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private Map<String, Boolean> userFirstMessage = new HashMap<>();

    public ChatTool() {
        setTitle("Chat Tool");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        // 💬 Khu vực chat
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setContentType("text/html");
        chatArea.setBackground(new Color(40, 44, 52)); // Màu nền tối
        chatArea.setForeground(Color.WHITE);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Tạo khoảng cách đẹp hơn
        add(scrollPane, BorderLayout.CENTER);

        // ✏️ Khu vực nhập liệu
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(30, 33, 40)); // Màu nền tối hơn

        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inputField.setBackground(new Color(50, 54, 60));
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        inputField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // Bo góc đẹp hơn

        // 🔘 Nút gửi
        sendButton = new JButton("➤");
        sendButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        sendButton.setBackground(new Color(70, 130, 180));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));



        // Hiệu ứng hover cho nút gửi
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendButton.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendButton.setBackground(new Color(70, 130, 180));
            }
        });

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // 🎯 Xử lý sự kiện gửi tin nhắn
        sendButton.addActionListener(e -> sendMessageToAI());
        inputField.addActionListener(e -> sendMessageToAI());

        // 🖼️ Lời chào đầu tiên của AI với avatar
        String avatarPath = "D:/CODE/DuAnTotNghiep/CoachMeAI/src/main/java/com/mycompany/Images/1.png";
        appendMessage("", "Xin chào !👋🏿 Hôm nay tôi có thể giúp gì nào? 😀", Color.LIGHT_GRAY, avatarPath);


        setVisible(true);
    }

    public void appendMessage(String sender, String message, Color color, String avatarUrl) {
        StyledDocument doc = chatArea.getStyledDocument();

        // Tạo panel chứa avatar + tin nhắn
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        messagePanel.setOpaque(false);

        try {
            System.out.println("Avatar URL: " + avatarUrl);

            // Load ảnh avatar
            ImageIcon avatar = new ImageIcon(avatarUrl);
            if (avatar.getIconWidth() == -1) {
                System.out.println("⚠️ Lỗi: Không thể tải ảnh từ " + avatarUrl);
                return;
            }

            // Resize avatar
            Image image = avatar.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon resizedAvatar = new ImageIcon(image);
            JLabel avatarLabel = new JLabel(resizedAvatar);

            // Tạo label cho tin nhắn
            JLabel textLabel = new JLabel("<html><div style='width: 250px; color: " + toHex(color) + ";'>" + sender
                    + " " + message + "</div></html>");
            textLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            // Thêm avatar và tin nhắn vào panel
            messagePanel.add(avatarLabel);
            messagePanel.add(textLabel);

            // Thêm vào JTextPane
            chatArea.setCaretPosition(doc.getLength());
            chatArea.insertComponent(messagePanel);
            doc.insertString(doc.getLength(), "\n", null);

            // Refresh lại giao diện
            chatArea.revalidate();
            chatArea.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm chuyển Color thành mã hex để set màu trong HTML
    private String toHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private void sendMessageToAI() {
        String userMessage = inputField.getText().trim();

        if (userMessage.isEmpty()) {
            return;
        }

        appendMessage("", userMessage, Color.GREEN, defaultUserAvatar); // Hiển thị tin nhắn người dùng
        inputField.setText("");

        // Kiểm tra nếu người dùng yêu cầu danh sách ngày trong tương lai
        if (userMessage.toLowerCase().contains("ngày trong tương lai")) {
            insertFutureDaysToDatabase(10); // Ví dụ: Thêm 10 ngày trong tương lai vào DB
            return;
        }

        // Gửi tin nhắn đến AI
        new Thread(() -> {
            final String aiResponse = getAIResponse(userMessage).replaceAll("(?i)<think>.*?</think>", "").trim();
            String aiResponses = aiResponse.replaceAll("(?i)<think>[^<]*</think>", "").trim();
            System.out.println(aiResponse);
            SwingUtilities.invokeLater(() -> appendMessage("", aiResponses, Color.LIGHT_GRAY, avatarPath));
        }).start();
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
                String trangThai = "Pending"; // Mặc định
                Timestamp thoiGianBatDau = parseTimestamp(extractField(response, "Start Time:"));
                Timestamp thoiGianKetThuc = parseTimestamp(extractField(response, "End Time:"));

                if (thoiGianBatDau == null || thoiGianKetThuc == null) {
                    System.out.println("\u274C Invalid start or end time format. Please use 'yyyy-MM-dd HH:mm'.");
                    return;
                }


                insertToDatabase(conn, 1, getMonHocId(conn, prompt), tieuDe, noiDung, trangThai, thoiGianBatDau, thoiGianKetThuc);
                conn.commit();
                System.out.println("\u2705 Schedule created successfully!");

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertToDatabase(Connection conn, int idTaiKhoan, int idMonHoc, String tieuDe, String noiDung, String trangThai, Timestamp thoiGianBatDau, Timestamp thoiGianKetThuc) throws SQLException {

        String sqlInsert = "INSERT INTO Nhiem_Vu (ID_Tai_Khoan, ID_Mon_Hoc, Tieu_De, Noi_Dung, Trang_Thai, Thoi_Gian_Bat_Dau, Thoi_Gian_Ket_Thuc) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
            stmt.setInt(1, idTaiKhoan);
            stmt.setInt(2, idMonHoc);
            stmt.setString(3, tieuDe);
            stmt.setString(4, noiDung);
            stmt.setString(5, trangThai);

            // Định dạng lại Timestamp
            stmt.setString(6, formatTimestamp(thoiGianBatDau));
            stmt.setString(7, formatTimestamp(thoiGianKetThuc));

            stmt.executeUpdate();
        }
    }

    private static String formatTimestamp(Timestamp timestamp) {
        // Định dạng Timestamp thành chuỗi yyyy-MM-dd HH:mm
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(timestamp);
    }

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

    private String getAIResponse(String prompt) {
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
                                        "Start Time: Get Time from Future [Specify start time; use '2025-MM-dd HH:mm']\n" +
                                        "End Time: Get Time from Future [Specify end time; use '2025-MM-dd HH:mm']\n\n" +
                                        "Request: " +
                                        "Do you want take this schedule?" + prompt)
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

                    // Check for missing fields
                    boolean isValid = aiResponse.contains("Title:") &&
                            aiResponse.contains("Content:") &&
                            aiResponse.contains("Notes:") &&
                            aiResponse.contains("Start Time:") &&
                            aiResponse.contains("End Time:");

                    if (!isValid) {
                        logger.log(Level.WARNING, "Incomplete response from the AI: " + aiResponse);
                        // Provide a default fallback response or description
                        aiResponse += "\n\n[Error: Some fields are missing. Please try again or improve the prompt formatting.]";
                    }

                    insertScheduleTask(prompt, aiResponse); // Save response regardless of validity for debugging
                    return aiResponse;
                }
            } catch (Exception e) {
                retryCount++;
                logger.log(Level.WARNING, "API error, retry " + retryCount + "/" + MAX_RETRIES + ": " + e.getMessage(), e);

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
                    return "\n[Error: Failed to get AI response after retries. Please check the logs.]";
                }
            }
        }
        return null; // Returns null if no retries work
    }

    private void insertFutureDaysToDatabase(int soNgay) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);

            try {
                LocalDateTime homNay = LocalDateTime.now(); // Lấy thời gian hiện tại
                for (int i = 1; i <= soNgay; i++) {
                    LocalDateTime ngayTuongLai = homNay.plusDays(i); // Tính toán ngày trong tương lai
                    Timestamp thoiGianBatDau = Timestamp.valueOf(ngayTuongLai);
                    Timestamp thoiGianKetThuc = Timestamp.valueOf(ngayTuongLai.plusHours(1)); // Ví dụ: kết thúc sau 1 giờ

                    // Gọi hàm chèn
                    insertToDatabase(conn, idTaiKhoan, idMonHoc,
                            "Tự động tạo lịch",
                            "Tự động tạo lịch ngày: " + ngayTuongLai.toLocalDate(),
                            "Pending", thoiGianBatDau, thoiGianKetThuc);
                }

                conn.commit();
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                        soNgay + " ngày trong tương lai đã được thêm vào cơ sở dữ liệu!"));

            } catch (SQLException e) {
                conn.rollback();
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                        "Lỗi khi thêm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE));
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatTool::new);
    }
}