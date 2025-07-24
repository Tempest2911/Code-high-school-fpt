/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ThongBaoXacNhan extends JDialog {

    public ThongBaoXacNhan(String text) {
        if (text.equals("xacnhanlk")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn đang có một yêu cầu liên kết");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Chấp nhận liên kết");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy liên kết");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("NVHV_them")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn thêm");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Thêm nhiệm vụ");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Quay lại");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("NVHV_xoa")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn xóa nhiệm vụ");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Xóa nhiệm vụ");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("NVHV_capnhat")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn cập nhật");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Chắc chắn");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("QLTK_xoa")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn xóa");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Xóa");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("QLTK_khoa")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn khóa");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Khóa");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("QLTK_mokhoa")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn mở khóa");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Mở khóa");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("QLBV_xoa")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn xóa");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Xóa");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("QLBV_sua")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn sửa");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Sửa");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("QLBV_luunhap")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn lưu");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Lưu nháp");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("QLBV_xuatban")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn xuất bản");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Xác nhận");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("FB_xoa")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn xóa");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Xóa");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("TTCN_sua")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có chắc chắn sửa");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Sửa");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("TTCN_doimk")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn có xác nhận đổi");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Đổi");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("TTCN_dangxuat")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Bạn đăng xuất?");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Xác nhận");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("TB_xoatatca")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Xóa tất cả");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Xóa");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        } else if (text.equals("FBPHHV_gui")) {
            setLayout(new BorderLayout());

            // Icon chuông
            JLabel iconLabel = new JLabel();
            try {
                BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Do Tuong Minh\\Desktop\\hihi\\SP25\\src\\sp25\\com\\edusys\\icon\\chuong.png"));
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel.setIcon(scaledIcon);
            } catch (IOException e) {
                e.printStackTrace();
                // Nếu lỗi, bạn có thể set text để báo không load được ảnh
                iconLabel.setText("Logo");
            }

            // Nội dung thông báo
            JLabel messageLabel = new JLabel("Gửi Feedback");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // Panel chứa icon và nội dung trên cùng một hàng
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            messagePanel.add(iconLabel);
            messagePanel.add(messageLabel);

            // Nút xóa
            JButton deleteButton = new RoundedButton("Gửi");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Nút hủy
            JButton cancelButton = new RoundedButton("Hủy");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.DARK_GRAY);
            cancelButton.addActionListener(e -> dispose());
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Panel chứa nút, căn giữa
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(deleteButton);

            // Thêm các thành phần vào dialog
            JPanel spaceTop = new JPanel();
            spaceTop.setPreferredSize(new Dimension(0, 15)); // Chiều rộng 0, chiều cao 25px
            spaceTop.setBackground(Color.WHITE);
            messagePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE);

            add(spaceTop, BorderLayout.NORTH);
            add(messagePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(390, 150);
            setLocationRelativeTo(null);
        }
    }
}
