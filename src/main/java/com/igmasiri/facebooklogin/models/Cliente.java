package com.igmasiri.facebooklogin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Cliente extends Usuario implements java.io.Serializable {

}


