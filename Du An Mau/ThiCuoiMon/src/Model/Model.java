/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//        public void FillTableData(ArrayList<Model> m) {
//        DefaultTableModel defaultTableModel = (DefaultTableModel) tblTable.getModel();
//        defaultTableModel.setRowCount(0);
//        int num = 1;
//        for (Model model : m) {
//            defaultTableModel.addRow(new Object[]{
//                num++,
//                model.idch,
//                model.tench,
//                model.mota,
//                model.loaihang,
//                model.ngaydangky,
//                model.tentt,
//                model.diachi
//            });
//        }
//    }
package Model;

/**
 *
 * @author drago
 */
public class Model {

    public int idsv;
    public String tensv;
    public String email;
    public String phone;
    public String address;
    public String tenph;
    public String nghePH;

    public Model() {
    }

    public Model(int idsv, String tensv, String email, String phone, String address, String tenph, String nghePH) {
        this.idsv = idsv;
        this.tensv = tensv;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.tenph = tenph;
        this.nghePH = nghePH;
    }

    public int getIdsv() {
        return idsv;
    }

    public void setIdsv(int idsv) {
        this.idsv = idsv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTenph() {
        return tenph;
    }

    public void setTenph(String tenph) {
        this.tenph = tenph;
    }

    public String getNghePH() {
        return nghePH;
    }

    public void setNghePH(String nghePH) {
        this.nghePH = nghePH;
    }

    
}
