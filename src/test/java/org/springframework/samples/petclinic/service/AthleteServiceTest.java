package org.springframework.samples.petclinic.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.stereotype.Service;

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
	public void shouldDeleteAthleteByEntrenador() {
		this.athleteService.eliminarEntrenadorDeAtleta(1);
		Athlete atleta = this.athleteService.findAthleteById(1);
		assertEquals(null,atleta.getEntrenador());
	}

}