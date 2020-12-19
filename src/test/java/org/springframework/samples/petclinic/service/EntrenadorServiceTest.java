package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EntrenadorServiceTest {
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	@Test
	public void testCountWithInitialData() {
		int count=entrenadorService.entrenadorCount();
		assertEquals(count,2);
	}
	
	@Test
	void shouldFindEntrenadorWithCorrectId() {
		Entrenador e = this.entrenadorService.findEntrenadorById(2);
		assertThat(e.getFirstName()).startsWith("Rosa");

	}
	
	@Test
	@Transactional
	public void shouldInsertEntrenador() {
		
		int count=this.entrenadorService.entrenadorCount();

		Entrenador ent = new Entrenador();
		ent.setFirstName("David");
		ent.setLastName("Muñoz");       
                
		this.entrenadorService.save(ent);

		assertThat(this.entrenadorService.entrenadorCount()).isEqualTo(count + 1);
	}
	
	@Test
	@Transactional
	void shouldUpdateEntrenador() {
		Entrenador e = this.entrenadorService.findEntrenadorById(1);
		String oldLastName = e.getLastName();
		String newLastName = oldLastName + "Fernández";

		e.setLastName(newLastName);
		this.entrenadorService.save(e);

		e = this.entrenadorService.findEntrenadorById(1);
		assertThat(e.getLastName()).isEqualTo(newLastName);
	}
	
	@Test
	@Transactional
	void shouldDeleteEntrenadorById() {		
		this.entrenadorService.deleteEntrenadorById(1);
		try {
			entrenadorService.findEntrenadorById(1);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
}
