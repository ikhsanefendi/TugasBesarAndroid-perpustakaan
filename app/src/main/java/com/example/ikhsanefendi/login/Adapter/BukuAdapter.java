package com.example.ikhsanefendi.login.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ikhsanefendi.login.Models.BukuModel;
import com.example.ikhsanefendi.login.R;
import com.example.ikhsanefendi.login.Rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by IKHSAN EFENDI on 10/12/2018.
 */

public class BukuAdapter extends ArrayAdapter<BukuModel>{
    private List<BukuModel> buku;
    private ImageView gambar;
    // mendapatkan alamat server dari class ApiClient
    private ApiClient api = new ApiClient();
    // mendapatkan alamat gambar server dari class ApiClient
    String assets=api.getAssets();
    private Context context;

    public BukuAdapter(List<BukuModel>buku, Context context){
        super(context, R.layout.list_buku, buku);
        this.buku = buku;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.list_buku, null, true);
        TextView kode = listViewItem.findViewById(R.id.kode_buku);
        TextView judul = listViewItem.findViewById(R.id.judul);
//        TextView pengarang = listViewItem.findViewById(R.id.pengarang);
        TextView kategori = listViewItem.findViewById(R.id.kategori);
//        ImageView image = listViewItem.findViewById(R.id.image_buku);
        TextView stok = listViewItem.findViewById(R.id.stok);


        BukuModel book = buku.get(position);

//        kode.setText(book.getKode_buku());
        judul.setText(book.getJudul());
//        pengarang.setText(book.getPengarang());
        kategori.setText(book.getKategori());
        stok.setText(book.getStok());

        gambar=(ImageView) listViewItem.findViewById(R.id.image_buku); // mengisi imgAwal sesuai dengan layoutnya
        Picasso.with(context).load(assets+"assets/img/"+book.getImage()).into(gambar);//loading dari url menggunakan library Picasso
        return listViewItem;
    }


}
