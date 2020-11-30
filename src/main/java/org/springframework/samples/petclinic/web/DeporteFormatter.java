package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Deporte;
import org.springframework.samples.petclinic.service.PistaService;

public class DeporteFormatter implements Formatter<Deporte>{
	
	private final PistaService service;
	
	@Autowired
	public DeporteFormatter(PistaService service) {
		this.service = service;
	}

	@Override
	public String print(Deporte deporte, Locale locale) {
		// TODO Auto-generated method stub
		return deporte.getName();
	}

	@Override
	public Deporte parse(String text, Locale locale) throws ParseException {
		Collection<Deporte> findDeportes = this.service.findDeportes();
		for (Deporte type : findDeportes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	
	}

}
