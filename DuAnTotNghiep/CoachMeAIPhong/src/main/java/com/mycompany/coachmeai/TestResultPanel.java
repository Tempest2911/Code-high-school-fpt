/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TestResultPanel extends JPanel {

    public TestResultPanel() {
        setLayout(new BorderLayout());

        // Panel chứa thông tin học viên và điểm
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        Font customFont = new Font("Arial", Font.BOLD, 20);
        infoPanel.add(new JLabel(" ") {
            {
                setFont(customFont);
            }
        });
        infoPanel.add(new JLabel("     Mã học viên: HV0123") {
            {
                setFont(customFont);
            }
        });
        infoPanel.add(new JLabel("     Môn học: Tiếng Anh") {
            {
                setFont(customFont);
            }
        });
        infoPanel.add(new JLabel("     Nhiệm vụ: Thì hiện tại đơn") {
            {
                setFont(customFont);
            }
        });
        JPanel scorePanelTong = new JPanel(new BorderLayout());
        scorePanelTong.setBackground(Color.WHITE);
        scorePanelTong.setPreferredSize(new Dimension(120, 120)); // Kích thước lớn hơn
        
        JPanel trang = new JPanel();
        trang.setBackground(Color.WHITE);
        trang.setPreferredSize(new Dimension(100, 20)); // Kích thước lớn hơn
        
        JPanel trang2 = new JPanel();
        trang2.setBackground(Color.WHITE);
        trang2.setPreferredSize(new Dimension(20, 100)); // Kích thước lớn hơn
        
        scorePanelTong.add(trang, BorderLayout.NORTH);
        scorePanelTong.add(trang2, BorderLayout.EAST);
        
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(Color.WHITE);
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Thêm viền
        scorePanel.setPreferredSize(new Dimension(100, 100)); // Kích thước lớn hơn
        
        JLabel scoreLabel = new JLabel("9", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 60));
        
        scorePanel.add(scoreLabel);
        scorePanelTong.add(scorePanel);

        topPanel.add(infoPanel, BorderLayout.WEST);
        topPanel.add(scorePanelTong, BorderLayout.EAST);
        JLabel testTitle = new JLabel("      Test 1");
        testTitle.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(testTitle, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Tiêu đề bài test
        // Dữ liệu bảng
        String[] columnNames = {"Câu", "Câu hỏi", "Đáp án của bạn", "Đáp án đúng", "Điểm"};
        Object[][] data = {
            {"1", "She ___ to school every day.", "B", "B", "1"},
            {"2", "My father ___ coffee in the morning.", "C", "B", "0"},
            {"3", "The sun ___ in the sky.", "B", "B", "1"}
        };

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        table.setRowHeight(100); // Đặt chiều cao tất cả các hàng là 40 pixel

        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel spaceLeft = new JPanel();
        spaceLeft.setPreferredSize(new Dimension(25, 0)); // Chiều rộng 25px, chiều cao 0
        spaceLeft.setBackground(Color.WHITE);

        JPanel spaceTop = new JPanel();
        spaceTop.setPreferredSize(new Dimension(0, 25)); // Chiều rộng 0, chiều cao 25px
        spaceTop.setBackground(Color.WHITE);

        JPanel spaceRight = new JPanel();
        spaceRight.setPreferredSize(new Dimension(25, 0));
        spaceRight.setBackground(Color.WHITE);

        panel.add(spaceLeft, BorderLayout.WEST);
        panel.add(spaceTop, BorderLayout.NORTH);
        panel.add(spaceRight, BorderLayout.EAST);
        add(panel, BorderLayout.CENTER);

        // Nút xác nhận
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton confirmButton = new JButton("Xác nhận");
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kết quả bài test");
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(710, 650);
        frame.add(new TestResultPanel());
        frame.setVisible(true);
    }
}
