package com.igmasiri.facebooklogin.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Cliente extends Usuario implements java.io.Serializable {

}


