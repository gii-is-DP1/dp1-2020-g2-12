package org.springframework.samples.petclinic.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Sanción;
import org.springframework.samples.petclinic.repository.AthleteRepository;
import org.springframework.samples.petclinic.repository.SanciónRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AthleteService {
	
	private AthleteRepository athleteRepository;
	private SanciónRepository sancionRepo;
	
	@Autowired
	public AthleteService(AthleteRepository athleteRepository,
			SanciónRepository sancionRepository) {
		this.athleteRepository = athleteRepository;
		this.sancionRepo = sancionRepository;
	}
	
	@Transactional
	public int athleteCount() {
		return (int) athleteRepository.count();
	}
	
	@Transactional
	public Iterable<Athlete> findAll() {
		return athleteRepository.findAll();
		}
	
	@Transactional
	public void delete(Athlete athlete) {
		athleteRepository.delete(athlete);
	}
	@Transactional
	public void save(Athlete athlete) {
		athleteRepository.save(athlete);
		
	}
	
	@Transactional
	public void deleteAthleteById(int athleteId) {
		athleteRepository.deleteById(athleteId);
	}
	
	
	@Transactional
	public void saveSancion(Sanción sancion) throws DataAccessException {
		sancionRepo.save(sancion);
		
	}
	
	@Transactional(readOnly = true)
	public Athlete findAthleteById(int id) throws DataAccessException{
		return athleteRepository.findById(id).get();
	}
	
	public Set<Athlete> findAthleteByEntrenadorId(int entrenadorId) {
		return athleteRepository.findByEntrenadorId(entrenadorId);
	}
}
