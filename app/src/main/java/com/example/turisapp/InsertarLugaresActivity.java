package com.example.turisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.turisapp.clases.Mensajes;
import com.example.turisapp.clases.UsuarioADO;
import com.example.turisapp.clases.lugaresADO;
import com.example.turisapp.modelos.lugares;
import com.example.turisapp.modelos.usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertarLugaresActivity extends AppCompatActivity {

    private lugares registro = new lugares();
    private EditText txtNombre;
    private EditText txtDepartamento;
    private EditText txtMunicipio;
    private EditText txtLatitud;
    private EditText txtLongitud;
    private Spinner spnTipo;
    private Spinner spnPresupuesto;
    private EditText txtComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_lugares);

        Button btnAtras = (Button) findViewById(R.id.insertarLugares_btnAtras);
        Button btnGuardar = (Button) findViewById(R.id.insertarLugares_btnGuardar);
        txtNombre = (EditText) findViewById(R.id.insertarLugares_txtNombre);
        txtDepartamento= (EditText) findViewById(R.id.insertarLugares_txtDepto);
        txtMunicipio= (EditText) findViewById(R.id.insertarLugares_txtMunicipio);
        txtLatitud = (EditText) findViewById(R.id.insertarLugares_txtLatitud);
        txtLongitud = (EditText) findViewById(R.id.insertarLugares_txtLongitud);
        spnTipo = (Spinner) findViewById(R.id.insertarLugares_spnTipo);
        spnPresupuesto = (Spinner) findViewById(R.id.insertarLugares_spnTipo);
        txtComentarios = (EditText) findViewById(R.id.insertarLugares_txtComentarios);

        Bundle parametros = getIntent().getExtras();
        if(parametros!=null)
        if(parametros.containsKey("id"))
        {
            lugaresADO db = new lugaresADO(this);
            lugares lug = db.obtenerLugar(parametros.getLong("id"));
            this.registro = lug;
            cargarDatos();
        }
        else
            this.registro = new lugares();

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onBackPressed();}
        });

       btnGuardar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String nombre = txtNombre.getText().toString();
               String departamento = txtDepartamento.getText().toString();
               String municipio = txtMunicipio.getText().toString();
               String latitud = txtLatitud.getText().toString();
               String longitud = txtLongitud.getText().toString();
               int tipo = spnTipo.getSelectedItemPosition();
               int presupuesto = spnPresupuesto.getSelectedItemPosition();
               String comentarios = txtComentarios.getText().toString();

               registro.setNombre(nombre);
               registro.setDepartamento(departamento);
               registro.setMunicipio(municipio);
               registro.setLatitud(latitud);
               registro.setLongitud(longitud);
               registro.setTipo(tipo);
               registro.setPresupuesto(presupuesto);
               registro.setComentarios(comentarios);

               lugaresADO registro = new lugaresADO(view.getContext());
               lugares lug = new lugares();
               lug.setNombre(nombre);
               lug.setDepartamento(departamento);
               lug.setMunicipio(municipio);
               lug.setLatitud(latitud);
               lug.setLongitud(longitud);
               lug.setTipo(tipo);
               lug.setPresupuesto(presupuesto);
               lug.setComentarios(comentarios);
               long idInsercion = registro.insertar(lug);
               new Mensajes(view.getContext()).alerta("", String.valueOf(idInsercion));
               if (idInsercion > 0)
                   new Mensajes(view.getContext()).alerta("Registro correcto", "Sitio turístico ingresado correctamente" + String.valueOf(idInsercion));
               else
                   new Mensajes(view.getContext()).alerta("Error", "Ha ocurrido un error en el ingreso, por favor intente de nuevo");


               // Write a message to the database
               FirebaseDatabase database = FirebaseDatabase.getInstance();
               database.getReference().child("Lugar").child(String.valueOf(lug.getId())).setValue(lug);


               Intent i = new Intent(view.getContext(), LugarResultado.class);
               startActivity(i);
           }
       });
    }


    private void cargarDatos()
    {
        this.txtNombre.setText(this.registro.getNombre());
        this.txtDepartamento.setText(this.registro.getDepartamento());
        this.txtMunicipio.setText(this.registro.getMunicipio());
        this.txtLatitud.setText(this.registro.getLatitud());
        this.txtLongitud.setText(this.registro.getLongitud());
        this.spnTipo.setSelection(this.registro.getTipo());
        this.spnPresupuesto.setSelection(this.registro.getPresupuesto());
        this.txtComentarios.setText(this.registro.getComentarios());
    }

}

