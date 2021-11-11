package com.example.turisapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.turisapp.clases.Mensajes;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PerfilActivity<onCreateOptionsMenu> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_TurisApp_barra);
        setContentView(R.layout.activity_perfil);

        FloatingActionButton btnAcercaDe = (FloatingActionButton) findViewById(R.id.perfil_flb_AcercaDe);
        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dlg = new BottomSheetDialog(view.getContext());
                dlg.setContentView(R.layout.activity_perfil);
                dlg.show();
            }
        });

        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "TurisApp es una aplicación creada por Diana Vanessa Becerra Alfonso, Eliana Marían Rincón y Alberto Vesga Guarín para la busqueda de sitios turísticos en Colombia", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater propiedadesMenu = getMenuInflater();
        propiedadesMenu.inflate(R.menu.menu_principal_perfil, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Mensajes msj = new Mensajes(this);
        Intent i = null;

        switch (item.getItemId())
        {
            case R.id.menuPpalPerfil_BuscarSitiosTuristicos: i = new Intent(this, Formulario.class);
                break;

            case R.id.menuPpalPerfil_SitiosVisitados: i = new Intent(this, LugaresVisitadosActivity.class);
                break;

            case R.id.menuPpalPerfil_CerrarSesion: msj.alerta("", "Se ha cerrado sesión"); i = new Intent(this, Bienvenida.class);
                break;
        }
        startActivity(i);
        return super.onOptionsItemSelected(item);

    }
}