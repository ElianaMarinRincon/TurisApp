package com.example.turisapp.modelos;

public class lugares {

    private int id;
    private String nombre;
    private String departamento;
    private String municipio;
    private String latitud;
    private String longitud;
    private int tipo;
    private int presupuesto;
    private String comentarios;

    public lugares(){
    }

    public int getId() {return id;}
    public void setId (int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre (String nombres) {this.nombre = nombre;}

    public String getDepartamento() {return departamento;}
    public void setDepartamento (String departamento) {this.departamento = departamento;}

    public String getMunicipio() {return municipio;}
    public void setMunicipio (String municipio) {this.municipio = municipio;}

    public String getLatitud() {return latitud;}
    public void setLatitud (String latitud) {this.latitud = latitud;}

    public String getLongitud () {return longitud;}
    public void setLongitud (String longitud) {this.longitud = longitud;}

    public int getTipo() {return tipo;}
    public void setTipo (int tipo) {this.tipo = tipo;}

    public int getPresupuesto() {return presupuesto;}
    public void setPresupuesto (int presupuesto) {this.presupuesto = presupuesto;}

    public String getComentarios () {return comentarios;}
    public void setComentarios (String comentarios) {this.comentarios = comentarios;}
}
