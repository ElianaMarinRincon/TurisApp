package com.example.turisapp.clases;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class Mensajes {
    private Context contexto;

    public Mensajes(Context contexto) {
        this.contexto = contexto;
    }

    public void alerta(String titulo, String cuerpo)
    {
        AlertDialog.Builder msj = new AlertDialog.Builder(this.contexto);
        msj.setTitle(titulo);
        msj.setMessage(cuerpo);
        msj.create();
        msj.show();
    }

}
