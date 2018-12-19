package com.example.ikhsanefendi.login.Models;

/**
 * Created by IKHSAN EFENDI on 11/12/2018.
 */

public class TransaksiModel {
    private String id_transaksi,kode_buku,tanggal_pinjam,tanggal_kembali;

    public TransaksiModel(String id_transaksi,String kode_buku,String tanggal_pinjam,String tanggal_kembali){
        this.id_transaksi=id_transaksi;
        this.kode_buku=kode_buku;
        this.tanggal_pinjam=tanggal_pinjam;
        this.tanggal_kembali=tanggal_kembali;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public String getTanggal_pinjam() {
        return tanggal_pinjam;
    }

    public String getTanggal_kembali() {
        return tanggal_kembali;
    }

}
