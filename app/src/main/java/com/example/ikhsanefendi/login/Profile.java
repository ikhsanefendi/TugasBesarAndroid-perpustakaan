package com.example.ikhsanefendi.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.example.ikhsanefendi.login.Adapter.ProfileAdapter;
import com.example.ikhsanefendi.login.Models.BukuModel;
import com.example.ikhsanefendi.login.Models.ProfileModel;
import com.example.ikhsanefendi.login.Rest.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    // mendapatkan alamat server dari class ApiClient
    private ApiClient api = new ApiClient();
    // menyimpan alamat ip ke base_url
    String base_url=api.getBase_url();

    private Button back;
    ListView listView;
    List<ProfileModel> user;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listView = (ListView) findViewById(R.id.lv_profile);//untuk mengidentifikasikan bahwa
        // listView akan diarahkan ke lv_profile
        user = new ArrayList<>();
        data_user();

        back=(Button) findViewById(R.id.kembali);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Slider.class);
                startActivity(i);
            }
        });

    }


    private void data_user() {
        // membuat shared preferences yang digunakan untuk mengambil kondisi transaksi pada user tertentu
        sharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        //memasukkan session shared preferences pada variabel nis.
        String nis =  sharedPreferences.getString("nis",null);
        Toast.makeText(getApplicationContext(), nis, Toast.LENGTH_LONG).show();
        // mengunakan libraries VOlleey untuk mengambil response dari server
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                // mengambil resources dari server melalui API CLient dengan method get
                Request.Method.GET,
                base_url + "/rest/profile_android?nis="+nis,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            Toast.makeText(getApplicationContext(),
//                                    response.getString("data"), Toast.LENGTH_LONG).show();
                            //menyimpan suatu response dari server yang bernama r'esponse' ke dalam variabel string
                            String code = response.getString("response"); // memberikan respone json object "code"
                            // mengecek kondisi apabila code yang didapatkan adalah 200 atau server ditemukan
                            if (code.toString().equals("200".toString())) { // jika respone code == 200
                                // membuat objek array untuk menyimpan response dari array yang bernama 'data'
                                JSONArray dataArray = response.getJSONArray("data"); // menyimpan respone json object pada variable calonArray
                                for (int i = 0; i < dataArray.length(); i++) { // looping semua data jsonArray calonArry
                                    // membuat JSON object melalui object array yang telah dibuat
                                    JSONObject dataObject = dataArray.getJSONObject(i); //getting the json object of the particular index inside the array
                                    // menyimpan hasil object array pada Profile model yang di dalamnya terdapat setter getter
                                    ProfileModel data = new ProfileModel(dataObject.getString("nis"),dataObject.getString("nama"),dataObject.getString("ttl"),dataObject.getString("kelas"),dataObject.getString("image"));
                                    user.add(data);
                                }
                                final ProfileAdapter adapter = new ProfileAdapter(user, getApplicationContext());
                                // disini membuat tau mengelola data yang ini ditampilkan pada view atau layoutnya
                                listView.setAdapter(adapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                        ViewGroup vg = (ViewGroup) view;

                                        TextView nis = (TextView) vg.findViewById(R.id.txtnis);
                                        TextView nama = (TextView) vg.findViewById(R.id.txtnama);
                                        TextView ttl = (TextView) vg.findViewById(R.id.txtttl);
                                        TextView kelas = (TextView) vg.findViewById(R.id.txtkelas);

                                        String tampil_nis = nis.getText().toString();
                                        String tampil_nama = nama.getText().toString();
                                        String tampil_ttl = ttl.getText().toString();
                                        String tampil_kelas = kelas.getText().toString();

                                    }
                                });

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Toast.makeText(getApplicationContext(), "REST API Gagal", Toast.LENGTH_LONG).show();
                    }
                }
        );
        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}


