package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;




//@MappedSuperclass
@Data
@Entity
public class Persona extends BaseEntity{
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
	@Column(name = "apellidos")
	@NotEmpty
	private String apellidos;
	
	@Column(name = "dni")
	@NotEmpty
	private String dni;
	
	@Column(name = "email")
	private String email;
	
	
	@Column(name = "fecha_nacimiento")
	@DateTimeFormat(pattern ="yyyy/MM/dd")
	private Date fechaNacimiento;
	
	@Column(name = "es_juez")
	private Boolean esJuez;
	
	

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setLastName(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	

	public Boolean getEsJuez() {
		return this.esJuez;
	}
	
	public void setEsJuez(Boolean esJuez) {
		this.esJuez = esJuez;
	}
	
	
}
