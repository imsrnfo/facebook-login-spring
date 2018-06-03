package com.igmasiri.facebooklogin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "empleado")
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Empleado extends Usuario implements java.io.Serializable {

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "idComercio", nullable = false)
     @JsonIgnore
     private Comercio comercio;


}


