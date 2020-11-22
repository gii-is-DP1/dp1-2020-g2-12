package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pista;
import org.springframework.samples.petclinic.model.Torneo;
import org.springframework.samples.petclinic.repository.PistaRepository;
import org.springframework.samples.petclinic.repository.TorneoRepostitory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TorneoService {

	@Autowired
	private TorneoRepostitory torneoRepo;
	
	@Transactional
	public int torneoCount() {
		return (int) torneoRepo.count();
	}
	
	@Transactional
	public Iterable<Torneo> findAll() {
		return torneoRepo.findAll();
		}

	public void delete(Torneo torneo) {
		torneoRepo.delete(torneo);
	}

	public void save(Torneo torneo) {
		torneoRepo.save(torneo);
		
	}

	public Optional<Torneo> findTorneoById(int torneoId) {
		
		return torneoRepo.findById(torneoId);
	}
	
	public void deleteTorneoById(int torneoId) {
		
		torneoRepo.deleteById(torneoId);
	}
}
