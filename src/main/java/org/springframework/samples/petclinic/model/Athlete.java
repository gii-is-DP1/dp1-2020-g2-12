package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.samples.petclinic.service.exceptions.AddParticipanteSancionadoException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "athletes")
public class Athlete extends Person{

	@Column(name = "height") 
	private Double height;
	
	@Column(name = "weight")
	private Double weight;
	
	@Column(name = "genero")
	private Genero genero;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "athlete")
	private Set<Sanción> sanciones;
	
	protected Set<Sanción> getSancionesInternal(){
		if (this.sanciones == null) {
			this.sanciones = new HashSet<>();
		}
		return this.sanciones;
	}

	protected void setSancionesInternal(Set<Sanción> sanciones) {
		this.sanciones = sanciones;
	}
	

	public void addSancion(Sanción sancion) {
		getSancionesInternal().add(sancion);
		sancion.setAthlete(this);
	}
	
	
	@ManyToOne
	@JoinColumn(name = "entrenador_id")
	private Entrenador entrenador;
	
	@ManyToMany
	@JoinColumn(name = "torneo_id")
	private Set<Torneo> torneos;
	
	protected Set<Torneo> getTorneosInternal(){
		if (this.torneos == null) {
			this.torneos = new HashSet<>();
		}
		return this.torneos;
	}

	protected void setTorneosInternal(Set<Torneo> torneos) {
		this.torneos = torneos;
	}
	

	public void addTorneo(Torneo torneo) {
		try {
			torneo.addParticipante(this);
			getTorneosInternal().add(torneo);
		} catch (AddParticipanteSancionadoException e) {
			e.printStackTrace();
		}
	}
	
	public Set<Torneo> getTorneos() {
		return this.getTorneosInternal();
	}
}	
	
	
	
	
