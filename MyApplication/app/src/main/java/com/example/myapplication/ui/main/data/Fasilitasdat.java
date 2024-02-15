package com.example.myapplication.ui.main.data;

public class Fasilitasdat {
    private int id_fas;
    private String nama_fas;
    private String ket_fas;
    private String 	gambar;

    public Fasilitasdat(int id_fas, String nama_fas, String ket_fas, String gambar) {
        this.id_fas = id_fas;
        this.nama_fas = nama_fas;
        this.ket_fas = ket_fas;
        this.gambar = gambar;
    }

    public int getId_fas() {
        return id_fas;
    }

    public String getNama_fas() {
        return nama_fas;
    }

    public String getKet_fas() {
        return ket_fas;
    }

    public String getGambar() {
        return gambar;
    }
}
