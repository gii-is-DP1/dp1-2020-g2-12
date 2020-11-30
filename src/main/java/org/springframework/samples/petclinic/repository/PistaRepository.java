package org.springframework.samples.petclinic.repository;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Deporte;
import org.springframework.samples.petclinic.model.Pista;


public interface PistaRepository  extends CrudRepository<Pista, Integer>{

	@Query("SELECT deporte FROM Deporte deporte ORDER BY deporte.name")
	List<Deporte> findDeportes() throws DataAccessException;
}
