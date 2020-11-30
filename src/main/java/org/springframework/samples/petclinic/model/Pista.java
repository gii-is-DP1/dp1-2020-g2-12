package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "pistas")
public class Pista extends NamedEntity{

	@Min(0)
	private Integer aforo;
	@NotBlank
	private String ciudad;
	@ManyToOne
	@JoinColumn(name = "deporte_id")
	private Deporte deporte;
}
