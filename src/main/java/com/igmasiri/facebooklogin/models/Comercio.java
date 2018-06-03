package com.igmasiri.facebooklogin.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "comercio")
public class Comercio  implements java.io.Serializable {

     @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     private int id;

     private String host;

     //facebook config
     private String facebookAplicationId;
     private String facebookSecretKey;
     private String facebookAccessTokenApi;

     @OneToMany(
             mappedBy = "comercio",
             fetch = FetchType.LAZY,
             cascade = CascadeType.ALL)
     private Set<Empleado> empleados = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFacebookAplicationId() {
        return facebookAplicationId;
    }

    public void setFacebookAplicationId(String facebookAplicationId) {
        this.facebookAplicationId = facebookAplicationId;
    }

    public String getFacebookSecretKey() {
        return facebookSecretKey;
    }

    public void setFacebookSecretKey(String facebookSecretKey) {
        this.facebookSecretKey = facebookSecretKey;
    }

    public String getFacebookAccessTokenApi() {
        return facebookAccessTokenApi;
    }

    public void setFacebookAccessTokenApi(String facebookAccessTokenApi) {
        this.facebookAccessTokenApi = facebookAccessTokenApi;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }
}


