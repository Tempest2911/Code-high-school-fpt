package asm1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author drago
 */
public class View extends javax.swing.JFrame {

    ArrayList<ModelNV> listNV = new ArrayList<>();
    private boolean fileOpened = false; // Chưa mở file

    public View() {
        initComponents();
    }

    public ModelNV readForm() {
        int id = Integer.parseInt(txtMaNV.getText());
        String ten = txtName.getText();
        int tuoi = Integer.parseInt(txtTuoi.getText());
        String Email = txtEmail.getText();
        float Luong = Float.parseFloat(txtLuong.getText());
        return new ModelNV(id, ten, tuoi, Email, Luong);
    }

    public void fillTableData(ArrayList<ModelNV> m) {
        DefaultTableModel model = (DefaultTableModel) tblNV.getModel();
        model.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#.###");
        for (ModelNV model1 : m) {
            model.addRow(new Object[]{model1.getId(), model1.getHoTen(), model1.getTuoi(), model1.getEmail(), df.format(model1.getLuong())});
        }
    }

    private void updateTextFields(int row) {
        txtMaNV.setText(tblNV.getValueAt(row, 0).toString());
        txtName.setText(tblNV.getValueAt(row, 1).toString());
        txtTuoi.setText(tblNV.getValueAt(row, 2).toString());
        txtEmail.setText(tblNV.getValueAt(row, 3).toString());
        txtLuong.setText(tblNV.getValueAt(row, 4).toString());
    }

    private boolean validateFields() {
        String ten = txtName.getText().trim();
        String tuoiStr = txtTuoi.getText().trim();
        String email = txtEmail.getText().trim();
        String luongStr = txtLuong.getText().trim();

        // Kiểm tra tên không chứa số
        if (!ten.matches("^[\\p{L} ]+$")) { // Chỉ chứa chữ cái và khoảng trắng
            JOptionPane.showMessageDialog(this, "Tên không được chứa số hoặc ký tự đặc biệt!");
            return false;
        }

        // Kiểm tra tuổi là số nguyên hợp lệ
        try {
            int tuoi = Integer.parseInt(tuoiStr);
            if (tuoi <= 0) {
                JOptionPane.showMessageDialog(this, "Tuổi phải là số dương hợp lệ!");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tuổi phải là số nguyên!");
            return false;
        }

        // Kiểm tra email có đúng định dạng Gmail không
        if (!email.toLowerCase().matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            JOptionPane.showMessageDialog(this, "Email phải có định dạng hợp lệ!");
            return false;
        }

        // Kiểm tra lương phải lớn hơn 5 triệu
        try {
            float luong = Float.parseFloat(luongStr);
            if (luong < 5000000) {
                JOptionPane.showMessageDialog(this, "Lương phải lớn hơn 5 triệu!");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lương phải là số hợp lệ!");
            return false;
        }

        return true; // Nếu tất cả hợp lệ
    }

    private void add() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("employee.txt", false))) {
            for (ModelNV s : listNV) {
                bw.write(s.getId() + "|" + s.getHoTen() + "|" + s.getTuoi() + "|" + s.getEmail() + "|" + s.getLuong());
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Lưu file thành công!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi ghi file!");
        }
    }

    private void updateFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("employee.txt", false))) {
            for (ModelNV nv : listNV) {
                bw.write(nv.getId() + "|" + nv.getHoTen() + "|" + nv.getTuoi() + "|" + nv.getEmail() + "|" + nv.getLuong());
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật file!");
        }
    }

    private void deleteByID(int id) {
        boolean found = false;

        // Duyệt danh sách để tìm và xóa nhân viên có ID cần xóa
        for (int i = 0; i < listNV.size(); i++) {
            if (listNV.get(i).getId() == id) {
                listNV.remove(i); // Xóa phần tử khỏi danh sách
                found = true;
                break;
            }
        }

        if (found) {
            updateFile(); // Ghi lại file sau khi xóa
            fillTableData(listNV); // Cập nhật lại bảng
            JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy ID cần xóa!");
        }
    }

    private void readFromFile() {
        File file = new File("employee.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "File không tồn tại!");
            return;
        }

        listNV.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|"); // Sử dụng dấu '|' thay vì '-'
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String ten = parts[1].trim();
                    int tuoi = Integer.parseInt(parts[2].trim());
                    String email = parts[3].trim();
                    float luong = Float.parseFloat(parts[4].trim());
                    listNV.add(new ModelNV(id, ten, tuoi, email, luong));
                }
            }
            fileOpened = true;
            SwingUtilities.invokeLater(() -> fillTableData(listNV));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        btnTop = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnBottom = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        label8 = new java.awt.Label();
        txtFindmaSV = new java.awt.TextField();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtName = new java.awt.TextField();
        txtMaNV = new java.awt.TextField();
        label9 = new java.awt.Label();
        txtTuoi = new java.awt.TextField();
        label10 = new java.awt.Label();
        txtEmail = new java.awt.TextField();
        label11 = new java.awt.Label();
        txtLuong = new java.awt.TextField();
        label12 = new java.awt.Label();
        label13 = new java.awt.Label();
        btnSave = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 51, 204));
        label1.setText("QUẢN LÝ NHÂN VIÊN");

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ và tên", "Tuổi", "Email", "Lương"
            }
        ));
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNV);

        btnTop.setBackground(new java.awt.Color(0, 255, 255));
        btnTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm1/1.png"))); // NOI18N
        btnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopActionPerformed(evt);
            }
        });

        btnPrevious.setBackground(new java.awt.Color(0, 0, 255));
        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm1/2.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(255, 0, 51));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm1/3.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnBottom.setBackground(new java.awt.Color(0, 255, 51));
        btnBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm1/4.png"))); // NOI18N
        btnBottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBottomActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(255, 255, 153));
        btnNew.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnNew.setForeground(new java.awt.Color(51, 153, 255));
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(102, 255, 102));
        btnDelete.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(51, 153, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label8.setText("Mã NV:");

        txtFindmaSV.setBackground(new java.awt.Color(86, 176, 255));
        txtFindmaSV.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        btnSearch.setBackground(new java.awt.Color(153, 153, 255));
        btnSearch.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 51, 51));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm1/ezgif-4-3142f4933d.gif"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFindmaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFindmaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtName.setBackground(new java.awt.Color(255, 255, 204));
        txtName.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        txtMaNV.setBackground(new java.awt.Color(204, 255, 204));
        txtMaNV.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        label9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label9.setText("Mã SV:");

        txtTuoi.setBackground(new java.awt.Color(255, 204, 204));
        txtTuoi.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTuoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuoiKeyReleased(evt);
            }
        });

        label10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label10.setText("Tuổi:");

        txtEmail.setBackground(new java.awt.Color(204, 204, 255));
        txtEmail.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        label11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label11.setText("Email:");

        txtLuong.setBackground(new java.awt.Color(204, 255, 255));
        txtLuong.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLuongKeyReleased(evt);
            }
        });

        label12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label12.setText("Lương:");

        label13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label13.setText("Họ và tên:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txtTuoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 28, Short.MAX_VALUE)
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        btnSave.setBackground(new java.awt.Color(255, 153, 153));
        btnSave.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnSave.setForeground(new java.awt.Color(51, 153, 255));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnOpen.setBackground(new java.awt.Color(255, 153, 102));
        btnOpen.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnOpen.setForeground(new java.awt.Color(51, 153, 255));
        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 153, 255));
        btnExit.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnExit.setForeground(new java.awt.Color(51, 153, 255));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(940, 940, 940))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnTop, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(btnBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(152, 152, 152))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnNew)
                        .addGap(4, 4, 4)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrevious, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if (!fileOpened) {
            JOptionPane.showMessageDialog(this, "Bạn phải mở file trước khi thao tác!");
            return;
        }

        if (tblNV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        String idSearchText = txtFindmaSV.getText();
        if (idSearchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ban chua dien ma ID de tim");
            return;
        }

        int IDSearch = Integer.parseInt(txtFindmaSV.getText());

        for (int i = 0; i < tblNV.getRowCount(); i++) {

            int IDInTable = Integer.parseInt(tblNV.getValueAt(i, 0).toString());

            if (IDInTable == IDSearch) {
                txtMaNV.setText(String.valueOf(IDInTable));
                txtName.setText(tblNV.getValueAt(i, 1).toString());
                txtTuoi.setText(tblNV.getValueAt(i, 2).toString());
                txtEmail.setText(tblNV.getValueAt(i, 3).toString());
                txtLuong.setText(tblNV.getValueAt(i, 4).toString());
            }
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtMaNV.setText(null);
        txtName.setText(null);
        txtTuoi.setText(null);
        txtEmail.setText(null);
        txtLuong.setText(null);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (!fileOpened) {
            JOptionPane.showMessageDialog(this, "Bạn phải mở file trước khi thao tác!");
            return;
        }
        if (txtMaNV.getText().isEmpty() || txtName.getText().isEmpty() || txtTuoi.getText().isEmpty() || txtEmail.getText().isEmpty() || txtLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa điền hoặc điền thiếu thông tin");
            return;
        }

        if (!validateFields()) { // Gọi hàm kiểm tra dữ liệu
            return;
        }

        try {
            int id = Integer.parseInt(txtMaNV.getText().trim());
            String ten = txtName.getText().trim();
            int tuoi = Integer.parseInt(txtTuoi.getText().trim());
            String email = txtEmail.getText().trim();
            float luong = Float.parseFloat(txtLuong.getText().trim());

            // Đọc danh sách nhân viên từ file để kiểm tra trùng ID
            File file = new File("employee.txt");
            if (file.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split("|");
                        if (parts.length > 0) {
                            int existingID = Integer.parseInt(parts[0].trim());
                            if (existingID == id) {
                                JOptionPane.showMessageDialog(this, "ID đã tồn tại, vui lòng nhập ID khác!");
                                return;
                            }
                        }
                    }
                }
            }

            // Nếu không trùng ID, tạo đối tượng mới
            ModelNV nv = new ModelNV(id, ten, tuoi, email, luong);

            // Thêm vào danh sách
            listNV.add(nv);

            // Ghi vào file
            add();
            listNV.clear();
            // Đọc lại từ file để cập nhật JTable
            readFromFile();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file!");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        if (!fileOpened) {
            JOptionPane.showMessageDialog(this, "Bạn phải mở file trước khi thao tác!");
            return;
        }

        if (txtMaNV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ID cần xóa!");
            return;
        }

        try {
            int id = Integer.parseInt(txtMaNV.getText().trim());
            deleteByID(id); // Gọi hàm xóa
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID phải là số nguyên hợp lệ!");
        }


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        int selectedIndex = tblNV.getSelectedRow();
        txtMaNV.setText(tblNV.getValueAt(selectedIndex, 0).toString());
        txtName.setText(tblNV.getValueAt(selectedIndex, 1).toString());
        txtTuoi.setText(tblNV.getValueAt(selectedIndex, 2).toString());
        txtEmail.setText(tblNV.getValueAt(selectedIndex, 3).toString());
        txtLuong.setText(tblNV.getValueAt(selectedIndex, 4).toString());
    }//GEN-LAST:event_tblNVMouseClicked

    private void btnBottomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBottomActionPerformed
        if (!fileOpened) {
            JOptionPane.showMessageDialog(this, "Bạn phải mở file trước khi thao tác!");
            return;
        }
        if (tblNV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        int lastRow = tblNV.getRowCount() - 1;
        tblNV.setRowSelectionInterval(lastRow, lastRow);
        updateTextFields(lastRow);
    }//GEN-LAST:event_btnBottomActionPerformed

    private void txtTuoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuoiKeyReleased

    }//GEN-LAST:event_txtTuoiKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased

    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongKeyReleased

    }//GEN-LAST:event_txtLuongKeyReleased

    private void btnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopActionPerformed
        if (!fileOpened) {
            JOptionPane.showMessageDialog(this, "Bạn phải mở file trước khi thao tác!");
            return;
        }
        if (tblNV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        tblNV.setRowSelectionInterval(0, 0);
        updateTextFields(0);
    }//GEN-LAST:event_btnTopActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (!fileOpened) {
            JOptionPane.showMessageDialog(this, "Bạn phải mở file trước khi thao tác!");
            return;
        }
        if (tblNV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        int selectedRow = tblNV.getSelectedRow();
        if (selectedRow < tblNV.getRowCount() - 1) {
            tblNV.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
            updateTextFields(selectedRow + 1);
        } else {
            tblNV.setRowSelectionInterval(0, 0);
            updateTextFields(0);
        }

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (!fileOpened) {
            JOptionPane.showMessageDialog(this, "Bạn phải mở file trước khi thao tác!");
            return;
        }
        if (tblNV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        int selectedRow = tblNV.getSelectedRow();
        if (selectedRow > 0) {
            tblNV.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
            updateTextFields(selectedRow - 1);
        } else {
            int lastRow = tblNV.getRowCount() - 1;
            tblNV.setRowSelectionInterval(lastRow, lastRow);
            updateTextFields(lastRow);
        }

    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        readFromFile();

    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

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
    private javax.swing.JButton btnBottom;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTop;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private javax.swing.JTable tblNV;
    private java.awt.TextField txtEmail;
    private java.awt.TextField txtFindmaSV;
    private java.awt.TextField txtLuong;
    private java.awt.TextField txtMaNV;
    private java.awt.TextField txtName;
    private java.awt.TextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}
