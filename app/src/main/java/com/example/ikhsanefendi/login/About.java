package com.example.ikhsanefendi.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class About extends AppCompatActivity {

    private ImageView gambar;
    private ImageView instagram, fb, gmail, maps;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        instagram = (ImageView) findViewById(R.id.instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.instagram.com");
                Intent i = new Intent(Intent.ACTION_VIEW, uri); //halaman yang dituju adalah nilai
                startActivity(i);
            }
        });

        fb = (ImageView) findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.facebook.com");
                Intent i = new Intent(Intent.ACTION_VIEW, uri); //halaman yang dituju adalah nilai
                startActivity(i);
            }
        });

        gmail = (ImageView) findViewById(R.id.gmail);
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.gmail.com");
                Intent i = new Intent(Intent.ACTION_VIEW, uri); //halaman yang dituju adalah nilai
                startActivity(i);
            }
        });

        maps = (ImageView) findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), LocationActivity.class); //halaman yang dituju adalah nilai
                startActivity(i);
            }
        });

        back= (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Slider.class);
                startActivity(i);
            }
        });

    }


}
