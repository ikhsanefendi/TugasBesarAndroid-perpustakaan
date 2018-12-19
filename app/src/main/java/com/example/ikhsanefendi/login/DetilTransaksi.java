package com.example.ikhsanefendi.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ServiceLoader;

public class DetilTransaksi extends AppCompatActivity {

    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_transaksi);

        TextView txtkode=findViewById(R.id.id_transaksi2);
        TextView txtjudul=findViewById(R.id.kode_buku2);
        TextView txtpengarang=findViewById(R.id.pinjam2);
        TextView txtkategori=findViewById(R.id.tanggal_kembali2);

        final Intent getI = getIntent();
        String id = getI.getStringExtra("id_transaksi");
        String kode = getI.getStringExtra("kode_buku");
        String pinjam = getI.getStringExtra("tanggal_pinjam");
        String kembali = getI.getStringExtra("tanggal_kembali");

        txtkode.setText(id);
        txtjudul.setText(kode);
        txtkategori.setText(pinjam);
        txtpengarang.setText(kembali);

        back=(Button) findViewById(R.id.kembali);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Slider.class);
                startActivity(i);
            }
        });

    }
}
