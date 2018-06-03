package com.igmasiri.facebooklogin.repositories;

import com.igmasiri.facebooklogin.models.Empleado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

	@Query("SELECT o FROM Empleado o WHERE o.id = :id")
    Empleado findById(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Empleado o WHERE o.id = :id")
    Integer deleteById(@Param("id") Integer id);

	@Query("SELECT o FROM Empleado o WHERE o.token = :token")
	Empleado obtenerEmpleadoPorToken(@Param("token") String token);

	@Query("SELECT o FROM Empleado o WHERE o.comercio.id = :idComercio")
	List<Empleado> ObtenerPorComercio(@Param("idComercio") Integer idComercio);

	@Query("SELECT o FROM Empleado o WHERE o.correo = :correo")
	Empleado obtenerEmpleadoPorCorreo(@Param("correo") String correo);

	@Query("SELECT o FROM Empleado o")
	List<Empleado> listar();
}
