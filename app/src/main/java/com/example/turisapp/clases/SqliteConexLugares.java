package com.example.turisapp.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteConexLugares extends SQLiteOpenHelper {

    private static final String nombre_bd = "turisapp.db";

    public SqliteConexLugares(@Nullable Context c)
    {
        super(c, nombre_bd, null, 2);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE lugares (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT NOT NULL, departamento TEXT NOT NULL, municipio TEXT NOT NULL, latitud TEXT NOT NULL, longitud TEXT NOT NULL, tipo INT NOT NULL, presupuesto INT NOT NULL, comentarios TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE lugares");
    }

}
