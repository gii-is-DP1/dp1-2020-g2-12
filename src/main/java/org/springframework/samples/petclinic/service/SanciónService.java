package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pista;
import org.springframework.samples.petclinic.model.Sanción;
import org.springframework.samples.petclinic.repository.SanciónRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SanciónService {
	
	@Autowired
	private SanciónRepository SancionRepo;
	
	@Transactional
	public Iterable<Sanción> findAll() {
		return SancionRepo.findAll();
		}
}
