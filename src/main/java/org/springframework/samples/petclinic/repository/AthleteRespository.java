package org.springframework.samples.petclinic.repository;


import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Athlete;



public interface AthleteRespository extends CrudRepository<Athlete, Integer>{

	
	@Query("SELECT athlete from Athlete athlete WHERE entrenador_id LIKE :entrenadorId%")
	Set<Athlete> findByEntrenadorId(@Param("entrenadorId")int entrenadorId);
	

}

