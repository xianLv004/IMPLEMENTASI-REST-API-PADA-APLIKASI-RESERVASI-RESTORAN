package com.example.myapplication.ui.main.data;

public class qrData {

    private String code;

    public qrData(String code, String gambar) {
        this.code = code;
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    private String gambar;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
