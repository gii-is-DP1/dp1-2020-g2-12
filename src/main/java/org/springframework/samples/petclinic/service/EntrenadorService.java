package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntrenadorService {
	

	@Autowired
	private EntrenadorRepository entrenadorRespository;
	
	@Transactional
	public int entrenadorCount() {
		return (int) entrenadorRespository.count();
	}
	
	@Transactional
	public Iterable<Entrenador> findAll() {
		return entrenadorRespository.findAll();
		}

	public void delete(Entrenador entrenador) {
		entrenadorRespository.delete(entrenador);
	}

	public void save(Entrenador entrenador) {
		entrenadorRespository.save(entrenador);
		
	}

	public Entrenador findEntrenadorById(int entrenadorId) {
		
		return entrenadorRespository.findById(entrenadorId).get();
	}
	
	public void deleteEntrenadorById(int entrenadorId) {
		
		entrenadorRespository.deleteById(entrenadorId);
	}
	
}
