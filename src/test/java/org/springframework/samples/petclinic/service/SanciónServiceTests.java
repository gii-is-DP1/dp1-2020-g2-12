package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Sanción;
import org.springframework.samples.petclinic.service.exceptions.IncongruentSancionDateExcepcion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SanciónServiceTests {
	
	@Autowired
	private SanciónService sancionService;
	@Autowired
	private AthleteService athleteService;
	
	@Test
	public void testCountWithInitialData() {
		int count=sancionService.sancCount();
		assertEquals(count,3);
	}
	@ParameterizedTest
	@ValueSource(ints = { 1,3})
	void testEsDeportistaSancionado(int argument) {
	assertTrue(sancionService.esSancionado(argument));
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 2,4})
	void testEsDeportistaNoSancionado(int argument) {
	assertFalse(sancionService.esSancionado(argument));
	}
	
	@Test
	public void SancionesByDeportista() {
		Set<Sanción> sanciones=sancionService.findSancionByAthleteId(1);
		assertEquals(sanciones.size(),2);
	}
	
	@Test
	public void testSancionarDeportista() {
		Sanción s=new Sanción();
		Integer nSanciones = sancionService.sancCount();
		Athlete a4= athleteService.findAthleteById(4);
		s.setDescripcion("Descripcion no vacia");
		s.setFechaFin(LocalDate.of(2021, 1, 23));
		s.setAthlete(a4);
		try {
			sancionService.saveSancion(s);
			a4.addSancion(s);
		} catch (IncongruentSancionDateExcepcion e) {
			e.printStackTrace();
		}
		athleteService.save(a4);
		boolean sancionado=sancionService.esSancionado(a4.getId());
		assertTrue(sancionado);
		assertThat(sancionService.sancCount()).isEqualTo(nSanciones+1);
	}
	
	
	@Test
	public void testSancionarDeportistaFechaAnterior() {
		Integer nSanciones = sancionService.sancCount();
		Sanción s=new Sanción();
		Athlete a= athleteService.findAthleteById(4);
		s.setDescripcion("Descripcion no vacia");
		s.setFechaFin(LocalDate.of(2019, 1, 23));
		s.setAthlete(a);
		try {
			sancionService.saveSancion(s);
			a.addSancion(s);
		} catch (IncongruentSancionDateExcepcion e) {
			e.printStackTrace();
		}
		athleteService.save(a);
		Assertions.assertThrows(IncongruentSancionDateExcepcion.class, () ->{
			sancionService.saveSancion(s);
		});
		assertThat(sancionService.sancCount()).isEqualTo(nSanciones);
	}
	
	@Test
	public void testSancionarDeportistaFechaActual() {
		Integer nSanciones=sancionService.sancCount();
		Sanción s=new Sanción();
		Athlete a= athleteService.findAthleteById(4);
		s.setDescripcion("Descripcion no vacia");
		s.setFechaFin(LocalDate.now());
		s.setAthlete(a);
		try {
			sancionService.saveSancion(s);
			a.addSancion(s);
		} catch (IncongruentSancionDateExcepcion e) {
			e.printStackTrace();
		}
		athleteService.save(a);
		Assertions.assertThrows(IncongruentSancionDateExcepcion.class, () ->{
			sancionService.saveSancion(s);
		});	
		assertThat(sancionService.sancCount()).isEqualTo(nSanciones);
	}

	@Test
	public void testSancionarDeportistaFechaNula() {
		Integer nSanciones=sancionService.sancCount();
		Sanción s=new Sanción();
		Athlete a= athleteService.findAthleteById(4);
		s.setDescripcion("Descripcion no vacia");
		s.setFechaFin(null);
		s.setAthlete(a);
		try {
			sancionService.saveSancion(s);
			a.addSancion(s);
		} catch (IncongruentSancionDateExcepcion e) {
			e.printStackTrace();
		}
		athleteService.save(a);
		Assertions.assertThrows(IncongruentSancionDateExcepcion.class, () ->{
			sancionService.saveSancion(s);
		});		
		assertThat(sancionService.sancCount()).isEqualTo(nSanciones);
	}
	
	@Test
	public void eliminarSancion() {
		Integer nSanciones=sancionService.sancCount();
		Sanción s = sancionService.findSancionById(1);
		sancionService.deleteSancion(s);
		
		assertThat(sancionService.sancCount()).isEqualTo(nSanciones-1);
	}
	
	@Test
	public void eliminarSancionNoExistente() {
		Integer nSanciones=sancionService.sancCount();
		try {
			Sanción s = sancionService.findSancionById(5);
			sancionService.deleteSancion(s);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		Assertions.assertThrows(NoSuchElementException.class, () ->{
			Sanción s = sancionService.findSancionById(5);
			sancionService.deleteSancion(s);
		});
		assertThat(sancionService.sancCount()).isEqualTo(nSanciones);
	}
}
