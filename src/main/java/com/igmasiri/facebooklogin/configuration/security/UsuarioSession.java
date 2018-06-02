package com.igmasiri.facebooklogin.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSession extends User {

    public UsuarioSession(String username, String password, Collection<? extends GrantedAuthority> authorities) {super(username, password, authorities);}

    //Aqui se pueden agregar todos los valores que queremos almacenar en la session del usuario
    private boolean esEmpleado = false;

    public boolean isEsEmpleado() {
        return esEmpleado;
    }

    public void setEsEmpleado(boolean esEmpleado) {
        this.esEmpleado = esEmpleado;
    }

}
