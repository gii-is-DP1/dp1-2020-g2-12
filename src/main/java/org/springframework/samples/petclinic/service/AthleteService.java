package org.springframework.samples.petclinic.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.repository.AthleteRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AthleteService {
	
	@Autowired
	private AthleteRespository athleteRespository;
	
	@Transactional
	public int athleteCount() {
		return (int) athleteRespository.count();
	}
	
	@Transactional
	public Iterable<Athlete> findAll() {
		return athleteRespository.findAll();
		}
	
	

	public void delete(Athlete athlete) {
		athleteRespository.delete(athlete);
	}

	public void save(Athlete athlete) {
		athleteRespository.save(athlete);
		
	}

	public Athlete findAthleteById(int athleteId) {
		
		return athleteRespository.findById(athleteId).get();
	}
	
	public void deleteAthleteById(int athleteId) {
		
		athleteRespository.deleteById(athleteId);
	}
	
	public Set<Athlete> findAthleteByEntrenadorId(int entrenadorId) {
		return athleteRespository.findByEntrenadorId(entrenadorId);
	}
}

	

