package com.example.ikhsanefendi.login.Rest;

/**
 * Created by IKHSAN EFENDI on 12/12/2018.
 */

public class ApiClient {

    // memberikan alamat ip server yang menyediakan REST API
    private String base_url="http://192.168.0.102:8080/perpus-server/index.php";
    // memberikan alamat url yang digunakan untuk mengambil assets gambar
    private String assets="http://192.168.0.102:8080/perpus-server/";

    public ApiClient(){
    }

    public String getAssets() {
        return assets;
    }

    public String getBase_url() {
        return base_url;
    }

}
