package com.example.turisapp.clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.turisapp.RegistroActivity;
import com.example.turisapp.modelos.usuario;

import java.util.ArrayList;

public class UsuarioADO extends SqliteConex{

    private Context contexto;

    public UsuarioADO(@Nullable Context c)
    {
        super(c);
        this.contexto = c;
    }

    public long insertar (usuario us)
    {
        long id = 0;

        try
        {
            SqliteConex dbc = new SqliteConex(this.contexto);
            SQLiteDatabase db = dbc.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombres", us.getNombres());
            valores.put("apellidos", us.getApellidos());
            valores.put("fechaNacimiento", us.getFechaNacimiento());
            valores.put("correo", us.getCorreo());
            valores.put("clave", us.getClave());

            id = db.insert("usuarios", null, valores);
        }

        catch (Exception ex)
        {

        }

        return id;
    }

    public usuario obtenerUsuario(long id)
    {
        usuario us = null;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
            Cursor cregistros = db.rawQuery("select id, nombres, apellidos, fchaNacimiento, email, clave from usuarios where id = " + String.valueOf(id), null);
            cregistros.moveToFirst();
            us = new usuario();
            us.setId(cregistros.getInt(0));
            us.setNombres(cregistros.getString(1));
            us.setApellidos(cregistros.getString(2));
            us.setFechaNacimiento(cregistros.getString(3));
            us.setCorreo(cregistros.getString(4));
            us.setClave(cregistros.getString(5));

        }
        catch (Exception ex)
        {

        }
        finally {
            db.close();
        }
        return us;
    }

    public ArrayList<usuario> listar()
    {
        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ArrayList<usuario> registros = new ArrayList<>();
        Cursor cregistros = db.rawQuery("select id, nombres, apellidos, fechaNacimiento, coreo, clave from usuarios", null);

        if(cregistros.moveToFirst())
            do {
                usuario us = new usuario();
                us.setId(cregistros.getInt(0));
                us.setNombres(cregistros.getString(1));
                us.setApellidos(cregistros.getString(2));
                us.setFechaNacimiento(cregistros.getString(3));
                us.setCorreo(cregistros.getString(4));
                us.setClave(cregistros.getString(5));

                registros.add(us);
            }while (cregistros.moveToNext());

        return registros;
    }

    public boolean editar(usuario us)
    {
        boolean editado = false;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();
        try
        {

            db.execSQL("UPDATE usuarios" +
                    "   SET nombres = '" + us.getNombres() + "'," +
                    "       apellidos = '" + us.getApellidos() + "'," +
                    "       fechaNacimento = '" + us.getFechaNacimiento() + "'," +
                    "       email = '" + us.getCorreo() + "'," +
                    "       clave = '" + us.getClave() + "'" +
                    " WHERE id = '" + String.valueOf(us.getId()) + "'");
            editado=true;

        }
        catch (Exception ex)
        {

        }
        finally {
            db.close();
        }

        return editado;
    }

    public boolean eliminar(long id)
    {
        boolean eliminado = false;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
            db.execSQL("DELETE FROM usuarios\n" +
                    "      WHERE id = '" + String.valueOf(id) + "'");
            eliminado=true;
        }
        catch (Exception ex)
        {

        }
        finally {
            db.close();
        }

        return eliminado;
    }


}
