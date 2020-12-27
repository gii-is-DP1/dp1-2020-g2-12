package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Sanción;
import org.springframework.samples.petclinic.model.Torneo;
import org.springframework.samples.petclinic.repository.AthleteRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SanciónRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.service.exceptions.IncongruentSancionDateExcepcion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SanciónService {
	
	private SanciónRepository sancionRepo;
	
	@Autowired
	public SanciónService(SanciónRepository sancionRepository,
			AthleteRepository deportistaRepository) {
		this.sancionRepo = sancionRepository;
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
				if (s.getFechaFin()!=null) {
					if(s.getFechaFin().isAfter(fechaActual)) {
						sancionado = true;
					}
				}
			}
		}
		return sancionado;
	}
	
	@Transactional
	Set<Sanción> findSancionByAthleteId(int athleteId) {
		return sancionRepo.findByAthleteId(athleteId);
	}
	
	@Transactional
	public Sanción findSancionById(int sancionId) throws DataAccessException {
		
		return sancionRepo.findById(sancionId).get();
	}
	
	@Transactional (rollbackFor = IncongruentSancionDateExcepcion.class)
	public void saveSancion(Sanción sancion) throws DataAccessException, IncongruentSancionDateExcepcion {
		if(sancion.getFechaFin()==null) {throw new IncongruentSancionDateExcepcion();}
		else{
			if (!sancion.getFechaFin().isAfter(LocalDate.now())) {
			throw new IncongruentSancionDateExcepcion();
		}else {
			sancionRepo.save(sancion);
			Athlete a = sancion.getAthlete();
			if(a.getTorneos()!=null) {
				Set<Torneo> torneos = a.getTorneos();
				Set<Torneo> torneosVacio = new HashSet<Torneo>();
				for(Torneo t:torneos) {
					Set<Athlete> participantes = t.getParticipantes();
					participantes.remove(a);
					t.setParticipantes(participantes);
					}
				a.setTorneos(torneosVacio);
				}
			}
		}
	}
	
	@Transactional
	public void deleteSancion(Sanción sancion) throws DataAccessException{
		Athlete a=sancion.getAthlete();
		a.getSanciones().remove(sancion);
		sancionRepo.delete(sancion);
	}
}

