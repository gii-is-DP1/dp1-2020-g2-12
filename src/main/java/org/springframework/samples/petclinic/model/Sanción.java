package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "sanciones")
public class Sanción extends BaseEntity{
	
	@Column(name = "fecha_fin") 
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFin;
	
	@Column(name = "descripcion") 
	private String descripción;
	
	@ManyToOne
	@JoinColumn(name = "athlete_id")
	private Athlete athlete;
	
	
}
