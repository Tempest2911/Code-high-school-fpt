/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*; // Để sử dụng JFrame, JTable, JScrollPane, v.v.
import javax.swing.table.DefaultTableModel; // Để tạo DefaultTableModel cho JTable
import java.awt.*; // Để sử dụng Color, Font
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*; //
import javax.swing.filechooser.FileNameExtensionFilter;
import DAOClass.CRUD_DAO;
import java.util.ArrayList;
import ModelsClass.HocVien;
import java.sql.Date;

/**
 *
 * @author drago
 */
public class QuanLyHocVien extends javax.swing.JFrame {

    ArrayList<HocVien> listhv = new ArrayList<>();

    /**
     * Creates new form ViewAdmin
     */
    public QuanLyHocVien() {
        initComponents();
        loadDataToTable();
        txt_ID.setEditable(false);
        ((JTextField) txt_Birthday.getDateEditor().getUiComponent()).setEditable(false);
        rdo_Male.setSelected(true);
    }
    String avatarPath = null;

    public void FillTableData(ArrayList<HocVien> m) {
        DefaultTableModel model = (DefaultTableModel) tbl_Table.getModel();
        model.setRowCount(0);
        for (HocVien hv : m) {
            model.addRow(new Object[]{
                hv.getId(),
                hv.getFullname(),
                hv.getGender(),
                hv.getBirthday(),
                hv.getPhone(),
                hv.getEmail(),
                hv.getAvatar()
            });
        }
    }

    public HocVien readForm() {
        return new HocVien(
                Integer.parseInt(txt_ID.getText()),
                txt_Name.getText(),
                rdo_Male.isSelected() ? "Male" : "Female",
                ((JTextField) txt_Birthday.getDateEditor().getUiComponent()).getText(),
                txt_Phone.getText(),
                txt_Email.getText(),
                avatarPath
        );
    }

    private String getAvatarPathFromDatabase(int ID) {
        String avatarPath = null;
        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT Avatar FROM HocVien WHERE MaHV = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                avatarPath = resultSet.getString("Avatar");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving avatar path: " + e.getMessage());
        }
        return avatarPath;
    }

    public ArrayList<HocVien> getAll() {
        return listhv;
    }

    public void AddTableDate() {
        listhv.clear();
        try (Connection con = CRUD_DAO.getConnect()) {
            String sql = "SELECT * FROM HocVien";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HocVien hv = new HocVien(
                        rs.getInt("MaHV"),
                        rs.getString("Name"),
                        rs.getString("Gender"),
                        rs.getString("Birthday"),
                        rs.getString("SDT"),
                        rs.getString("Email"),
                        rs.getString("Avatar")
                );
                listhv.add(hv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Phương thức load dữ liệu từ cơ sở dữ liệu vào bảng
    private void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_Table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT * FROM HocVien";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("MaHV");
                String name = resultSet.getString("Name");
                String gender = resultSet.getString("Gender");
                String birthday = resultSet.getString("Birthday");
                String phone = resultSet.getString("SDT");
                String email = resultSet.getString("Email");
                String avatar = resultSet.getString("Avatar");
                // Sử dụng model.addRow() thay vì tbl_Table.add()
                model.addRow(new Object[]{id, name, gender, birthday, phone, email, avatar});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

// Phương thức hiển thị ảnh trên JButton
    private void setAvatarImage(JLabel avatarButton, String avatarPath) {
        try {
            if (avatarPath != null && !avatarPath.isEmpty()) {
                // Kiểm tra xem tệp ảnh có tồn tại không
                File imageFile = new File(avatarPath);
                if (imageFile.exists()) {
                    // Tạo đối tượng ImageIcon từ file ảnh
                    ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());

                    // Lấy kích thước JButton
                    int buttonWidth = avatarButton.getWidth();
                    int buttonHeight = avatarButton.getHeight();

                    // Co giãn ảnh sao cho vừa với JButton
                    Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);

                    // Cập nhật icon cho JButton
                    avatarButton.setIcon(new ImageIcon(image));
                } else {
                    avatarButton.setText("No Image");
                }
            } else {
                avatarButton.setText("No Image");
            }
        } catch (Exception e) {
            avatarButton.setText("Error");
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

//
//
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        LogOut = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        quanLyCD = new javax.swing.JMenu();
        quanLyKH = new javax.swing.JMenu();
        quanLyTK = new javax.swing.JMenu();
        quanLyHV = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jMenu17 = new javax.swing.JMenu();
        Exit = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        txt_Name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Phone = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        btn_Create = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_AddImage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Table = new javax.swing.JTable();
        rdo_Female = new javax.swing.JRadioButton();
        rdo_Male = new javax.swing.JRadioButton();
        txt_Birthday = new com.toedter.calendar.JDateChooser();
        txt_Image = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        LogOut1 = new javax.swing.JMenu();
        Exit1 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        quanLyCD1 = new javax.swing.JMenu();
        quanLyKH1 = new javax.swing.JMenu();
        quanLyTK1 = new javax.swing.JMenu();
        quanLyHV1 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        jMenu18 = new javax.swing.JMenu();
        jMenu19 = new javax.swing.JMenu();
        jMenu20 = new javax.swing.JMenu();

        jMenu1.setText("Hệ thống");

        jMenu5.setText("Đổi mật khẩu");
        jMenu1.add(jMenu5);

        LogOut.setText("Đăng xuất");
        LogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOutMouseClicked(evt);
            }
        });
        jMenu1.add(LogOut);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lý");

        quanLyCD.setText("Chuyên đề");
        quanLyCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyCDMouseClicked(evt);
            }
        });
        jMenu2.add(quanLyCD);

        quanLyKH.setText("Khóa học");
        quanLyKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyKHMouseClicked(evt);
            }
        });
        jMenu2.add(quanLyKH);

        quanLyTK.setText("Tài khoản");
        quanLyTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyTKMouseClicked(evt);
            }
        });
        jMenu2.add(quanLyTK);

        quanLyHV.setText("Học viên");
        quanLyHV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyHVMouseClicked(evt);
            }
        });
        jMenu2.add(quanLyHV);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Thống kê");

        jMenu12.setText("Bảng điểm");
        jMenu3.add(jMenu12);

        jMenu13.setText("Doanh thu");
        jMenu3.add(jMenu13);

        jMenu14.setText("Lượng người học");
        jMenu3.add(jMenu14);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Trợ giúp");

        jMenu16.setText("Hướng dẫn sử dụng");
        jMenu4.add(jMenu16);

        jMenu17.setText("Giới thiệu về chúng tôi");
        jMenu4.add(jMenu17);

        jMenuBar1.add(jMenu4);

        Exit.setText("Thoát");
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(Exit);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("ID");

        txt_Name.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel8.setText("Name");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel9.setText("Gender");

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel10.setText("Birthday");

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel11.setText("Phone");

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel12.setText("Email");

        txt_ID.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        txt_Phone.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        txt_Email.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        btn_Create.setText("Create");
        btn_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateActionPerformed(evt);
            }
        });

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_AddImage.setText("Image");
        btn_AddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddImageActionPerformed(evt);
            }
        });

        tbl_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Gender", "Birthday", "Phone", "Email"
            }
        ));
        tbl_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Table);

        buttonGroup1.add(rdo_Female);
        rdo_Female.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        rdo_Female.setText("Female");

        buttonGroup1.add(rdo_Male);
        rdo_Male.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        rdo_Male.setText("Male");

        txt_Birthday.setDateFormatString("y-MM-d");

        jMenu7.setText("Hệ thống");

        jMenu6.setText("Trang chủ");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu7.add(jMenu6);

        jMenu8.setText("Đổi mật khẩu");
        jMenu7.add(jMenu8);

        LogOut1.setText("Đăng xuất");
        LogOut1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOut1MouseClicked(evt);
            }
        });
        jMenu7.add(LogOut1);

        Exit1.setText("Thoát");
        Exit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Exit1MouseClicked(evt);
            }
        });
        jMenu7.add(Exit1);

        jMenuBar2.add(jMenu7);

        jMenu9.setText("Quản lý");

        quanLyCD1.setText("Chuyên đề");
        quanLyCD1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyCD1MouseClicked(evt);
            }
        });
        jMenu9.add(quanLyCD1);

        quanLyKH1.setText("Khóa học");
        quanLyKH1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyKH1MouseClicked(evt);
            }
        });
        jMenu9.add(quanLyKH1);

        quanLyTK1.setText("Tài khoản");
        quanLyTK1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyTK1MouseClicked(evt);
            }
        });
        jMenu9.add(quanLyTK1);

        quanLyHV1.setText("Học viên");
        quanLyHV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyHV1MouseClicked(evt);
            }
        });
        jMenu9.add(quanLyHV1);

        jMenuBar2.add(jMenu9);

        jMenu10.setText("Thống kê");

        jMenu15.setText("Thống kê tổng hợp");
        jMenu15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu15MouseClicked(evt);
            }
        });
        jMenu10.add(jMenu15);

        jMenuBar2.add(jMenu10);

        jMenu18.setText("Trợ giúp");

        jMenu19.setText("Hướng dẫn sử dụng");
        jMenu18.add(jMenu19);

        jMenu20.setText("Giới thiệu về chúng tôi");
        jMenu18.add(jMenu20);

        jMenuBar2.add(jMenu18);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                    .addComponent(txt_Phone, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                    .addComponent(txt_Birthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_Name, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdo_Male, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(rdo_Female, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)))))
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Create, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_AddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(rdo_Female, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdo_Male, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(txt_Birthday, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txt_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Create)
                            .addComponent(btn_Update)
                            .addComponent(btn_Delete)
                            .addComponent(btn_AddImage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddImageActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "gif"));
        int returnValue = chooser.showOpenDialog(rootPane);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            avatarPath = file.getAbsolutePath();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            txt_Image.setIcon(icon);
        }
    }//GEN-LAST:event_btn_AddImageActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        //   Kiểm tra nhập liệu
        if (!validateInput(txt_Name.getText(), txt_Birthday.getDate(), txt_Phone.getText(), txt_Email.getText())) {
            return;
        }
        String sql = "UPDATE HocVien SET Name = ?, Gender = ?, Birthday = ?, SDT = ?, Email = ?, Avatar = ? where MaHV = ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, txt_Name.getText());
            preparedStatement.setString(2, rdo_Male.isSelected() ? "Male" : "Female");
            preparedStatement.setDate(3, new java.sql.Date(txt_Birthday.getDate().getTime())); // ✅ ĐÚNG

            preparedStatement.setString(4, txt_Phone.getText());
            preparedStatement.setString(5, txt_Email.getText());
            preparedStatement.setString(6, avatarPath);
            preparedStatement.setString(7, txt_ID.getText());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(null, "Update error: No record found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateActionPerformed
        SignUp signUp = new SignUp();
        signUp.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_CreateActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        String sql = "Delete HocVien where maHV =?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, Integer.parseInt(txt_ID.getText()));

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(null, "Delete error: No record found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting ChuyenDe: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void tbl_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TableMouseClicked
        int selectedRow = tbl_Table.getSelectedRow(); // Lấy hàng được chọn
        if (selectedRow == -1) {
            return; // Không có dòng nào được chọn
        }

        try {
            // Lấy dữ liệu từ bảng (kiểm tra null trước khi gọi toString)
            String idStr = getValueAt(selectedRow, 0);
            int id = idStr.isEmpty() ? 0 : Integer.parseInt(idStr);
            txt_ID.setText(idStr);
            txt_Name.setText(getValueAt(selectedRow, 1));

            // Xử lý giới tính
            String gender = getValueAt(selectedRow, 2);
            if ("Male".equalsIgnoreCase(gender)) {
                rdo_Male.setSelected(true);
            } else {
                rdo_Female.setSelected(true);
            }

            // Chuyển đổi ngày tháng
            String birthdayStr = getValueAt(selectedRow, 3);
            if (!birthdayStr.isEmpty()) {
                txt_Birthday.setDate(java.sql.Date.valueOf(birthdayStr));
            }

            // Cập nhật thông tin khác
            txt_Phone.setText(getValueAt(selectedRow, 4));
            txt_Email.setText(getValueAt(selectedRow, 5));

            // Lấy đường dẫn avatar từ cơ sở dữ liệu
            String avatarPath = getAvatarPathFromDatabase(id);

            // Hiển thị ảnh trên luồng riêng (tránh lag)
            new Thread(() -> {
                if (avatarPath != null && !avatarPath.isEmpty()) {
                    setAvatarImage(txt_Image, avatarPath);
                } else {
                    txt_Image.setIcon(new ImageIcon("default-avatar.png")); // Ảnh mặc định
                }
            }).start();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lỗi chuyển đổi ID: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_tbl_TableMouseClicked

    private void LogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOutMouseClicked
        // TODO add your handling code here:
        SignIn signIn = new SignIn();
        signIn.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogOutMouseClicked

    private void quanLyCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyCDMouseClicked
        // TODO add your handling code here:
        QuanLyChuyenDe cd = new QuanLyChuyenDe();
        cd.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyCDMouseClicked

    private void quanLyKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyKHMouseClicked
        // TODO add your handling code here:
        QuanLyKhoaHoc kh = new QuanLyKhoaHoc();
        kh.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyKHMouseClicked

    private void quanLyTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyTKMouseClicked
        // TODO add your handling code here:
        QuanLyAccount acc = new QuanLyAccount();
        acc.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyTKMouseClicked

    private void quanLyHVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyHVMouseClicked
        // TODO add your handling code here:
        QuanLyHocVien hv = new QuanLyHocVien();
        hv.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyHVMouseClicked

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        dispose();
    }//GEN-LAST:event_ExitMouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu6MouseClicked

    private void LogOut1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut1MouseClicked
        // TODO add your handling code here:
        SignIn signIn = new SignIn();
        signIn.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogOut1MouseClicked

    private void Exit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit1MouseClicked
        dispose();
    }//GEN-LAST:event_Exit1MouseClicked

    private void quanLyCD1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyCD1MouseClicked
        // TODO add your handling code here:
        QuanLyChuyenDe cd = new QuanLyChuyenDe();
        cd.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyCD1MouseClicked

    private void quanLyKH1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyKH1MouseClicked
        // TODO add your handling code here:
        QuanLyKhoaHoc kh = new QuanLyKhoaHoc();
        kh.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyKH1MouseClicked

    private void quanLyTK1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyTK1MouseClicked
        // TODO add your handling code here:
        QuanLyAccount acc = new QuanLyAccount();
        acc.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyTK1MouseClicked

    private void quanLyHV1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyHV1MouseClicked
        // TODO add your handling code here:
        QuanLyHocVien hv = new QuanLyHocVien();
        hv.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyHV1MouseClicked

    private void jMenu15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu15MouseClicked
        ThongKe tk = new ThongKe();
        tk.setVisible(true);
        dispose();

    }//GEN-LAST:event_jMenu15MouseClicked

    private String getValueAt(int row, int col) {
        Object value = tbl_Table.getValueAt(row, col);
        return value == null ? "" : value.toString();
    }

    private boolean validateInput(String fullname, java.util.Date birthday, String phone, String email) {
        if (phone.isEmpty() || email.isEmpty() || fullname.isEmpty() || birthday == null) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ thông tin", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 3. Kiểm tra số điện thoại VN (Bắt đầu bằng 0, có 10 số)
        if (!phone.matches("^0\\d{9}$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0!");
            return false;
        }

        // 4. Kiểm tra email (phải có @ và đúng định dạng)
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
            return false;
        }
        return true;
    }

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
            java.util.logging.Logger.getLogger(QuanLyHocVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyHocVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Exit;
    private javax.swing.JMenu Exit1;
    private javax.swing.JMenu LogOut;
    private javax.swing.JMenu LogOut1;
    private javax.swing.JButton btn_AddImage;
    private javax.swing.JButton btn_Create;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu quanLyCD;
    private javax.swing.JMenu quanLyCD1;
    private javax.swing.JMenu quanLyHV;
    private javax.swing.JMenu quanLyHV1;
    private javax.swing.JMenu quanLyKH;
    private javax.swing.JMenu quanLyKH1;
    private javax.swing.JMenu quanLyTK;
    private javax.swing.JMenu quanLyTK1;
    private javax.swing.JRadioButton rdo_Female;
    private javax.swing.JRadioButton rdo_Male;
    private javax.swing.JTable tbl_Table;
    private com.toedter.calendar.JDateChooser txt_Birthday;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JLabel txt_Image;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JTextField txt_Phone;
    // End of variables declaration//GEN-END:variables
}
