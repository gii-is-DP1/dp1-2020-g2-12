package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Deporte;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Pista;
import org.springframework.samples.petclinic.repository.PistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PistaService {

	@Autowired
	private PistaRepository pistaRepo;
	
	@Transactional
	public int pistCount() {
		return (int) pistaRepo.count();
	}
	
	@Transactional
	public Iterable<Pista> findAll() {
		return pistaRepo.findAll();
		}

	public void delete(Pista pista) {
		pistaRepo.delete(pista);
	}

	public void save(Pista pista) {
		pistaRepo.save(pista);
		
	}

	public Optional<Pista> findPistaById(int pistaId) {
		
		return pistaRepo.findById(pistaId);
	}
	
	public void deletePistaById(int pistaId) {
		
		pistaRepo.deleteById(pistaId);
	}
	
	@Transactional(readOnly = true)
	public Collection<Deporte> findDeportes() throws DataAccessException {
		return pistaRepo.findDeportes();
	}
}
