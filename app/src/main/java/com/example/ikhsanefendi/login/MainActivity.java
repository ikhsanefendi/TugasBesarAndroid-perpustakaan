package com.example.ikhsanefendi.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private ApiClient api = new ApiClient();
    String base_url=api.getBase_url();

    EditText edtUsername,edtPassword;
    Button buttonLogin;
    DatabaseHelper dbHelper;
    private SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String nis =  sharedpreferences.getString("nis",null);

        if(nis!=null){
            goToActivity();
        }

        edtUsername = findViewById(R.id.editusername);
        edtPassword = findViewById(R.id.editpassword);
        buttonLogin = findViewById(R.id.btnLogin);
        dbHelper = new DatabaseHelper(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (isValidUsername(username)){
                    edtUsername.setError("Username tidak boleh kosong");
                }else if(!isValidPassword(password)) {
                    edtPassword.setError("Password tidak boleh kosong");
                }else{
                    login();
                }
            }
        });
    }

    private void goToActivity(){
        Intent mIntent = new Intent(getApplicationContext(), Slider.class);
        startActivity(mIntent);
    }


    private boolean isValidUsername(String username) {
        if (!TextUtils.isEmpty(username)&&username.length()>3){
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String password){
        if (!TextUtils.isEmpty(password)&&password.length()>3){
            return true;
        }
        return false;
    }



    private void login() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                // mengambil resources dari server melalui API CLient dengan method get
                Request.Method.GET, // fungsi get pada REST Client
                base_url + "/rest/proses_android?nis="+edtUsername.getText().toString()+"&pass="+edtPassword.getText().toString() , // url request
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //menyimpan suatu response dari server yang bernama r'esponse' ke dalam variabel string
                            String code = response.getString("response");
                            // mengecek kondisi apabila code yang didapatkan adalah 200 atau server ditemukan
                            if (code.toString().equals("201".toString())) {
                                // menyimpan resources dari server yang bernama session ke dalam String session
                                String session = response.getString("session");
                                // membuat object shared preferences
                                sharedpreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
                                // agar sharedpreferences dapat diedit,dihapus, dan di isi
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                //  membuat shared preferences nis berisikan session
                                editor.putString("nis",session);
                                editor.commit();
                                // kalau terdapat username dan password cocok lalu akan termasuk login
                                goToActivity();

                            }
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
