package com.crm.javaCRM.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cliente extends Persona {

	private String dni;
	private int edad;
	private Collection<String> productos;

	protected Cliente() {

	}

	/**
	 * Es probable que no se necesite el constructor con parámetros de Persona al
	 * ser un cliente una oportunidad ya guardada que se convierte en cliente
	 */
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

}
