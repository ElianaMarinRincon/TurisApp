
package com.example.turisapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.turisapp.clases.Mensajes;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth autenticacion;
    private EditText txtEmail;
    private EditText txtClave;
    private int SignInEstado = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autenticacion = FirebaseAuth.getInstance();

        FirebaseUser usuario = autenticacion.getCurrentUser();
        if(usuario!=null)
        {
            Intent menu = new Intent(this, PerfilActivity.class);
            menu.putExtra("usuario", usuario.getEmail());
            startActivity(menu);
        }


        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.login_txtEmail);
        txtClave = (EditText) findViewById(R.id.login_txtClave);
        Button btnAcceder = (Button) findViewById(R.id.login_btnIngreso);
        Button btnRegistro = (Button) findViewById(R.id.login_btnRegistro);
        Button btnRegistrarLugares = (Button) findViewById(R.id.login_btnRegistrarLugares);
        ImageButton btnGoogle = (ImageButton) findViewById(R.id.login_btnGoogle);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCamposVacios())
                {
                    mensajes("Existen campos vacios");
                }
                else {
                    autenticacion.createUserWithEmailAndPassword(
                            txtEmail.getText().toString(),
                            txtClave.getText().toString()).addOnCompleteListener((Activity) view.getContext(),
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Intent menu = new Intent(view.getContext(), RegistroActivity.class);
                                        menu.putExtra("usuario", txtEmail.getText().toString());
                                        startActivity(menu);
                                    }
                                    else
                                    {
                                        mensajes("No se pudo registrar el usuario");
                                    }
                                }
                            });
                }
            }
        });

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCamposVacios())
                {
                    mensajes("Existen campos vacios");
                }
                else
                {
                    autenticacion.signInWithEmailAndPassword(
                            txtEmail.getText().toString(),
                            txtClave.getText().toString()).addOnCompleteListener((Activity) view.getContext(),
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Intent menu = new Intent(view.getContext(), PerfilActivity.class);
                                        menu.putExtra("usuario", txtEmail.getText().toString());
                                        startActivity(menu);
                                    }
                                }
                            });
                }
            }
        });

        btnRegistrarLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), InsertarLugaresActivity.class);
                startActivity(i);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.web_client))
                        .requestEmail()
                        .build();

                Intent googleDialog = GoogleSignIn.getClient(view.getContext(), gso).getSignInIntent();
                startActivityForResult(googleDialog, SignInEstado);


            }
        });
    }

    public boolean validarCamposVacios()
    {
        boolean vacio = true;

        try {
            if(!txtEmail.getText().toString().isEmpty() && !txtClave.getText().toString().isEmpty())
                vacio=false;
        }
        catch (Exception ex)
        {

        }

        return vacio;
    }

    public void mensajes(String cuerpo)
    {
        AlertDialog.Builder msj = new AlertDialog.Builder(this);
        msj.setMessage(cuerpo);
        msj.create();
        msj.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        txtEmail.setText("");
        txtClave.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Activity actividadActual = this;

        if(requestCode==SignInEstado)
        {
            Task<GoogleSignInAccount> cuentaEsperada = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount cuenta = cuentaEsperada.getResult(ApiException.class);
                AuthCredential credencial = GoogleAuthProvider.getCredential(cuenta.getIdToken(), null);
                autenticacion.signInWithCredential(credencial).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser usuario = autenticacion.getCurrentUser();
                            Intent menu = new Intent(actividadActual, PerfilActivity.class);
                            menu.putExtra("usuario", usuario.getEmail());
                            startActivity(menu);
                        }
                        else
                        {
                            mensajes("No se encontró el usuario");
                        }
                    }
                });
            }
            catch (Exception ex)
            {

            }
        }

    }
}