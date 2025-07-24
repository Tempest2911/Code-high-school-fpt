/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền bo tròn
        g2.setColor(new Color(0, 51, 102)); // Màu xanh đậm
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        // Vẽ viền (nếu cần)
        g2.setColor(getBackground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Không cần vẽ viền
    }
}

public class TaskEditor extends JFrame {

    public TaskEditor(String text) {

        
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Tiêu đề");
        JTextField txtTitle = new JTextField("Nhiệm vụ 1", 20);

        JLabel lblSubject = new JLabel("Môn học");
        //JTextField txtSubject = new JTextField("Tiếng Anh");
        JComboBox cboSubject = new JComboBox(new Object[]{"None","Toán", "Văn", "Tiếng Anh"});

        JLabel lblTime = new JLabel("Thời gian");
        JSpinner spnStart = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(spnStart, "HH:mm");
        spnStart.setEditor(startEditor);
        spnStart.setValue(new java.util.Date());

        JSpinner spnEnd = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(spnEnd, "HH:mm");
        spnEnd.setEditor(endEditor);
        spnEnd.setValue(new java.util.Date());

        JLabel lblTask = new JLabel("Nhiệm vụ");
        JTextField txtTask = new JTextField("Thì hiện tại đơn trong tiếng Anh");

        JLabel lblNotes = new JLabel("Ghi chú");
        JTextArea txtNotes = new JTextArea(3, 20);
        txtNotes.setText("Hoàn thành các câu trắc nghiệm trong bài kiểm tra");
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtNotes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panel.add(txtTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblSubject, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panel.add(cboSubject, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(lblTime, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        panel.add(spnStart, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(new JLabel("Đến"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        panel.add(spnEnd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(lblTask, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panel.add(txtTask, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(lblNotes, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);
        
        if (text.equals("chitiet")) {
            setTitle("Chỉnh sửa nhiệm vụ");
            JButton btnComplete = new RoundedButton("Hoàn thành");
            JButton btnUpdate = new RoundedButton("Cập nhật");

// Cấu hình thêm cho nút
            btnComplete.setForeground(Color.WHITE);
            btnComplete.setPreferredSize(new Dimension(120, 40));
            btnComplete.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnUpdate.setForeground(Color.WHITE);
            btnUpdate.setPreferredSize(new Dimension(120, 40));
            btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThongBaoXacNhan lk = new ThongBaoXacNhan("thaydoi");
                    lk.setVisible(true);
                }
            });
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            gbc.weighty = 0;
            panel.add(btnComplete, gbc);

            gbc.gridx = 2;
            gbc.gridy = 5;
            panel.add(btnUpdate, gbc);
        } else if (text.equals("them")) {
            txtNotes.setText("");
            txtTask.setText("");
            txtTitle.setText("");
            
            setTitle("Thêm nhiệm vụ");
            JButton btnAdd = new RoundedButton("Thêm");

// Cấu hình thêm cho nút
            btnAdd.setForeground(Color.WHITE);
            btnAdd.setPreferredSize(new Dimension(120, 40));
            btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThongBaoXacNhan lk = new ThongBaoXacNhan("thaydoi");
                    lk.setVisible(true);
                }
            });
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            gbc.weighty = 0;
            panel.add(btnAdd, gbc);
            
        }

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TaskEditor("them").setVisible(true);
        });
    }
}
