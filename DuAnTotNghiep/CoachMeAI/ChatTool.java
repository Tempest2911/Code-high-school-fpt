package com.mycompany.coachmeai;

import com.mycompany.DAO.DatabaseManager;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;    
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import java.sql.Timestamp;

public class ChatTool extends JFrame {

    private JTextPane chatArea;
    private JTextField inputField;
    private JButton sendButton;

    public ChatTool() {
        setTitle("Chat Tool");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Gửi");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessageToAI());
        inputField.addActionListener(e -> sendMessageToAI());

        setVisible(true);
    }

    private void sendMessageToAI() {
        String userMessage = inputField.getText().trim();
        if (userMessage.isEmpty()) {
            return;
        }

        appendMessage("Bạn", userMessage, Color.GREEN);
        inputField.setText("");

        new Thread(() -> {
            String aiResponse = processScheduleRequest(userMessage);
            JSONObject taskData = new JSONObject(aiResponse);
            SwingUtilities.invokeLater(() -> showConfirmationDialog(taskData));
        }).start();
    }

    private String processScheduleRequest(String prompt) {
        String regex = ".*schedule (?:a )?(class|lesson|session) (.+) from (\\d{1,2} ?[APap][Mm]) to (\\d{1,2} ?[APap][Mm])(?: on (\\d{1,2}/\\d{1,2}/\\d{4}))? with content ['\"]([^'\"]+)['\"](?: and note ['\"]([^'\"]+)['\"])?";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(prompt);

        if (matcher.matches()) {
            String subjectName = matcher.group(2).trim();
            subjectName = validateSubjectName(subjectName);

            String startTimeRaw = matcher.group(3).trim();
            String endTimeRaw = matcher.group(4).trim();
            String dateRaw = matcher.group(5);
            String content = matcher.group(6).trim();
            String note = matcher.group(7) != null ? matcher.group(7).trim() : "No notes";

            LocalDate date = parseDate(dateRaw);
            LocalTime startTime = parseTime(startTimeRaw);
            LocalTime endTime = parseTime(endTimeRaw);

            LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(date, endTime);

            DateTimeFormatter sqlFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String startDateTimeSQL = startDateTime.format(sqlFormatter);
            String endDateTimeSQL = endDateTime.format(sqlFormatter);

            JSONObject scheduleJson = new JSONObject();
            scheduleJson.put("title", "Class Schedule: " + subjectName);
            scheduleJson.put("subject_name", subjectName);
            scheduleJson.put("content", content);
            scheduleJson.put("note", note);
            scheduleJson.put("start_time", startDateTimeSQL);
            scheduleJson.put("end_time", endDateTimeSQL);

            return scheduleJson.toString(4);
        } else {
            return "❌ Unable to recognize the request. Please use a valid format.";
        }
    }

    private String validateSubjectName(String subjectName) {
        if (subjectName.equalsIgnoreCase("toán") || subjectName.equalsIgnoreCase("văn") || subjectName.equalsIgnoreCase("anh")) {
            return subjectName;
        } else {
            return "Khác";
        }
    }

    private LocalDate parseDate(String dateRaw) {
        return (dateRaw != null)
                ? LocalDate.parse(dateRaw, DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH))
                : LocalDate.now();
    }

    private LocalTime parseTime(String timeRaw) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h a", Locale.ENGLISH);
        return LocalTime.parse(timeRaw.toUpperCase(), timeFormatter);
    }

    private void showConfirmationDialog(JSONObject taskData) {
        JDialog dialog = new JDialog(this, "Xác nhận thông tin", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(7, 2));

        JLabel lblTitle = new JLabel("Tiêu đề:");
        JTextField txtTitle = new JTextField(taskData.getString("title"));

        JLabel lblSubject = new JLabel("Môn học:");
        JTextField txtSubject = new JTextField(taskData.getString("subject_name"));
        txtSubject.setEditable(false);

        JLabel lblContent = new JLabel("Nội dung:");
        JTextField txtContent = new JTextField(taskData.getString("content"));

        JLabel lblNote = new JLabel("Ghi chú:");
        JTextField txtNote = new JTextField(taskData.optString("note", ""));

        JLabel lblStartTime = new JLabel("Bắt đầu:");
        JTextField txtStartTime = new JTextField(taskData.getString("start_time"));

        JLabel lblEndTime = new JLabel("Kết thúc:");
        JTextField txtEndTime = new JTextField(taskData.getString("end_time"));

        JButton btnConfirm = new JButton("Xác nhận");
        JButton btnCancel = new JButton("Hủy");

        dialog.add(lblTitle);
        dialog.add(txtTitle);
        dialog.add(lblSubject);
        dialog.add(txtSubject);
        dialog.add(lblContent);
        dialog.add(txtContent);
        dialog.add(lblNote);
        dialog.add(txtNote);
        dialog.add(lblStartTime);
        dialog.add(txtStartTime);
        dialog.add(lblEndTime);
        dialog.add(txtEndTime);
        dialog.add(btnConfirm);
        dialog.add(btnCancel);

        btnConfirm.addActionListener(e -> {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime startDateTime = LocalDateTime.parse(txtStartTime.getText(), formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(txtEndTime.getText(), formatter);

                if (!isValidDateTime(txtStartTime.getText()) || !isValidDateTime(txtEndTime.getText())) {
                    JOptionPane.showMessageDialog(this, "Định dạng thời gian không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isFutureDateTime(startDateTime)) {
                    JOptionPane.showMessageDialog(this, "Không thể thêm nhiệm vụ trong quá khứ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
                Timestamp endTimestamp = Timestamp.valueOf(endDateTime);

                DatabaseManager dbManager = new DatabaseManager();
                dbManager.addTask(
                        txtTitle.getText(),
                        txtSubject.getText(),
                        txtContent.getText(),
                        txtNote.getText(),
                        startTimestamp,
                        endTimestamp
                );

                JOptionPane.showMessageDialog(this, "Lưu thành công!");
                askForTestCreation(taskData);
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void appendMessage(String sender, String message, Color bgColor) {
        chatArea.setText(chatArea.getText() + "\n" + sender + ": " + message);
    }

    public boolean isValidDateTime(String dateTime) {
        String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateTime);
        return matcher.matches();
    }

    public boolean isFutureDateTime(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return dateTime.isAfter(currentDateTime);
    }

    private void askForTestCreation(JSONObject taskData) {
        int response = JOptionPane.showConfirmDialog(this,
                "Bạn có muốn tạo bài test trắc nghiệm cho môn " + taskData.getString("subject_name") + " không?",
                "Tạo bài test", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            String numberOfQuestionsStr = JOptionPane.showInputDialog(this, "Nhập số câu hỏi (tối đa 15):");
            int numberOfQuestions = 5;

            try {
                numberOfQuestions = Integer.parseInt(numberOfQuestionsStr);
                if (numberOfQuestions > 15) {
                    numberOfQuestions = 15;
                    JOptionPane.showMessageDialog(this, "Số câu hỏi đã được điều chỉnh xuống 15.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số câu hỏi không hợp lệ, sử dụng mặc định 5 câu.");
            }

            generateTestContent(taskData.getString("subject_name"), numberOfQuestions);
        } else {
            showConfirmationDialog(taskData);
        }
    }

    public void generateTestContent(String subject, int numberOfQuestions) {
        String prompt = String.format(
                "Please generate %d multiple-choice questions for the subject '%s'. Each question should have exactly one correct answer and three incorrect answers.\n"
                        + "\n"
                        + "Format each question as follows:\n"
                        + "Question X: [Question Text]\n"
                        + "A) [Option A]\n"
                        + "B) [Option B]\n"
                        + "C) [Option C]\n"
                        + "D) [Option D]\n"
                        + "Correct Answer: [A, B, C, or D]\n"
                        + "\n"
                        + "Do not provide any explanations or extra text. Only output the questions in the required format. Ensure that the number of questions is %d",
                numberOfQuestions, subject, numberOfQuestions
        );

        String response = deepSeekApiCall(prompt, numberOfQuestions);
        System.out.println("Phản hồi từ API:\n" + response);

        try {
            String[] questions = response.split("\\n\\s*\\n");

            for (String questionBlock : questions) {
                questionBlock = questionBlock.trim();
                String[] lines = questionBlock.split("\\n");

                if (lines.length >= 6) {
                    String question = lines[1].trim();
                    String answerA = extractAnswer(lines[2], "A)");
                    String answerB = extractAnswer(lines[3], "B)");
                    String answerC = extractAnswer(lines[4], "C)");
                    String answerD = extractAnswer(lines[5], "D)");
                    String correctAnswer = extractAnswer(lines[6], "Correct Answer:");

                    if (answerA == null || answerB == null || answerC == null || answerD == null || correctAnswer == null) {
                        System.out.println("⚠️ Câu hỏi không đúng định dạng, bỏ qua.");
                        continue;
                    }

                    System.out.println("Câu hỏi: " + question);
                    System.out.println("A. " + answerA);
                    System.out.println("B. " + answerB);
                    System.out.println("C. " + answerC);
                    System.out.println("D. " + answerD);
                    System.out.println("Đáp án đúng: " + correctAnswer);
                    System.out.println();
                } else {
                    System.out.println("⚠️ Câu hỏi không đủ dòng, bỏ qua: " + questionBlock);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractAnswer(String line, String prefix) {
        int index = line.indexOf(prefix);
        if (index != -1) {
            return line.substring(index + prefix.length()).trim();
        }
        return null;
    }

    private String deepSeekApiCall(String prompt, int numberOfQuestions) {
        String model = "mistral:7b";
        String fullResponse = "Xin lỗi, tôi không thể trả lời ngay bây giờ.";

        try {
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject requestJson = new JSONObject();
            requestJson.put("model", model);
            requestJson.put("prompt", prompt);
            requestJson.put("stream", false);
            requestJson.put("num_questions", numberOfQuestions);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestJson.toString().getBytes());
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String responseLine = br.readLine();
                JSONObject responseJson = new JSONObject(responseLine);
                fullResponse = responseJson.getString("response");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullResponse;
    }

    private void saveTestToDatabase(String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer) {
        System.out.println("Saving question to database: " + question);
        // DatabaseManager dbManager = new DatabaseManager();
        // dbManager.addTestQuestion(question, answerA, answerB, answerC, answerD, correctAnswer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatTool::new);
    }
}
