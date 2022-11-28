package com.crm.javaCRM.model;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona {

	private long id;
	private String nombre;
	private String email;
	private String direccion;
	private String telefono;
	private Collection<Contacto> contactos;

	protected Persona() {

	}

	public Persona(String nombre, String email, String direccion, String telefono, List<Contacto> contactos) {
		this.nombre = nombre;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.contactos = contactos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotBlank
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(unique = true)
	@Pattern(regexp = "^([A-z0-9 ]{1,}<[A-z0-9]{1,}@[A-z0-9.]{0,}>|[A-z0-9]{1,}@[A-z0-9.]{0,})$")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "persona")
	public Collection<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(Collection<Contacto> contactos) {
		this.contactos = contactos;
	}

}
