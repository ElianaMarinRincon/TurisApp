package com.example.turisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComentariosCalificacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios_calificacion);

        Button btnAtras = (Button) findViewById(R.id.comentarios_btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onBackPressed();}
        });

        Button btnFinaliar = (Button) findViewById(R.id.comentarios_btnFinalizar);
        btnFinaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PerfilActivity.class);
                startActivity(i);
            }
        });
    }
}