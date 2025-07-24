/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package asm1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Random;
import java.util.stream.Stream;
import javax.swing.JScrollPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotUndoException;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drago
 */
public class View extends javax.swing.JFrame {

    ArrayList<String> allAccount = new ArrayList<>();

    String curentFile = "";

    /**
     * Creates new form View
     */
    public View() {
        initComponents();
        Login();
    }

    public void Login() {

        UndoManager undoManager = new UndoManager();
        JTextArea jTextArea = new JTextArea();
        jTextArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        JMenuBar menubar = new JMenuBar();

        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        JMenu menu3 = new JMenu("Information");

        JMenuItem menuItem1 = new JMenuItem("Open");
        JMenuItem menuItem2 = new JMenuItem("Save");
        JMenuItem menuItem3 = new JMenuItem("New");

        JMenuItem menuItem4 = new JMenuItem("Back");
        JMenuItem menuItem5 = new JMenuItem("Clear");
        JMenuItem menuItem6 = new JMenuItem("Random Text");

// Thêm các mục vào menu
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);

        menu2.add(menuItem4);
        menu2.add(menuItem5);
        menu2.add(menuItem6);

// Thêm menu vào menubar
        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);

// Đặt menubar vào JFrame
        this.setJMenuBar(menubar);
        menubar.setVisible(false);

        JTextField txt_Username = new JTextField();
        JPasswordField txt_Password = new JPasswordField();

        JLabel Username = new JLabel("Username");
        JLabel Password = new JLabel("Password");
        JButton btn_Login = new JButton("Login");

        Username.setSize(200, 100);
        Username.setLocation(40, 10);
        Username.setFont(new Font("Arial", 1, 15));
        this.add(Username);

        Password.setSize(200, 100);
        Password.setLocation(40, 70);
        Password.setFont(new Font("Arial", 1, 15));
        this.add(Password);

        txt_Username.setSize(300, 40);
        txt_Username.setLocation(150, 40);
        this.add(txt_Username);

        txt_Password.setSize(300, 40);
        txt_Password.setLocation(150, 100);
        this.add(txt_Password);

        btn_Login.setSize(100, 40);
        btn_Login.setLocation(150, 150);
        btn_Login.setFont(new Font("Arial", 1, 15));
        this.add(btn_Login);

        jTextArea.setLocation(0, 0);
        jTextArea.setSize(10000, 10000);
        this.add(jTextArea);
        jTextArea.setFont(new Font("Arial", 0, 15));
        jTextArea.setVisible(false);

        menubar.setSize(10000, 10000);
        menubar.setLocation(0, 0);
        this.add(menubar);

        btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "D:\\CODE\\Java 3 Project\\ASM1\\taikhoan.txt";
                try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                    stream.forEach(line -> {
                        System.out.println(line);
                        allAccount.add(line);
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                String LoginInfo = txt_Username.getText() + "," + txt_Password.getText();
                boolean checkLogIn = false;
                for (int i = 0; i < allAccount.size(); i++) {
                    if (allAccount.get(i).equals(LoginInfo)) {
                        JOptionPane.showMessageDialog(rootPane, "Successfully");
                        checkLogIn = true;
                        Username.setVisible(false);
                        Password.setVisible(false);
                        txt_Username.setVisible(false);
                        txt_Password.setVisible(false);
                        btn_Login.setVisible(false);
                        jTextArea.setVisible(true);
                        menubar.setVisible(true);
                        setJMenuBar(menubar);
                        break;
                    }
                }

                if (!checkLogIn) {
                    JOptionPane.showMessageDialog(rootPane, "Failled");
                }

            }
        });

//Open
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFile(jTextArea, undoManager);
            }
        });

//SAve
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveFile(jTextArea);
            }
        });
//New
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewFile(jTextArea);
            }
        });

//BACK
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackFile(undoManager);
            }
        });

//CLEAR        
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.setText("");
            }
        });

//Random Text
        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomText(jTextArea);
            }
        });

//NOT ADMIN
        JLabel user = new JLabel("Username: ");
        JLabel pass = new JLabel("Password: ");
        JLabel userafterlogin = new JLabel();
        JLabel passafterlogin = new JLabel();

        user.setSize(200, 100);
        user.setLocation(40, 10);
        user.setFont(new Font("Arial", 1, 20));
        this.add(user);
        user.setVisible(false);

        pass.setSize(200, 100);
        pass.setLocation(40, 70);
        pass.setFont(new Font("Arial", 1, 20));
        this.add(pass);
        pass.setVisible(false);

        userafterlogin.setSize(300, 40);
        userafterlogin.setLocation(150, 40);
        userafterlogin.setFont(new Font("Arial", 1, 15));
        this.add(userafterlogin);
        userafterlogin.setVisible(false);

        passafterlogin.setSize(300, 40);
        passafterlogin.setLocation(150, 100);
        passafterlogin.setFont(new Font("Arial", 1, 15));
        this.add(passafterlogin);
        passafterlogin.setVisible(false);

        String[] columnNames = {"Username", "Password"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 40, 600, 300);
        this.add(scrollPane);
        scrollPane.setVisible(false);

//INFORMATION
        menu3.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                Information(txt_Username, txt_Password, jTextArea, model, scrollPane, user, pass, userafterlogin, passafterlogin);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

    }

    public String generateRandomText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder randomText = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomText.append(characters.charAt(index));
        }

        return randomText.toString();
    }

    public void OpenFile(JTextArea jTextArea, UndoManager undoManager) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int returnValue = chooser.showOpenDialog(rootPane);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            curentFile = file.getAbsolutePath(); //Chọn đường dẫn
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                jTextArea.getDocument().removeUndoableEditListener(undoManager);
                jTextArea.read(reader, null);
                jTextArea.getDocument().addUndoableEditListener(e1 -> undoManager.addEdit(e1.getEdit())); // Kích hoạt lại listener sau khi đọc xong
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        jTextArea.setVisible(true);
    }

    public void SaveFile(JTextArea jTextArea) {
        if (!curentFile.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(curentFile))) {
                writer.write(jTextArea.getText());
                JOptionPane.showMessageDialog(rootPane, "Successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "Failed!");
            }
        } else {
            // Nếu chưa chọn file trước đó, thông báo lỗi
            JOptionPane.showMessageDialog(rootPane, "No file selected to save. Please use 'Open' or 'New' first.");
        }
    }

    public void NewFile(JTextArea jTextArea) {
        if (!curentFile.isEmpty()) {
            int reply = JOptionPane.showConfirmDialog(rootPane,
                    "Bạn muốn lưu file trước khi tạo cái mới không?",
                    "New File",
                    JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                if (!curentFile.isEmpty()) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(curentFile))) {
                        writer.write(jTextArea.getText());
                        JOptionPane.showMessageDialog(rootPane, "File saved successfully!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(rootPane, "Failed to save file!");
                    }
                } else {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                    int returnValue = chooser.showSaveDialog(rootPane);

                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        curentFile = file.getAbsolutePath();
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                            writer.write(jTextArea.getText());
                            JOptionPane.showMessageDialog(rootPane, "File saved successfully!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(rootPane, "Failed to save file!");
                        }
                    }
                }
            } else if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION) {
                return;
            }
            jTextArea.setText("");
            curentFile = "";
        }
    }

    public void BackFile(UndoManager undoManager) {
        try {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } catch (CannotUndoException ex) {
            ex.printStackTrace();
        }
    }

    public void RandomText(JTextArea jTextArea) {
        jTextArea.setText("");
        for (int i = 0; i < 1; i++) {
            String randomText = generateRandomText(100);
            jTextArea.append(randomText);
        }
    }

    public void Information(JTextField txt_Username,
            JPasswordField txt_Password,
            JTextArea jTextArea,
            DefaultTableModel model,
            JScrollPane scrollPane,
            JLabel user,
            JLabel pass,
            JLabel userafterlogin,
            JLabel passafterlogin) {
        if (txt_Username.getText().equals("admin")) {
            jTextArea.setVisible(false);
            try {
                File file = new File("D:\\CODE\\Java 3 Project\\ASM1\\taikhoan.txt");
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line;
                model.setRowCount(0);

                while ((line = br.readLine()) != null) {
                    String[] account = line.split(",");
                    if (account.length == 2) {
                        model.addRow(new Object[]{account[0].trim(), account[1].trim()});
                    }
                }

                br.close();
                scrollPane.setVisible(true);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            jTextArea.setVisible(false);
            user.setVisible(true);
            pass.setVisible(true);
            userafterlogin.setText(txt_Username.getText());
            passafterlogin.setText(txt_Password.getText());
            userafterlogin.setVisible(true);
            passafterlogin.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
