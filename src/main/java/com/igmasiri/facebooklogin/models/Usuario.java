package com.igmasiri.facebooklogin.models;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String username;
    private String password;

    private String token;
    private String nombre;
    private String apellido;
    private String correo;
    private String imagen;
    private String facebookid;
    private String facebooktoken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFacebookid() {
        return facebookid;
    }

    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }

    public String getFacebooktoken() {
        return facebooktoken;
    }

    public void setFacebooktoken(String facebooktoken) {
        this.facebooktoken = facebooktoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
