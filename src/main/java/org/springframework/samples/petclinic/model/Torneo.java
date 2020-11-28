package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Torneo extends NamedEntity{

	
	//cuando se importe deportista cambiar la clase, cuando se inicialize que participantes sea un cojunto vacio
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Persona> participantes;
	@Column(name = "fecha_inicio")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFin;
	
	protected Set<Persona> getParticipantesInternal() {
		if (this.participantes == null) {
			this.participantes = new HashSet<>();
		}
		return this.participantes;
	}
	
	protected void setParticipantesInternal(Set<Persona> participantes) {
		this.participantes = participantes;
	}

	public List<Persona> getVisits() {
		List<Persona> sortedParticipantes = new ArrayList<>(getParticipantesInternal());
		PropertyComparator.sort(sortedParticipantes, new MutableSortDefinition("date", false, false));
		return Collections.unmodifiableList(sortedParticipantes);
	}

	public void addParticipante(Persona persona) {
		getParticipantesInternal().add(persona);
		//deportista.addTorneo(torneo);
	}
	
}
