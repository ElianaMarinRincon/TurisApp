package com.example.turisapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth autenticacion;
    private EditText txtEmail;
    private EditText txtclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autenticacion=FirebaseAuth.getInstance();

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
                if (validarcamposvacios())
                {
                AlertDialog.Builder msj=new AlertDialog.Builder(view.getContext());
                msj.setMessage("Existen campos vacios");
                msj.create();
                msj.show();
                }
                else {
                    autenticacion.createUserWithEmailAndPassword(
                            txtEmail.getText().toString(),
                            txtClave.getText().toString()).addOnCompleteListener((Activity) view.getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful())
                           {
                               Intent menu=new Intent(view.getContext(), PerfilActivity.class);
                               menu.putExtra("usuario",txtEmail.getText().toString());
                               startActivity(menu);

                           }
                           else {
                               AlertDialog.Builder msj=new AlertDialog.Builder(view.getContext());
                               msj.setMessage("No se puede registrar el usuario");
                               msj.create();
                               msj.show();

                           }
                        }
                    });
                }

                Intent in = new Intent(view.getContext(), RegistroActivity.class);
                startActivity(in);
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



    private boolean validarcamposvacios()
    {
        boolean vacio=true;
        try {
            if (txtEmail.getText().toString().isEmpty()&& txtclave.getText().toString().isEmpty());
            vacio=false;
        } catch (Exception e) {
            e.printStackTrace();
        }        return vacio;
    }

}