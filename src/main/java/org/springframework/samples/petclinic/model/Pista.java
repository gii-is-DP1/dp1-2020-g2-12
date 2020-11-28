package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Pista extends NamedEntity{

	@Min(0)
	private Integer aforo;
	@NotBlank
	private String ciudad;
}
