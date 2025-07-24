package com.mycompany.coachmeai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TestGenerator {
    private static final String DB_URL = "jdbc:sqlserver://Tempest:1433;databasename=Coach_Me_AI;trustServerCertificate=true;user=sa;password=123456;charset=UTF-8";
    private static final Logger logger = Logger.getLogger(TestGenerator.class.getName());

    public List<CauHoiBaiTest> generateTestQuestions(String prompt, int numberOfQuestions) {
        // Sử dụng AI (hoặc logic tùy chỉnh) để tạo câu hỏi bài test
        // Thay thế phần này bằng logic thực tế của bạn
        List<CauHoiBaiTest> questions = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            CauHoiBaiTest question = new CauHoiBaiTest();
            question.cauHoi = "Câu hỏi " + (i + 1) + " cho nhiệm vụ: " + prompt;
            question.dapAnDung = "Đáp án đúng " + (i + 1);
            questions.add(question);
        }
        return questions;
    }

    public void insertTestQuestionsToDatabase(int idNhiemVu, List<CauHoiBaiTest> questions) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            for (CauHoiBaiTest question : questions) {
                insertToDatabaseCHBT(conn, idNhiemVu, question.cauHoi, question.dapAnDung);
            }
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Bài test đã được tạo thành công!"));
        } catch (SQLException e) {
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Lỗi khi tạo bài test: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE));
            logger.log(Level.SEVERE, "Lỗi khi tạo bài test", e);
        }
    }

    private static void insertToDatabaseCHBT(Connection conn, int idNhiemVu, String cauHoi, String dapAnDung) throws SQLException {
        String sqlInsert = "INSERT INTO Cau_Hoi_Bai_Test (ID_Nhiem_Vu, Cau_Hoi, Dap_An_Dung) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
            stmt.setInt(1, idNhiemVu);
            stmt.setString(2, cauHoi);
            stmt.setString(3, dapAnDung);
            stmt.executeUpdate();
        }
    }

    public static class CauHoiBaiTest {
        public String cauHoi;
        public String dapAnDung;
    }

    public String extractField(String response, String fieldName) {
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
}
