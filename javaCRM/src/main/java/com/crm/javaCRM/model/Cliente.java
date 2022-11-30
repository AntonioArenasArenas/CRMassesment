package com.crm.javaCRM.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Clase que define la entidad cliente, tiene datos extras que se entiende se
 * consiguen al cerrar un negocio con una Oportunidad
 */
@Entity
public class Cliente extends Persona {

	private String dni;
	private int edad;
	private Collection<String> productos;

	public Cliente() {

	}

	public Cliente(String name, String email, String direccion, String telefono, Collection<Contacto> contactos) {
		super(name, email, direccion, telefono, contactos);
	}

	public Cliente(String dni, int edad, Collection<String> productos) {
		super();
		this.dni = dni;
		this.edad = edad;
		this.productos = productos;
	}

	@NotBlank
	@Column(unique = true)
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@NotNull
	public Collection<String> getProductos() {
		return productos;
	}

	public void setProductos(Collection<String> productos) {
		this.productos = productos;
	}

	public void setOportunidad(Oportunidad o) {
		this.setContactos(o.getContactos());
		this.setDireccion(o.getDireccion());
		this.setEmail(o.getEmail());
		this.setNombre(o.getNombre());
		this.setTelefono(o.getTelefono());
	}

}
