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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.service.AthleteService;
import org.springframework.samples.petclinic.service.SanciónService;
import org.springframework.samples.petclinic.service.exceptions.AddParticipanteSancionadoException;
import org.springframework.samples.petclinic.service.exceptions.IncongruentSancionDateExcepcion;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "torneos")
public class Torneo extends NamedEntity{

//	@Autowired
//	private SanciónService sancionService;
	
	//cuando se importe deportista cambiar la clase, cuando se inicialize que participantes sea un cojunto vacio
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Athlete> participantes;
	@Column(name = "fecha_inicio")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFin;
	
	protected Set<Athlete> getParticipantesInternal() {
		if (this.participantes == null) {
			this.participantes = new HashSet<>();
		}
		return this.participantes;
	}
	
	protected void setParticipantesInternal(Set<Athlete> participantes) {
		this.participantes = participantes;
	}

	public List<Athlete> getVisits() {
		List<Athlete> sortedParticipantes = new ArrayList<>(getParticipantesInternal());
		PropertyComparator.sort(sortedParticipantes, new MutableSortDefinition("date", false, false));
		return Collections.unmodifiableList(sortedParticipantes);
	}

	@Transactional (rollbackFor = AddParticipanteSancionadoException.class)
	public void addParticipante(Athlete athlete) throws AddParticipanteSancionadoException{
//		if (!sancionService.esSancionado(athlete.getId())){
			getParticipantesInternal().add(athlete);
			athlete.addTorneo(this);
//		}else {
//			throw new AddParticipanteSancionadoException();
//		}
		
	}
	
}
