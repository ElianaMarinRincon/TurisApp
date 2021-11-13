package com.example.turisapp.clases;

import android.content.Context;

import androidx.annotation.Nullable;

public class UsuarioADO extends SqliteConex{

    private Context contexto;

    public UsuarioADO(@Nullable Context c)
    {
        super(c);
        this.contexto = c;
    }

    public long insertar (Usuario us);

}
