package org.springframework.samples.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "entrenadores")
public class Entrenador extends Person {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entrenador")
	private Set<Athlete> athletes;
	
	public void addAthlete(Athlete athlete) {
		getAthletesInternal().add(athlete);
		athlete.setEntrenador(this);
	}
	
	protected Set<Athlete> getAthletesInternal() {
		if (this.athletes == null) {
			this.athletes = new HashSet<>();
		}
		return this.athletes;
	}
}

