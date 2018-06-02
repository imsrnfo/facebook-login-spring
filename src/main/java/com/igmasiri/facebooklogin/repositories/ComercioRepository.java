package com.igmasiri.facebooklogin.repositories;

import com.igmasiri.facebooklogin.models.Comercio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComercioRepository extends CrudRepository<Comercio, Long> {

	@Query("SELECT o FROM Comercio o WHERE o.id = :id")
    Comercio findById(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Comercio o WHERE o.id = :id")
    Integer deleteById(@Param("id") Integer id);

	@Query("SELECT o FROM Comercio o WHERE o.host = :host")
	Comercio obtenerPorHost(@Param("host") String host);

	@Query("SELECT o FROM Comercio o")
	List<Comercio> listar();
}
