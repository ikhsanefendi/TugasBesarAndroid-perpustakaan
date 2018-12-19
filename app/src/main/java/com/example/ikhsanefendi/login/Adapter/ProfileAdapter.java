package com.example.ikhsanefendi.login.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;


import com.example.ikhsanefendi.login.Models.ProfileModel;
import com.example.ikhsanefendi.login.R;
import com.example.ikhsanefendi.login.Rest.ApiClient;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by IKHSAN EFENDI on 12/12/2018.
 */

public class ProfileAdapter extends ArrayAdapter<ProfileModel>{

    // mendapatkan alamat server dari class ApiClient
    private ApiClient api = new ApiClient();
    // mendapatkan alamat gambar server dari class ApiClient
    String assets=api.getAssets();
    private List<ProfileModel>user;
    private ImageView gambar;

    private Context context;

    public ProfileAdapter(List<ProfileModel>user, Context context){
        super(context, R.layout.activity_profile, user);
        this.user = user;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.activity_profile, null, true);

        //menciptakan data ke view melalui id idnya
        TextView nis = listViewItem.findViewById(R.id.txtnis);
        TextView nama = listViewItem.findViewById(R.id.txtnama);
        TextView ttl = listViewItem.findViewById(R.id.txtttl);
        TextView kelas = listViewItem.findViewById(R.id.txtkelas);

        ProfileModel data = user.get(position);

        //memberikan nilai pada text view
        nis.setText(data.getNis());
        nama.setText(data.getNama());
        ttl.setText(data.getTtl());
        kelas.setText(data.getKelas());

        gambar=(ImageView) listViewItem.findViewById(R.id.profile); // mengisi gambar sesuai dengan id pada  layoutnya
        Picasso.with(context).load(assets+"assets/img/anggota/"+data.getImage()).into(gambar);//loading dari url server menggunakan library Picasso
        //yaitu mengambil gambar pada alamat url_assets+ value yang ada pada folder img/ anggota dengan nama file tertentu.



        return listViewItem;


    }


}
