package com.example.ikhsanefendi.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ikhsanefendi.login.Adapter.TransaksiAdapter;
import com.example.ikhsanefendi.login.Models.BukuModel;
import com.example.ikhsanefendi.login.Models.TransaksiModel;
import com.example.ikhsanefendi.login.Rest.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransaksiActivity extends AppCompatActivity {
    // mendapatkan alamat server dari class ApiClient
    private ApiClient api = new ApiClient();
    // menyimpan alamat ip ke base_url
    String base_url=api.getBase_url();

    ListView listView;
    List<TransaksiModel> transaksi;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        // membuat shared preferences yang digunakan untuk mengambil kondisi transaksi pada user tertentu
        pref = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        //memasukkan session shared preferences pada variabel nis.
        String nis =  pref.getString("nis",null);
        listView = (ListView) findViewById(R.id.lv_transaksi);//untuk mengidentifikasikan bahwa
                                                                // listView akan diarahkan ke lv_transaksi
        transaksi = new ArrayList<>();
        setAdapter();
    }


    private void setAdapter() {
        // membuat shared preferences yang digunakan untuk mengambil kondisi transaksi pada user tertentu
        pref = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        //memasukkan session shared preferences pada variabel nis.
        String nis =  pref.getString("nis",null);
        // mengunakan libraries VOlleey untuk mengambil response dari server
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                // mengambil resources dari server melalui API CLient dengan method get
                Request.Method.GET, // metode request
                base_url + "/rest/transaksi?nis="+nis , // Base_url+statis
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //menyimpan suatu response dari server yang bernama r'esponse' ke dalam variabel string
                            String code = response.getString("response");
                            // mengecek kondisi apabila code yang didapatkan adalah 200 atau server ditemukan
                            if (code.toString().equals("200".toString())) {
                                // membuat objek array untuk menyimpan response dari array yang bernama 'data'
                                JSONArray transaksiArray = response.getJSONArray("data");
                                for (int i = 0; i < transaksiArray.length(); i++) {
                                    // membuat JSON object melalui object array yang telah dibuat
                                    JSONObject transaksiObject = transaksiArray.getJSONObject(i);
                                    // menyimpan hasil object array pada Transaksi model yang di dalamnya terdapat setter getter

                                    TransaksiModel list_transaksi = new TransaksiModel(transaksiObject.getString("id_transaksi"), transaksiObject.getString("kode_buku"), transaksiObject.getString("tanggal_pinjam"), transaksiObject.getString("tanggal_kembali"));
                                    transaksi.add(list_transaksi);
                                }

                                final TransaksiAdapter adapter = new TransaksiAdapter(transaksi, getApplicationContext());
                                // disini membuat tau mengelola data yang ini ditampilkan pada view atau layoutnya
                                listView.setAdapter(adapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                        ViewGroup vg = (ViewGroup) view;

                                        TextView id = (TextView) vg.findViewById(R.id.id_transaksi);
//                                        TextView kode = (TextView) vg.findViewById(R.id.kode_buku);
//                                        TextView pinjam = (TextView) vg.findViewById(R.id.tanggal_pinjam);
//                                        TextView kembali = (TextView) vg.findViewById(R.id.tanggal_kembali);

                                        //menyimpan hasil dari yang ada disimpan pada view group
                                        final String id_list = id.getText().toString();
//                                        String kode_list = kode.getText().toString();
//                                        String pinjam_list = pinjam.getText().toString();
//                                        String kembali_list = kembali.getText().toString();
                                    }
                                });

                            }
                            // membuat action agar list view dapat di klik
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    // memberikan value atau nilai kepada layout lainnya yaitu pada layout details transaksi
                                    Intent i= new Intent(TransaksiActivity.this, DetilTransaksi.class);
                                    i.putExtra("id_transaksi", transaksi.get(position).getId_transaksi());
                                    i.putExtra("kode_buku", transaksi.get(position).getKode_buku());
                                    i.putExtra("tanggal_pinjam", transaksi.get(position).getTanggal_pinjam());
                                    i.putExtra("tanggal_kembali", transaksi.get(position).getTanggal_kembali());

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
        // menambahkan jsonObjectRequest pada objek dari requestQueue
        requestQueue.add(jsonObjectRequest);
    }
}
