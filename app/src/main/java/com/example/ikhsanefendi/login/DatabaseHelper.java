package com.example.ikhsanefendi.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by IKHSAN EFENDI on 04/12/2018.
 */

public class DatabaseHelper  extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="project.db";
    private static final int DATABASE_VERSION=1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String user= "create table user(idUser integer primary key autoincrement, username text, email text, password text);";
//        Log.d("Data", "onCreate: "+user);
//        db.execSQL(user);
//        //membuat table user
//
//        //menambarkan record di table user
//        String sql="Insert into user(idUser, username, email, password) values('1', 'joko', 'joko@gmail.com', 'qwerty');";
//        db.execSQL(sql);


        String sql= "create table anggota(id_anggota integer primary key autoincrement, nis text, password text, nama text, kelas text);";
        Log.d("Data", "onCreate: "+sql);
        db.execSQL(sql);

//        sql = "INSERT INTO anggota(nis,password,nama,kelas) VALUES ('http://192.168.0.101:8080/perpus-server/index.php');";
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
