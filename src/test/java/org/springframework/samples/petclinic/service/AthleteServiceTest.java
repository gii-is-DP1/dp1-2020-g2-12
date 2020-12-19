package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AthleteServiceTest {
	
	@Autowired
	private AthleteService athleteService;
	
	@Test
	public void testCountWithInitialData() {
		int count=athleteService.athleteCount();
		assertEquals(count,3);
	}
	
	@Test
	public void testCountAthleteByEntrenador() {
		Set<Athlete> atletas = this.athleteService.findAthleteByEntrenadorId(1);
		assertEquals(2,atletas.size());
	}
	
	@Test
	public void shouldDeleteEntrenadorDeAtleta() {
		this.athleteService.eliminarEntrenadorDeAtleta(1);
		Athlete atleta = this.athleteService.findAthleteById(1);
		assertEquals(null,atleta.getEntrenador());
	}
	
	@Test
	void shouldFindAthleteWithCorrectId() {
		Athlete e = this.athleteService.findAthleteById(1);
		assertThat(e.getFirstName()).startsWith("Lucas");

	}
	
	@Test
	@Transactional
	public void shouldInsertEntrenador() {
		int count=this.athleteService.athleteCount();
		Athlete ath = new Athlete();
		ath.setFirstName("David");
		ath.setLastName("Muñoz");       
		this.athleteService.save(ath);
		assertThat(this.athleteService.athleteCount()).isEqualTo(count + 1);
	}
	
	@Test
	@Transactional
	void shouldUpdateAthlete() {
		Athlete ath = this.athleteService.findAthleteById(1);
		String oldLastName = ath.getLastName();
		String newLastName = oldLastName + "Fernández";

		ath.setLastName(newLastName);
		this.athleteService.save(ath);

		ath = this.athleteService.findAthleteById(1);
		assertThat(ath.getLastName()).isEqualTo(newLastName);
	}
	
	@Test
	@Transactional
	void shouldDeleteEntrenadorById() {		
		this.athleteService.deleteAthleteById(1);
		try {
			athleteService.findAthleteById(1);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

}
