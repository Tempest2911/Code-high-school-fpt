package com.mycompany.entity;

public class TestQuestion {
    private String cauHoi;
    private String dapAnDung;

    public TestQuestion() {
    }

    // Constructor
    public TestQuestion(String cauHoi, String dapAnDung) {
        this.cauHoi = cauHoi;
        this.dapAnDung = dapAnDung;
    }

    // Getter & Setter
    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    // Hiển thị câu hỏi (không tiết lộ đáp án đúng)
    @Override
    public String toString() {
        return "Câu hỏi: " + cauHoi;
    }
}
