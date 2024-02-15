package com.example.myapplication.ui.main.data;

public class Menu
{
    private int id_menu;
    private String nama;
    private String status;
    private String kode_menu;
    private int harga;
    private String gambar;

    public Menu(int id_menu, String nama, String status, String kode_menu, int harga, String gambar) {
        this.id_menu = id_menu;
        this.nama = nama;
        this.status = status;
        this.kode_menu = kode_menu;
        this.harga = harga;
        this.gambar = gambar;
    }


    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKode_menu() {
        return kode_menu;
    }

    public void setKode_menu(String kode_menu) {
        this.kode_menu = kode_menu;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}

