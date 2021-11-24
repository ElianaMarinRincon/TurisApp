package com.example.turisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnIngresar = (Button) findViewById(R.id.login_btnIngreso);
        EditText txtEmail = (EditText) findViewById(R.id.login_txtEmail);
        EditText txtClave = (EditText) findViewById(R.id.login_txtClave);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String clave = txtClave.getText().toString();

                if(email.isEmpty() || clave.isEmpty()){
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(view.getContext());
                    mensaje.setTitle("No se puede ingresar");
                    mensaje.setMessage("Por favor diligencia los campos de ingreso o regístrate");
                    mensaje.create();
                    mensaje.show();
                }

                else {
                    Intent i = new Intent(view.getContext(), PerfilActivity.class);
                    startActivity(i);
                }
            }
        });

        Button btnRegistrarse = (Button) findViewById(R.id.login_btnRegistro);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegistroActivity.class);
                startActivity(i);
            }
        });

        Button btnRegistroLugares = (Button) findViewById(R.id.login_btnRegistrarLugares);
        btnRegistroLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), InsertarLugaresActivity.class);
                startActivity(i);
            }
        });

    }
}