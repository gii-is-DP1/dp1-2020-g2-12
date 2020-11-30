package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Sanción;
import org.springframework.samples.petclinic.repository.AthleteRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SanciónRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SanciónService {
	
	private SanciónRepository sancionRepo;
	private AthleteRepository deportistaRepo;
	
	@Autowired
	public SanciónService(SanciónRepository sancionRepository,
			AthleteRepository deportistaRepository) {
		this.sancionRepo = sancionRepository;
		this.deportistaRepo = deportistaRepository;
	}
	
	@Transactional
	public Iterable<Sanción> findAll() {
		return sancionRepo.findAll();
		}
	
	@Transactional
	public int sancCount() {
		return (int) sancionRepo.count();
	}
	
	@Transactional
	public boolean esSancionado(int athleteId) {
		boolean sancionado = false;
		LocalDate fechaActual = LocalDate.now();
		Set<Sanción> sanciones=findSancionByAthleteId(athleteId);
		if(!sanciones.isEmpty()) {
			for(Sanción s:sanciones) {
				if(s.getFechaFin().isAfter(fechaActual)) {
					sancionado = true;
				}
			}
		}
		return sancionado;
	}
	
	@Transactional
	Set<Sanción> findSancionByAthleteId(int athleteId){
		return sancionRepo.findByAthleteId(athleteId);
	}
	
	@Transactional
	public Sanción saveSanción(Sanción sancion) {
		return sancionRepo.save(sancion);
	}
	
	@Transactional
	public void deleteSanción(Sanción sancion) {
		sancionRepo.delete(sancion);
	}
	
	@Transactional
	public Optional<Sanción> findSancionById(int sancionId) {
		
		return sancionRepo.findById(sancionId);
	}
}

