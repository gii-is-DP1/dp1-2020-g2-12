package org.springframework.samples.petclinic.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Sanción;

public interface SanciónRepository extends CrudRepository<Sanción, Integer>{
	@Query("SELECT sancion from Sanción sancion WHERE athlete_id LIKE :athleteId%")
	Set<Sanción> findByAthleteId(@Param("athleteId")int athleteId);
}
