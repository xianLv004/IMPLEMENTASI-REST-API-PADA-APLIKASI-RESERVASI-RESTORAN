package com.example.myapplication.ui.main.data;

public class data {
    private String tanggal_reservasi, jam_reservasi, status;

    public data() {
    }



    public data(String tanggal_reservasi, String jam_reservasi, String status){
        this.tanggal_reservasi = tanggal_reservasi;
        this.jam_reservasi = jam_reservasi;
        this.status = status;


    }

    public String getTanggal_reservasi() {
        return tanggal_reservasi;
    }

    public void setTanggal_reservasi(String tanggal_reservasi) {
        this.tanggal_reservasi = tanggal_reservasi;
    }

    public String getJam_reservasi() {
        return jam_reservasi;
    }

    public void setJam_reservasi(String jam_reservasi) {
        this.jam_reservasi = jam_reservasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
