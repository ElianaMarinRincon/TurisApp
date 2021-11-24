package com.example.turisapp.clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.turisapp.modelos.lugares;
import com.example.turisapp.modelos.usuario;

import java.util.ArrayList;

public class lugaresADO extends SqliteConexLugares {

    private Context contexto;

    public lugaresADO(@Nullable Context c)
    {
        super(c);
        this.contexto = c;
    }

    public long insertar (lugares lug)
    {
        long id = 0;

        try
        {
            SqliteConex dbc = new SqliteConex(this.contexto);
            SQLiteDatabase db = dbc.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombre", lug.getNombre());
            valores.put("departamento", lug.getDepartamento());
            valores.put("municipio", lug.getMunicipio());
            valores.put("latitud", lug.getLatitud());
            valores.put("longitud", lug.getLongitud());
            valores.put("tipo", lug.getTipo());
            valores.put("presupuesto", lug.getPresupuesto());
            valores.put("comentarios", lug.getComentarios());

            id = db.insert("lugares", null, valores);
        }

        catch (Exception ex)
        {

        }

        return id;
    }

    public lugares obtenerlugar (long id)
    {
        lugares lug = null;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
            Cursor cregistros = db.rawQuery("select id, nombre, departamento, municipio, latitud, longitud, tipo, presupuesto from lugares where id = " + String.valueOf(id), null);
            cregistros.moveToFirst();
            lug = new lugares();
            lug.setId(cregistros.getInt(0));
            lug.setNombre(cregistros.getString(1));
            lug.setDepartamento(cregistros.getString(2));
            lug.setMunicipio(cregistros.getString(3));
            lug.setLatitud(cregistros.getString(4));
            lug.setLongitud(cregistros.getString(5));
            lug.setTipo(cregistros.getInt(6));
            lug.setPresupuesto(cregistros.getInt(7));
            lug.setComentarios(cregistros.getString(8));

        }
        catch (Exception ex)
        {

        }
        finally {
            db.close();
        }
        return lug;
    }

    public ArrayList<lugares> listar()
    {
        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ArrayList<lugares> registros = new ArrayList<>();
        Cursor cregistros = db.rawQuery("select id, nombre, departamento, municipio, latitud, longitud, tipo, presupuesto, comentarios from lugares", null);

        if(cregistros.moveToFirst())
            do {
                lugares lug = new lugares();
                lug.setId(cregistros.getInt(0));
                lug.setNombre(cregistros.getString(1));
                lug.setDepartamento(cregistros.getString(2));
                lug.setMunicipio(cregistros.getString(3));
                lug.setLatitud(cregistros.getString(4));
                lug.setLongitud(cregistros.getString(5));
                lug.setTipo(cregistros.getInt(6));
                lug.setPresupuesto(cregistros.getInt(7));
                lug.setComentarios(cregistros.getString(8));

                registros.add(lug);
            }while (cregistros.moveToNext());

        return registros;
    }

    public boolean editar (lugares lug)
    {
        boolean editado = false;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();
        try
        {

            db.execSQL("UPDATE lugares" +
                    "   SET nombre = '" + lug.getNombre() + "'," +
                    "       departamento = '" + lug.getDepartamento() + "'," +
                    "       municipio = '" + lug.getMunicipio() + "'," +
                    "       latitud = '" + lug.getLatitud() + "'," +
                    "       longitud = '" + lug.getLongitud() + "'," +
                    "       tipo = '" + lug.getTipo() + "'," +
                    "       presupuesto = '" + lug.getPresupuesto() + "'" +
                    "        comentarios = '" + lug.getComentarios() + "'" +
                    " WHERE id = '" + String.valueOf(lug.getId()) + "'");
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

    public boolean eliminar (long id)
    {
        boolean eliminado = false;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
            db.execSQL("DELETE FROM lugares\n" +
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
