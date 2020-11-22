package org.springframework.samples.petclinic.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Pista;
import org.springframework.samples.petclinic.model.Torneo;


public interface TorneoRepostitory  extends CrudRepository<Torneo, Integer>{

}
