package com.crm.javaCRM.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Contacto {

	private long id;
	private MetodoContacto metodo;
	private String motivo;
	private Date fecha;
	private Persona persona;

	protected Contacto() {

	}

	public Contacto( MetodoContacto metodo, String motivo, Date fecha, Persona persona) {
		this.metodo = metodo;
		this.motivo = motivo;
		this.fecha = fecha;
		this.persona = persona;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotNull
	public MetodoContacto getMetodo() {
		return metodo;
	}

	public void setMetodo(MetodoContacto metodo) {
		this.metodo = metodo;
	}

	@NotBlank
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@ManyToOne(optional = false)
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
