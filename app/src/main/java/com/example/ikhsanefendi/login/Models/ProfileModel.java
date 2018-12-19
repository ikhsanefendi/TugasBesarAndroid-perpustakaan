package com.example.ikhsanefendi.login.Models;

/**
 * Created by IKHSAN EFENDI on 12/12/2018.
 */

public class ProfileModel {

    private String nis,nama,ttl,kelas,image;

    public ProfileModel(String nis,String nama,String ttl,String kelas,String image){
        this.nis=nis;
        this.nama=nama;
        this.ttl=ttl;
        this.kelas=kelas;
        this.image=image;
    }

    public String getNis() {
        return nis;
    }

    public String getNama() {
        return nama;
    }

    public String getTtl() {
        return ttl;
    }

    public String getKelas() {
        return kelas;
    }

    public String getImage() {
        return image;
    }


}
