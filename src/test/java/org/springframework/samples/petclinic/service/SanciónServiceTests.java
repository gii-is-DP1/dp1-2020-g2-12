package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sanción;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SanciónServiceTests {
	
	@Autowired
	private SanciónService sancionService;
	
	@Test
	public void testCountWithInitialData() {
		int count=sancionService.sancCount();
		assertEquals(count,3);
	}
//	
//	@Test
//	public void SancionesIdDeportista() {
//		Set<Sanción> sanciones=sancionService.findSancionByDeportistaId(3);
//		assertEquals(sanciones.size(),1);
//	}
//	
//	@Test
//	public void testEsDeportistaSancionadoFechafutura() {
//		boolean sancionado=sancionService.esSancionado(3);
//		assertEquals(sancionado,true);
//	}
//	@Test
//	public void testEsDeportistaSancionadoFechaAnterior() {
//		boolean sancionado=sancionService.esSancionado(2);
//		assertEquals(sancionado,false);
//	}
//	@Test
//	public void testEsDeportistaNoSancionado() {
//		boolean sancionado=sancionService.esSancionado(1);
//		assertEquals(sancionado,false);
//	}
//	
	
}
