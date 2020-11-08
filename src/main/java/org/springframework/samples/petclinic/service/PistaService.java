package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
