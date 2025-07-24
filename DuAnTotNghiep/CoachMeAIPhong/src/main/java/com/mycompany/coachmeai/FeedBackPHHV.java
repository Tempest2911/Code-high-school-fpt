/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FeedBackPHHV extends JPanel{

    public FeedBackPHHV() {
        giaoDien_Feedback_PH_HV();

    }

    public void giaoDien_Feedback_PH_HV() {
        this.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBounds(0, 0, 710, 650);
        whitePanel.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(224, 240, 255));
        panel_2.setBounds(30, 30, 620, 550);
        panel_2.setLayout(null);
        panel_2.setBorder(BorderFactory.createLineBorder(new Color(224, 240, 255), 2));
        whitePanel.add(panel_2);

        JLabel chuongLabel = new JLabel();
        String imagePath = "C:\\Users\\Admin\\Documents\\Image_TuAnh\\2.png";
        ImageIcon chuongImage = new ImageIcon(imagePath);

        if (chuongImage.getIconWidth() > 0) {
            Image img = chuongImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            chuongLabel.setIcon(new ImageIcon(img));
        } else {
            chuongLabel.setText("Ảnh không tồn tại!");
        }
        chuongLabel.setBounds(20, 20, 50, 50);

        JLabel titleLabel = new JLabel("FEEDBACK");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(80, 20, 300, 50);

        JLabel descriptionLabel = new JLabel("<html>"
                + "<b>\"Chúng tôi trân trọng mọi ý kiến đóng góp từ bạn!\"</b><br><br>"
                + "Chất lượng dịch vụ và trải nghiệm người dùng luôn là ưu tiên hàng đầu của chúng tôi.<br>"
                + "Để không ngừng cải thiện, chúng tôi mong nhận được những phản hồi chân thành từ bạn.<br><br>"
                + "Vui lòng để lại đánh giá của bạn bên dưới. Chúng tôi cam kết lắng nghe và cải tiến."
                + "</html>");

        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setBounds(20, 80, 580, 120); // Tăng chiều cao để không bị cắt chữ
        panel_2.add(descriptionLabel);

        JTextArea feedbackField = new JTextArea("Mời nhập feedback ...");
        feedbackField.setFont(new Font("Arial", Font.PLAIN, 14));
        feedbackField.setLineWrap(true);
        feedbackField.setForeground(Color.GRAY);
        feedbackField.setWrapStyleWord(true);
        feedbackField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JScrollPane scrollPane = new JScrollPane(feedbackField);
        scrollPane.setBounds(20, 200, 580, 200);

        feedbackField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (feedbackField.getText().equals("Mời nhập feedback ...")) {
                    feedbackField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (feedbackField.getText().isEmpty()) {
                    feedbackField.setText("Mời nhập feedback ...");
                }
            }
        });

        panel_2.add(scrollPane);

        JButton sendButton = new JButton("Gửi");
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendButton.setBackground(new Color(10, 58, 95));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        sendButton.setBounds(250, 420, 120, 40);

        // Sự kiện khi nhấn "Gửi"
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String feedbackText = feedbackField.getText().trim();

                if (!feedbackText.isEmpty() && !feedbackText.equals("Mời nhập feedback ...")) {
                    // Hiển thị thông báo gửi thành công
                    JOptionPane.showMessageDialog(null, "Gửi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    // Đặt lại text về mặc định
                    feedbackField.setText("Mời nhập feedback ...");
                    feedbackField.setForeground(Color.GRAY);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập feedback trước khi gửi!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        panel_2.add(sendButton);
        panel_2.add(chuongLabel);
        panel_2.add(titleLabel);
        add(whitePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Feedback PH_HV");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(710, 650);
            frame.setResizable(false);

            FeedBackPHHV feedbackPH_HV = new FeedBackPHHV();
            frame.add(feedbackPH_HV);
            frame.setLocationRelativeTo(null); // Căn giữa màn hình
            frame.setVisible(true);
        });
    }

}
