package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Pista extends NamedEntity{

	private Integer aforo;
	private String ciudad;
	private String nombre;
}
