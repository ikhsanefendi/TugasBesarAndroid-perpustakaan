package com.example.ikhsanefendi.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Slider extends AppCompatActivity {
    private SliderLayout sliderLayout;
    private FrameLayout frame1,profile,transaksi,about;
    private ImageView buku;
    private TextView logout;
    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor mEditor;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        sharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String nis =  sharedPreferences.getString("nis",null);
        Toast.makeText(getApplicationContext(),nis,Toast.LENGTH_LONG).show();

//        Toast.makeText(getApplicationContext(),sharedPreferences.getString("nis",null),Toast.LENGTH_LONG).show();

        frame1=(FrameLayout) findViewById(R.id.frame1); //menyatakan bahwa id Button ButtonProfil disimpan pada object myProfil
        frame1.setOnClickListener(new View.OnClickListener() {// Method agar myProfile dapat melakukan pekerjaan saat di klik
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),BukuActivity.class);//bahwa halaman yang dituju adalah Profil.class
                startActivity(i);
            }
        });

        about=(FrameLayout) findViewById(R.id.frame_about); //menyatakan bahwa id Button ButtonProfil disimpan pada object myProfil
        about.setOnClickListener(new View.OnClickListener() {// Method agar myProfile dapat melakukan pekerjaan saat di klik
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),About.class);//bahwa halaman yang dituju adalah Profil.class
                startActivity(i);
            }
        });

        profile=(FrameLayout) findViewById(R.id.frame_profile); //menyatakan bahwa id Button ButtonProfil disimpan pada object myProfil
        profile.setOnClickListener(new View.OnClickListener() {// Method agar myProfile dapat melakukan pekerjaan saat di klik
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Profile.class);//bahwa halaman yang dituju adalah Profil.class
                startActivity(i);
            }
        });

        transaksi=(FrameLayout) findViewById(R.id.frame_transaksi); //menyatakan bahwa id Button ButtonProfil disimpan pada object myProfil
        transaksi.setOnClickListener(new View.OnClickListener() {// Method agar myProfile dapat melakukan pekerjaan saat di klik
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),TransaksiActivity.class);//bahwa halaman yang dituju adalah Profil.class
                startActivity(i);
            }
        });


        logout = (TextView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {// Method agar myProfile dapat melakukan pekerjaan saat di klik
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        // Load image dari URL
        HashMap<String,String> url_maps = new HashMap<String, String>();
//        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
//        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
//        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        // Load Image Dari res/drawable
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Perpustakaan SMAN 2 Sidoarjo",R.drawable.perpus2);
        file_maps.put("Kumpulan Buku Terbaru",R.drawable.perpus3);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);


//        String imageUri = "http://senpuu.com.br/wp-content/uploads/2013/03/1172317494990.jpg"; // mengakses ke gambar dengan url tersebur
//        buku=(ImageView) findViewById(R.id.img_buku); // mengisi imgAwal sesuai dengan layoutnya
//        Picasso.with(this).load(imageUri).into(buku);//loading dari url menggunakan library Picasso ,
//        // jadi gambar di dapat dari url secaara online, hal ini juga dapat membuat
//        // aplikasi kita menjadi lebih ringan karena sebagian gambar terdapat
//        // pada url online


    }
    private void logout(){
        mEditor = sharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
        //User Logged in Successfully Launch You home screen activity
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}