package com.example.ikhsanefendi.login.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ikhsanefendi.login.Models.BukuModel;
import com.example.ikhsanefendi.login.Models.TransaksiModel;
import com.example.ikhsanefendi.login.R;

import java.util.List;

/**
 * Created by IKHSAN EFENDI on 11/12/2018.
 */

public class TransaksiAdapter extends ArrayAdapter<TransaksiModel> {
    private List<TransaksiModel> transaksi;

    private Context context;

    public TransaksiAdapter(List<TransaksiModel>transaksi, Context context){
        super(context, R.layout.list_transaksi, transaksi);
        this.transaksi = transaksi;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        //
        View listViewItem = inflater.inflate(R.layout.list_transaksi, null, true);

        //menciptakan data ke view melalui id idnya
        TextView id = listViewItem.findViewById(R.id.id_transaksi);
//        TextView kode = listViewItem.findViewById(R.id.kode_buku);
//        TextView pinjam = listViewItem.findViewById(R.id.tanggal_pinjam);
//        TextView kembali = listViewItem.findViewById(R.id.tanggal_kembali);

        TransaksiModel list = transaksi.get(position);

        //memberikan nilai pada text view
        id.setText(list.getId_transaksi());
//        kode.setText(list.getKode_buku());
//        pinjam.setText(list.getTanggal_pinjam());
//        kembali.setText(list.getTanggal_kembali());

        return listViewItem;
    }
}

