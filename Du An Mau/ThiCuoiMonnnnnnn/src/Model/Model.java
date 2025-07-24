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

import java.util.Date;

/**
 *
 * @author drago
 */
public class Model {

    public int idch;
    public String tench;
    public String mota;
    public String loaihang;
    public String ngaydangky;
    public String tentt;
    public String diachi;

    public Model() {
    }

    public Model(int idch, String tench, String mota, String loaihang, String ngaydangky, String tentt, String diachi) {
        this.idch = idch;
        this.tench = tench;
        this.mota = mota;
        this.loaihang = loaihang;
        this.ngaydangky = ngaydangky;
        this.tentt = tentt;
        this.diachi = diachi;
    }

    public int getIdch() {
        return idch;
    }

    public void setIdch(int idch) {
        this.idch = idch;
    }

    public String getTench() {
        return tench;
    }

    public void setTench(String tench) {
        this.tench = tench;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLoaihang() {
        return loaihang;
    }

    public void setLoaihang(String loaihang) {
        this.loaihang = loaihang;
    }

    public String getNgaydangky() {
        return ngaydangky;
    }

    public void setNgaydangky(String ngaydangky) {
        this.ngaydangky = ngaydangky;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

}
