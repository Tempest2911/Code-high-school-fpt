
import java.util.ArrayList;
import javax.swing.JOptionPane;
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

    ArrayList<Model> modelSV = new ArrayList<>();
    ServiceSV SV = new ServiceSV();

    public View() {
        initComponents();
        SV.autoAdd();
        fillTableData(SV.getAll());

    }

    public Model readForm() {
        int id = Integer.parseInt(txtMaSV.getText());
        String ten = txtName.getText();
        float TiengAnh = Float.parseFloat(txtTA.getText());
        float TinHoc = Float.parseFloat(txtTinHoc.getText());
        float TC = Float.parseFloat(txtTC.getText());
        float diemTB = (TC+TiengAnh+TinHoc)/3;

        return new Model(id, ten, TiengAnh, TinHoc, TC, diemTB);
    }

    public void fillTableData(ArrayList<Model> m) {
        DefaultTableModel model = (DefaultTableModel) tblSV.getModel();
        model.setRowCount(0);
        for (Model model1 : m) {
            model.addRow(new Object[]{model1.getMaSV(), model1.getHoTen(), model1.getTiengAnh(), model1.getTinHoc(), model1.getGiaoDucTC(), model1.getDiemTB()});
        }
        tblSV.setModel(model);
    }

    private void updateTextFields(int row) {
        txtMaSV.setText(tblSV.getValueAt(row, 0).toString());
        txtName.setText(tblSV.getValueAt(row, 1).toString());
        txtTA.setText(tblSV.getValueAt(row, 2).toString());
        txtTinHoc.setText(tblSV.getValueAt(row, 3).toString());
        txtTC.setText(tblSV.getValueAt(row, 4).toString());
        lbldiemTB.setText(tblSV.getValueAt(row, 5).toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSV = new javax.swing.JTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        btnTop = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnBottom = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        label8 = new java.awt.Label();
        txtFindmaSV = new java.awt.TextField();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtName = new java.awt.TextField();
        txtMaSV = new java.awt.TextField();
        label9 = new java.awt.Label();
        txtTA = new java.awt.TextField();
        label10 = new java.awt.Label();
        txtTinHoc = new java.awt.TextField();
        label11 = new java.awt.Label();
        txtTC = new java.awt.TextField();
        label12 = new java.awt.Label();
        label13 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        lbldiemTB = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 51, 204));
        label1.setText("QUẢN LÝ ĐIỂM SINH VIÊN");

        tblSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SV", "Họ và tên", "Tiếng Anh", "Tin Học", "Giáo dục TC", "DiemTB"
            }
        ));
        tblSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSV);

        btnTop.setBackground(new java.awt.Color(0, 255, 255));
        btnTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1.png"))); // NOI18N
        btnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopActionPerformed(evt);
            }
        });

        btnPrevious.setBackground(new java.awt.Color(0, 0, 255));
        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/3.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(255, 0, 51));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/2.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnBottom.setBackground(new java.awt.Color(0, 255, 51));
        btnBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/4.png"))); // NOI18N
        btnBottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBottomActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(255, 255, 153));
        btnNew.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnNew.setForeground(new java.awt.Color(51, 153, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cat FREAKY.gif"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(102, 255, 102));
        btnDelete.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(51, 153, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ambatukam.gif"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(153, 255, 255));
        btnUpdate.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(51, 153, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ditto.gif"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label8.setText("Mã SV:");

        txtFindmaSV.setBackground(new java.awt.Color(86, 176, 255));
        txtFindmaSV.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        btnSearch.setBackground(new java.awt.Color(153, 153, 255));
        btnSearch.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 51, 51));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shockthuoc.gif"))); // NOI18N
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
                .addGap(82, 82, 82)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFindmaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnSearch)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFindmaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtName.setBackground(new java.awt.Color(255, 255, 204));
        txtName.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        txtMaSV.setBackground(new java.awt.Color(204, 255, 204));
        txtMaSV.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        label9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label9.setText("Mã SV:");

        txtTA.setBackground(new java.awt.Color(255, 204, 204));
        txtTA.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTAKeyReleased(evt);
            }
        });

        label10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label10.setText("Tiếng Anh");

        txtTinHoc.setBackground(new java.awt.Color(204, 204, 255));
        txtTinHoc.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTinHoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTinHocKeyReleased(evt);
            }
        });

        label11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label11.setText("Tin học:");

        txtTC.setBackground(new java.awt.Color(204, 255, 255));
        txtTC.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTCKeyReleased(evt);
            }
        });

        label12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label12.setText("Giáo dục TC:");

        label13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label13.setText("Họ và tên:");

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Điểm TB:");

        lbldiemTB.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lbldiemTB.setForeground(new java.awt.Color(51, 51, 255));
        lbldiemTB.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTinHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTC, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTA, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(lbldiemTB)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtTinHoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbldiemTB))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        btnSave.setBackground(new java.awt.Color(255, 153, 153));
        btnSave.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        btnSave.setForeground(new java.awt.Color(51, 153, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ezgif-4-3142f4933d.gif"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTop, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(139, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(940, 940, 940))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(btnNew)
                        .addGap(4, 4, 4)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        if (tblSV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        String idSearchText = txtFindmaSV.getText();
        if (idSearchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ban chua dien ma ID de tim");
            return;
        }

        int IDSearch = Integer.parseInt(txtFindmaSV.getText());

        for (int i = 0; i < tblSV.getRowCount(); i++) {

            int IDInTable = Integer.parseInt(tblSV.getValueAt(i, 0).toString());

            if (IDInTable == IDSearch) {
                txtMaSV.setText(String.valueOf(IDInTable));
                txtName.setText(tblSV.getValueAt(i, 1).toString());
                txtTA.setText(tblSV.getValueAt(i, 2).toString());
                txtTinHoc.setText(tblSV.getValueAt(i, 3).toString());
                txtTC.setText(tblSV.getValueAt(i, 4).toString());
            }
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtMaSV.setText(null);
        txtName.setText(null);
        txtTA.setText(null);
        txtTinHoc.setText(null);
        txtTC.setText(null);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (txtMaSV.getText().isEmpty() || txtName.getText().isEmpty() || txtTA.getText().isEmpty() || txtTinHoc.getText().isEmpty() || txtTC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ban chua dien hoac dien thieu thong tin");
            return;
        }

        SV.add(readForm());
        fillTableData(SV.getAll());
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtMaSV.getText().isEmpty() || txtName.getText().isEmpty() || txtTA.getText().isEmpty() || txtTinHoc.getText().isEmpty() || txtTC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phai dien thong tin trc khi delete");
            return;
        }

        SV.delete(readForm().getMaSV());
        fillTableData(SV.getAll());
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (txtMaSV.getText().isEmpty() || txtName.getText().isEmpty() || txtTA.getText().isEmpty() || txtTinHoc.getText().isEmpty() || txtTC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phai dien thong tin trc khi update");
            return;
        }

        SV.update(readForm());
        fillTableData(SV.getAll());
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSVMouseClicked
        int selectedIndex = tblSV.getSelectedRow();
        txtMaSV.setText(tblSV.getValueAt(selectedIndex, 0).toString());
        txtName.setText(tblSV.getValueAt(selectedIndex, 1).toString());
        txtTA.setText(tblSV.getValueAt(selectedIndex, 2).toString());
        txtTinHoc.setText(tblSV.getValueAt(selectedIndex, 3).toString());
        txtTC.setText(tblSV.getValueAt(selectedIndex, 4).toString());

        float TiengAnh = Float.parseFloat(txtTA.getText());
        float TinHoc = Float.parseFloat(txtTinHoc.getText());
        float TC = Float.parseFloat(txtTC.getText());

        float DiemTB = (TiengAnh + TinHoc + TC) / 3;

        lbldiemTB.setText(String.format("%.1f", DiemTB));


    }//GEN-LAST:event_tblSVMouseClicked

    private void btnBottomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBottomActionPerformed
        if (tblSV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        int lastRow = tblSV.getRowCount() - 1;
        tblSV.setRowSelectionInterval(lastRow, lastRow);
        updateTextFields(lastRow);

        float TiengAnh = Float.parseFloat(txtTA.getText());
        float TinHoc = Float.parseFloat(txtTinHoc.getText());
        float TC = Float.parseFloat(txtTC.getText());

        float DiemTB = (TiengAnh + TinHoc + TC) / 3;

        lbldiemTB.setText(String.format("%.1f", DiemTB));

    }//GEN-LAST:event_btnBottomActionPerformed

    private void txtTAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTAKeyReleased

    }//GEN-LAST:event_txtTAKeyReleased

    private void txtTinHocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTinHocKeyReleased

    }//GEN-LAST:event_txtTinHocKeyReleased

    private void txtTCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTCKeyReleased

    }//GEN-LAST:event_txtTCKeyReleased

    private void btnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopActionPerformed
        if (tblSV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        tblSV.setRowSelectionInterval(0, 0);
        updateTextFields(0);

        float TiengAnh = Float.parseFloat(txtTA.getText());
        float TinHoc = Float.parseFloat(txtTinHoc.getText());
        float TC = Float.parseFloat(txtTC.getText());

        float DiemTB = (TiengAnh + TinHoc + TC) / 3;

        lbldiemTB.setText(String.format("%.1f", DiemTB));
    }//GEN-LAST:event_btnTopActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (tblSV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        int selectedRow = tblSV.getSelectedRow();
        if (selectedRow < tblSV.getRowCount() - 1) {
            tblSV.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
            updateTextFields(selectedRow + 1);
        } else {
            tblSV.setRowSelectionInterval(0, 0);
            updateTextFields(0);
        }

        float TiengAnh = Float.parseFloat(txtTA.getText());
        float TinHoc = Float.parseFloat(txtTinHoc.getText());
        float TC = Float.parseFloat(txtTC.getText());

        float DiemTB = (TiengAnh + TinHoc + TC) / 3;

        lbldiemTB.setText(String.format("%.1f", DiemTB));
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (tblSV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chua co thong tin SV trong bang");
            return;
        }

        int selectedRow = tblSV.getSelectedRow();
        if (selectedRow > 0) {
            tblSV.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
            updateTextFields(selectedRow - 1);
        } else {
            int lastRow = tblSV.getRowCount() - 1;
            tblSV.setRowSelectionInterval(lastRow, lastRow);
            updateTextFields(lastRow);
        }

        float TiengAnh = Float.parseFloat(txtTA.getText());
        float TinHoc = Float.parseFloat(txtTinHoc.getText());
        float TC = Float.parseFloat(txtTC.getText());

        float DiemTB = (TiengAnh + TinHoc + TC) / 3;

        lbldiemTB.setText(String.format("%.1f", DiemTB));
    }//GEN-LAST:event_btnPreviousActionPerformed

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
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTop;
    private javax.swing.JButton btnUpdate;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel lbldiemTB;
    private javax.swing.JTable tblSV;
    private java.awt.TextField txtFindmaSV;
    private java.awt.TextField txtMaSV;
    private java.awt.TextField txtName;
    private java.awt.TextField txtTA;
    private java.awt.TextField txtTC;
    private java.awt.TextField txtTinHoc;
    // End of variables declaration//GEN-END:variables
}
