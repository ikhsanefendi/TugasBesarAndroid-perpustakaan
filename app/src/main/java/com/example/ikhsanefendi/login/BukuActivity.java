package com.example.ikhsanefendi.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ikhsanefendi.login.Adapter.BukuAdapter;
import com.example.ikhsanefendi.login.Models.BukuModel;
import com.example.ikhsanefendi.login.Rest.ApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BukuActivity extends AppCompatActivity {
    // mendapatkan alamat server dari class ApiClient
    private ApiClient api = new ApiClient();
    // menyimpan alamat ip ke base_url
    String base_url=api.getBase_url();
    ListView listView;
    List<BukuModel> buku;
    private ImageView gambar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku);

        listView = (ListView) findViewById(R.id.lv_buku);
        buku = new ArrayList<>();
        initviews();
    }

    private void initviews() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                // mengambil resources dari server melalui API CLient dengan method get
                Request.Method.GET,
                base_url + "/rest/buku" ,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //menyimpan suatu response dari server yang bernama response' ke dalam variabel string
                            String code = response.getString("response");
                            // mengecek kondisi apabila code yang didapatkan adalah 200 atau server ditemukan
                            if (code.toString().equals("200".toString())) {
                                // membuat objek array untuk menyimpan response dari array yang bernama 'data'
                                JSONArray bukuArray = response.getJSONArray("data");

                                for (int i = 0; i < bukuArray.length(); i++) {
                                    // membuat JSON object melalui object array yang telah dibuat
                                    JSONObject bukuObject = bukuArray.getJSONObject(i); //parameter harus sama
                                    // menyimpan hasil object array pada Transaksi model yang di dalamnya terdapat setter getter
                                    BukuModel book = new BukuModel(bukuObject.getString("kode_buku"), bukuObject.getString("judul"), bukuObject.getString("pengarang"), bukuObject.getString("kategori"), bukuObject.getString("stok"), bukuObject.getString("image"));
                                    buku.add(book);
                                }
                                final BukuAdapter adapter = new BukuAdapter(buku, getApplicationContext());
                                // disini membuat tau mengelola data yang ini ditampilkan pada view atau layoutnya
                                listView.setAdapter(adapter);

                                // membuat action agar list view dapat di klik
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                        ViewGroup vg = (ViewGroup) view;

                                        TextView judul = (TextView) vg.findViewById(R.id.judul);
//                                        TextView pengarang = (TextView) vg.findViewById(R.id.pengarang);
                                        TextView kategori = (TextView) vg.findViewById(R.id.kategori);
                                        TextView stok = (TextView) vg.findViewById(R.id.stok);

                                        final String judulDetails = judul.getText().toString();
//                                        String pengarangDetails = pengarang.getText().toString();
                                        String kategoriDetails = kategori.getText().toString();
                                        String stokDetails = stok.getText().toString();
                                    }
                                });



                            }
                            // membuat action agar list view dapat di klik
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    // memberikan value atau nilai kepada layout lainnya yaitu pada layout details transaksi
                                    Intent i= new Intent(BukuActivity.this, DetilBuku.class);
                                    i.putExtra("judul", buku.get(position).getJudul());
                                    i.putExtra("pengarang", buku.get(position).getPengarang());
                                    i.putExtra("kategori", buku.get(position).getKategori());
                                    i.putExtra("stok", buku.get(position).getStok());
                                    i.putExtra("kode_buku", buku.get(position).getKode_buku());
                                    i.putExtra("image", buku.get(position).getImage());


                                    startActivity(i);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // mengecek value apakah REST terhadap server telah terhubung atau tidak
                        Toast.makeText(getApplicationContext(), "REST API Gagal", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

}