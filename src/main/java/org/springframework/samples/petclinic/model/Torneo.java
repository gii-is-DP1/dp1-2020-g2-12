package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Torneo extends NamedEntity{

	
	//cuando se importe deportista cambiar la clase
	//@Column(name = "participantes")
	//private Set<Persona> participantes;
	@Column(name = "fecha_inicio")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFin;
	
}
