package com.example.turisapp.modelos;

import java.util.Date;

public class usuario {
    private int id;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String correo;
    private String clave;

    public usuario(){
    }

    public int getId() {return id;}
    public void setId (int id) {this.id = id;}

    public String getNombres() {return nombres;}
    public void setNombres (String nombres) {this.nombres = nombres;}

    public String getApellidos() {return apellidos;}
    public void setApellidos (String apellidos) {this.apellidos = apellidos;}

    public String getFechaNacimiento() {return fechaNacimiento;}
    public void setFechaNacimiento (String fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public String getCorreo() {return correo;}
    public void setCorreo (String correo) {this.correo = correo;}

    public String getClave() {return clave;}
    public void setClave (String clave) {this.clave = clave;}
}
