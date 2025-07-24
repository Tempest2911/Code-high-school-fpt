/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NhiemVuPhuHuynh extends JPanel {

    private JPanel taskListPanel;
    private JLabel studentLabel;
    private List<String[]> tasks;

    public NhiemVuPhuHuynh() {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        JPanel topPanel = new JPanel(new BorderLayout());
        
        JPanel spaceRight = new JPanel();
        spaceRight.setPreferredSize(new Dimension(25, 0)); // Chiều rộng 25px, chiều cao 0
        spaceRight.setBackground(Color.WHITE);
        topPanel.add(spaceRight, BorderLayout.EAST);
        topPanel.setBackground(Color.WHITE);
        studentLabel = new JLabel("Mã học viên");
        studentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        studentLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(studentLabel, BorderLayout.WEST);
        String[] items = {"Option 1", "Option 2", "Option 3"};
        RoundedComboBox cboMaHV = new RoundedComboBox(items);
        topPanel.add(cboMaHV, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        tasks = getSampleTasks();
        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        taskListPanel.setBackground(Color.WHITE);
        updateTaskList();

        JScrollPane scrollPane = new JScrollPane(taskListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

    }

    private void updateTaskList() {
        taskListPanel.removeAll();
        for (String[] task : tasks) {
            taskListPanel.add(createTaskCard(task));
            taskListPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các thẻ
        }
        taskListPanel.revalidate();
        taskListPanel.repaint();
    }

    private JPanel createTaskCard(String[] taskData) {
        JPanel card = new JPanel();
        card.setLayout(new GridLayout(6, 1));
        card.setBackground(Color.white);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true)));
        card.setPreferredSize(new Dimension(300, 240));
        card.setMaximumSize(new Dimension(300, 240));

        JLabel titleLabel = new JLabel("Nhiệm vụ: " + taskData[0]);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel subjectLabel = new JLabel("Môn học: " + taskData[1]);
        JLabel timeLabel = new JLabel("Thời gian: " + taskData[2]);
        JLabel descriptionLabel = new JLabel("Nhiệm vụ: " + taskData[3]);
        JLabel noteLabel = new JLabel("Ghi chú: " + taskData[4]);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton viewButton = new JButton("Chi tiết");
        JButton deleteButton = new JButton("Xóa");

        deleteButton.addActionListener(e -> {
            tasks.remove(taskData);
            updateTaskList();
        });

        viewButton.addActionListener(e -> {
            viewDetails();
            updateTaskList();
        });

        viewButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);

        card.add(titleLabel);
        card.add(subjectLabel);
        card.add(timeLabel);
        card.add(descriptionLabel);
        card.add(noteLabel);
        card.add(buttonPanel);

        return card;
    }

    private void viewDetails() {
        new TaskEditor("chitiet").setVisible(true);
        updateTaskList();
        System.out.println("hello");
    }

    private List<String[]> getSampleTasks() {
        List<String[]> sampleTasks = new ArrayList<>();
        sampleTasks.add(new String[]{"Nhiệm vụ 1", "Toán", "08:00 - 10:00", "Làm bài tập", "Không có ghi chú"});
        sampleTasks.add(new String[]{"Nhiệm vụ 2", "Lý", "10:30 - 12:00", "Ôn tập chương 3", "Mang theo vở ghi"});
        sampleTasks.add(new String[]{"Nhiệm vụ 3", "Hóa", "13:00 - 15:00", "Làm thí nghiệm", "Mang theo kính bảo hộ"});
        return sampleTasks;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý Nhiệm Vụ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 650);
        frame.add(new NhiemVuPhuHuynh());
        frame.setVisible(true);
    }
}
