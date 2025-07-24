/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeedBack extends JPanel {

    static String connectionUrl = "jdbc:sqlserver://TEMPEST:1433;databaseName=Coach_Me_AI;trustServerCertificate=true;user=sa;password=123456";

    private JFrame frame;
    private JPanel feedbackPanel;
    private JScrollPane scrollPane;

    public FeedBack() {
        giaoDien_feedback();
    }

    public void giaoDien_feedback() {
        this.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBounds(0, 0, 710, 650);
        whitePanel.setLayout(null);

        // Tiêu đề
        JLabel titleLabel = new JLabel("FEEDBACK");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(250, 20, 300, 50);
        whitePanel.add(titleLabel);

        // Tạo panel chứa feedback
        feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));
        //feedbackPanel.setBackground(Color.white);

        // ScrollPane chứa feedbackPanel
        scrollPane = new JScrollPane(feedbackPanel);
        scrollPane.setBounds(20, 80, 650, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        whitePanel.add(scrollPane);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        loadFeedback(); // Load dữ liệu từ CSDL

        this.add(whitePanel);
        this.setVisible(true);
    }

    private void loadFeedback() {
        feedbackPanel.removeAll();
        try (Connection conn = DriverManager.getConnection(connectionUrl); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT ID_FeedBack, Noi_Dung_FeedBack, Ngay_Gui, ID_Tai_Khoan FROM FeedBack ORDER BY Ngay_Gui DESC")) {

            while (rs.next()) {
                int feedbackID = rs.getInt("ID_FeedBack");
                String content = rs.getString("Noi_Dung_FeedBack");
                String date = rs.getString("Ngay_Gui");
                int userID = rs.getInt("ID_Tai_Khoan");

                addFeedbackItem(feedbackID, userID, content, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        feedbackPanel.revalidate();
        feedbackPanel.repaint();
    }

    private void addFeedbackItem(int feedbackID, int userID, String content, String date) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Feedback từ tài khoản: " + userID));

        // Tạo JTextArea để hiển thị nội dung feedback
        JTextArea txtContent = new JTextArea("Ngày gửi: " + date + "\n" + content);
        txtContent.setWrapStyleWord(true);
        txtContent.setLineWrap(true);
        txtContent.setEditable(false);
        txtContent.setBackground(Color.WHITE);
        txtContent.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Đặt kích thước tối thiểu để hiển thị đẹp hơn
        txtContent.setPreferredSize(new java.awt.Dimension(600, 80));

        // ScrollPane chứa nội dung phản hồi
        JScrollPane contentScroll = new JScrollPane(txtContent);
        contentScroll.setBorder(null);

        // Tạo panel chứa nút và căn về bên phải
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Căn nút về bên phải
        JButton btnReply = new JButton("Trả Lời");
        JButton btnDelete = new JButton("Xóa");

        panel.setBackground(Color.WHITE);
        buttonPanel.setBackground(Color.WHITE);

        btnReply.addActionListener(e -> replyFeedback(feedbackID));
        btnDelete.addActionListener(e -> deleteFeedback(feedbackID));

        btnDelete.setBackground(Color.decode("#064469"));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));

        btnReply.setBackground(Color.decode("#064469"));
        btnReply.setForeground(Color.WHITE);
        btnReply.setFont(new Font("Arial", Font.BOLD, 12));

        buttonPanel.add(btnReply);
        buttonPanel.add(btnDelete);

        // Thêm vào panel chính
        panel.add(contentScroll, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        feedbackPanel.add(panel);
    }

    private void replyFeedback(int feedbackID) {
        JTextArea textArea = new JTextArea(5, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Đặt placeholder ban đầu
        String placeholder = "Phản hồi tại đây...";
        textArea.setText(placeholder);
        textArea.setForeground(Color.GRAY);
        textArea.setBackground(Color.WHITE); // Nền trắng

        // Bo viền đen và đổ bóng nhẹ
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2), // Viền đen
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Khoảng cách nội dung
        ));

        // Xử lý sự kiện khi focus vào JTextArea
        textArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals(placeholder)) {
                    textArea.setText(""); // Xóa placeholder khi nhấn vào
                    textArea.setForeground(Color.BLACK); // Chữ màu đen
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().trim().isEmpty()) {
                    textArea.setText(placeholder); // Hiện lại placeholder nếu không nhập gì
                    textArea.setForeground(Color.GRAY);
                }
            }
        });

        // Cho JTextArea vào JScrollPane để có thanh cuộn
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Tạo nút tùy chỉnh "Gửi" và "Hủy"
        Object[] options = {"Gửi", "Hủy"};

        int option = JOptionPane.showOptionDialog(
                null, scrollPane, "Nhập nội dung phản hồi:",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (option == 0) { // Nếu người dùng bấm "Gửi"
            String reply = textArea.getText().trim();

            if (!reply.isEmpty() && !reply.equals(placeholder)) {
                try (Connection conn = DriverManager.getConnection(connectionUrl); PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO Tra_Loi_FeedBack (ID_FeedBack, Noi_Dung_Tra_Loi, Thoi_Gian, ID_Admin) VALUES (?, ?, GETDATE(), ?)")) {

                    pstmt.setInt(1, feedbackID);
                    pstmt.setString(2, reply);
                    pstmt.setInt(3, 1); // ID_Admin giả định là 1

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Phản hồi đã được gửi!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nội dung phản hồi không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteFeedback(int feedbackID) {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa feedback này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(connectionUrl); PreparedStatement pstmt = conn.prepareStatement("DELETE FROM FeedBack WHERE ID_FeedBack = ?")) {
                pstmt.setInt(1, feedbackID);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Feedback đã bị xóa!");
                loadFeedback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Feedback");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(710, 650);
            frame.setResizable(false);

            FeedBack feedbackPanel = new FeedBack();
            frame.add(feedbackPanel);

            frame.setLocationRelativeTo(null); // Căn giữa màn hình
            frame.setVisible(true);
        });
    }
}
