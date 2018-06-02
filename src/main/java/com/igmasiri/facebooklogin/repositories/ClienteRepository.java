package com.igmasiri.facebooklogin.repositories;

import com.igmasiri.facebooklogin.models.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query("SELECT o FROM Cliente o WHERE o.id = :id")
    Cliente findById(@Param("id") Integer id);

	@Transactional
	@Modifying
	@Query("DELETE FROM Cliente o WHERE o.id = :id")
    Integer deleteById(@Param("id") Integer id);

	@Query("SELECT o FROM Cliente o WHERE o.token = :token")
	Cliente obtenerClientePorToken(@Param("token") String token);

	@Query("SELECT o FROM Cliente o WHERE o.correo = :correo")
	Cliente obtenerClientePorCorreo(@Param("correo") String correo);

	@Query("SELECT o FROM Cliente o")
	List<Cliente> listar();
}
