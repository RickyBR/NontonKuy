package com.example.nontonkuy;

public class HistoryTicket {

    String nama_film,lokasi_film,waktu_film,cinema,jumlah_tiket,id_film;
    String total_harga;

    public HistoryTicket() {
    }

    public HistoryTicket(String nama_film, String lokasi_film, String waktu_film, String cinema, String jumlah_tiket, String total_harga) {
        this.nama_film = nama_film;
        this.lokasi_film = lokasi_film;
        this.waktu_film = waktu_film;
        this.cinema = cinema;
        this.jumlah_tiket = jumlah_tiket;
        this.total_harga = total_harga;
        this.id_film = id_film;
    }

    public String getNama_film() {
        return nama_film;
    }

    public void setNama_film(String nama_film) {
        this.nama_film = nama_film;
    }

    public String getLokasi_film() {
        return lokasi_film;
    }

    public void setLokasi_film(String lokasi_film) {
        this.lokasi_film = lokasi_film;
    }

    public String getWaktu_film() {
        return waktu_film;
    }

    public void setWaktu_film(String waktu_film) {
        this.waktu_film = waktu_film;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getJumlah_tiket() {
        return jumlah_tiket;
    }

    public void setJumlah_tiket(String jumlah_tiket) {
        this.jumlah_tiket = jumlah_tiket;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }

    public String getId_film() {
        return id_film;
    }

    public void setId_film(String id_film) {
        this.id_film = id_film;
    }
}
