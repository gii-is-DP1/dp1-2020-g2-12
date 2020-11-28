package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Sanción extends BaseEntity{
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFin;
	
	private String descripción;
	
	//de momento
	private String deportista;
}
