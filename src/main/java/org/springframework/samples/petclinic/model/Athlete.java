package org.springframework.samples.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "athletes")
public class Athlete extends Person{
	
	@Column(name = "height")
	
	private Double height;
	
	@Column(name = "weight")
	
	private Double weight;
	
	@Column(name = "genero")
	private String genero;
	
	private String sanctioned;
	
	@ManyToOne
	@JoinColumn(name = "entrenador_id")
	private Entrenador entrenador;
	
	
	
	
	
	

	
	
	
	
	

}
