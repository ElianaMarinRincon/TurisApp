package com.example.turisapp.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteConex extends SQLiteOpenHelper {

    private static final String nombre_bd = "turisapp.db";

    public SqliteConex(@Nullable Context c)
    {
        super(c, nombre_bd, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombres TEXT NOT NULL, apellidos TEXT NOT NULL, fechaNacimiento TEXT NOT NULL, correo	TEXT NOT NULL, contraseña TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE usuarios");
    }
}
