package com.example.ikhsanefendi.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ikhsanefendi.login.Rest.ApiClient;
import com.squareup.picasso.Picasso;


public class DetilBuku extends AppCompatActivity {

    private ImageView gambar;
    private ApiClient api = new ApiClient();
    String assets=api.getAssets();

    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_buku);

        TextView txtkode=findViewById(R.id.kode_buku2);
        TextView txtjudul=findViewById(R.id.judul2);
        TextView txtpengarang=findViewById(R.id.pengarang2);
        TextView txtkategori=findViewById(R.id.kategori2);
        TextView txtstok=findViewById(R.id.stok2);

        gambar=(ImageView) findViewById(R.id.buku); // mengisi imgAwal sesuai dengan layoutnya



        Intent getI = getIntent();
        String kode = getI.getStringExtra("kode_buku");
        String judul = getI.getStringExtra("judul");
        String pengarang = getI.getStringExtra("pengarang");
        String kategori = getI.getStringExtra("kategori");
        String stok = getI.getStringExtra("stok");
        String image = getI.getStringExtra("image");
        Picasso.with(this).load(assets+"assets/img/"+image).into(gambar);//loading dari url menggunakan library Picasso ,


        txtkode.setText(kode);
        txtjudul.setText(judul);
        txtkategori.setText(kategori);
        txtpengarang.setText(pengarang);
        txtstok.setText(stok);


        back= (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Slider.class);
                startActivity(i);
            }
        });
    }
}
