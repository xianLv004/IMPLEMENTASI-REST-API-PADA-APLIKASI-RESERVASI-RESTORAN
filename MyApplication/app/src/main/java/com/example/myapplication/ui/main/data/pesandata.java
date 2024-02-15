package com.example.myapplication.ui.main.data;

public class pesandata {
    private String tanggal_reservasi, jam_reservasi, status;
    private int id_user;

    public pesandata() {
        this.tanggal_reservasi = tanggal_reservasi;
        this.jam_reservasi = jam_reservasi;
        this.status = status;
        this.id_user = id_user;
    }

    public String getTanggal_reservasi() {
        return tanggal_reservasi;
    }

    public String getJam_reservasi() {
        return jam_reservasi;
    }

    public String getStatus() {
        return status;
    }

    public int getId_user() {
        return id_user;
    }

    public String setTanggal_reservasi(String tanggal_reservasi) {
        this.tanggal_reservasi = tanggal_reservasi;
        return null;
    }

    public String setJam_reservasi(String jam_reservasi) {
        this.jam_reservasi = jam_reservasi;
        return null;
    }

    public String setStatus(String status) {
        this.status = status;
        return null;
    }

    public String setId_user(String id_user) {
        return null;
    }
}
