package com.igmasiri.facebooklogin.repositories;

import com.igmasiri.facebooklogin.models.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE LOWER(u.username) = LOWER(:name)")
    List<Usuario> findByName(@Param("name") String name);
	
	@Query("SELECT u FROM Usuario u WHERE u.id = :id")
	Usuario findById(@Param("id") Integer id);

	@Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
	Usuario obtenerPorCorreo (@Param("correo") String correo);

	@Transactional
	@Modifying
	@Query("DELETE FROM Usuario u WHERE u.id = :id")
    Integer deleteById(@Param("id") Integer id);
}
