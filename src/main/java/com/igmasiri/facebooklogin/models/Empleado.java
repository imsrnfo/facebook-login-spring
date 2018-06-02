package com.igmasiri.facebooklogin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


