package com.example.turisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.turisapp.clases.Mensajes;
import com.example.turisapp.clases.UsuarioADO;
import com.example.turisapp.modelos.usuario;

public class RegistroActivity extends AppCompatActivity {

    private usuario registro=null;
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtFechaNacimiento;
    private EditText txtEmail;
    private EditText txtClave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnVolver = (Button) findViewById(R.id.registro_btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button btnGuardar = (Button) findViewById(R.id.registor_btnGuardar);
        txtNombres = (EditText) findViewById(R.id.registro_txtNombres);
        txtApellidos = (EditText) findViewById(R.id.registro_txtApellidos);
        txtFechaNacimiento = (EditText) findViewById(R.id.registro_dateFechaNacimiento);
        txtEmail = (EditText) findViewById(R.id.registo_emailCorreo);
        txtClave = (EditText) findViewById(R.id.registro_pswClave);

        Bundle parametros = getIntent().getExtras();
        if (parametros != null)
        if (parametros.containsKey("id")) {
           UsuarioADO db = new UsuarioADO(this);
           usuario us = db.obtenerUsuario(parametros.getLong("id"));
           this.registro = us;
           cargarDatos();
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String nombres = txtNombres.getText().toString();
            String apellidos = txtApellidos.getText().toString();
            String fechaNacimiento = txtFechaNacimiento.getText().toString();
            String email = txtEmail.getText().toString();
            String clave = txtClave.getText().toString();

            if (nombres.isEmpty() || apellidos.isEmpty() || fechaNacimiento.isEmpty() || email.isEmpty() || clave.isEmpty()) {
               AlertDialog.Builder mensaje = new AlertDialog.Builder(view.getContext());
               mensaje.setTitle("No se puede guardad");
               mensaje.setMessage("Por favor diligencia todos los campos");
               mensaje.create();
               mensaje.show();
            }
            else if (clave.length() < 6) {
               AlertDialog.Builder mensaje = new AlertDialog.Builder(view.getContext());
               mensaje.setTitle("Error");
               mensaje.setMessage("Por favor ingresa una contraseña de 6 o más caracteres");
               mensaje.create();
               mensaje.show();
            }
            else {
               Intent i = new Intent(view.getContext(), PerfilActivity.class);
               startActivity(i);
            }

            }
        });
    }

    public static boolean validacionClave (String clave)
    {
        boolean validacion = false;
        if ((clave.length()>=6))
            validacion = true;

        return validacion;
    }



    private void cargarDatos()
    {
        this.txtNombres.setText(this.registro.getNombres());
        this.txtApellidos.setText(this.registro.getApellidos());
        this.txtFechaNacimiento.setText(this.registro.getFechaNacimiento());
        this.txtEmail.setText(this.registro.getCorreo());
        this.txtClave.setText(this.registro.getClave());
    }


}