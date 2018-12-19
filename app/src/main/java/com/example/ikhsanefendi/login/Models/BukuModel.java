package com.example.ikhsanefendi.login.Models;


public class BukuModel {

    private String kode_buku,judul,pengarang, kategori,stok,image;

    public BukuModel(String kode_buku,String judul, String pengarang, String kategori,String stok,String image){
        this.kode_buku=kode_buku;
        this.judul=judul;
        this.pengarang=pengarang;
        this.kategori=kategori;
        this.stok=stok;
        this.image=image;
    }


    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKategori() {
        return kategori;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public String getJudul() {
        return judul;
    }

    public String getStok() {
        return stok;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getImage() {
        return image;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }



    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
