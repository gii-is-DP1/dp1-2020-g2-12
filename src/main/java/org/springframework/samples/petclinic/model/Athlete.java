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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;
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

	
}	
	
	
	
	
