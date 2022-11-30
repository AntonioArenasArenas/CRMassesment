package com.crm.javaCRM.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.crm.javaCRM.model.Cliente;
import com.crm.javaCRM.model.Oportunidad;
import com.crm.javaCRM.repositories.ClienteRepository;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	// Metodos CRUD

	/**
	 * Método para el listado de todos los objetos Cliente
	 * 
	 * @return listado de objetos cliente
	 */
	public List<Cliente> list() {
		List<Cliente> lista = new LinkedList<>();
		lista = clienteRepository.findAll();
		return lista;
	}

	/**
	 * Obtención de un cliente a partir de su ID
	 * 
	 * @param opId ID del cliente
	 * @return el cliente obtenido
	 * @exception IllegalArgumentException si no existe el ID en cuestión
	 */
	public Cliente listOne(Integer id) {
		Optional<Cliente> c;
		c = clienteRepository.findById(id);
		Assert.isTrue(c.isPresent(), "No existe un cliente con ese ID");
		return c.get();
	}

	/**
	 * Guardado de un cliente en la base de datos
	 * 
	 * @param c cliente a guardar
	 * @return el cliente una vez ya guardado
	 * @exception IllegalArgumentException si la oportunidad tiene el nombre vacio o
	 *                                     su email o teléfono coinciden con uno ya
	 *                                     existente en la base de datos
	 */
	public Cliente crearCliente(Cliente c) {
		// Comprobamos que no es la misma persona creada dos veces, para ello miramos el
		// email y el teléfono que son las dos propiedades que pueden definir la
		// unicidad de una persona
		Assert.notNull(c, "Objeto pasado sin parámetros");
		Assert.notNull(c.getNombre(), "El cliente no tiene nombre");
		Assert.isTrue(!c.getNombre().isBlank(), "El cliente no tiene nombre!");
		Assert.notNull(c.getDni(), "El cliente no tiene DNI!");
		Assert.isTrue(!c.getDni().isBlank(), "El cliente no tiene DNI!");
		Cliente creado = null;
		String email = c.getEmail();
		String telefono = c.getTelefono();
		String dni = c.getDni();
		List<Cliente> obbdd;
		if (email != null) {
			obbdd = clienteRepository.findByEmail(email);
			Assert.isTrue(obbdd.isEmpty(), "Este usuario ya está en la base de datos!");
		}
		if (telefono != null) {
			obbdd = clienteRepository.findByTelefono(telefono);
			Assert.isTrue(obbdd.isEmpty(), "Este usuario ya está en la base de datos!");
		}
		obbdd = clienteRepository.findByDni(dni);
		Assert.isTrue(obbdd.isEmpty(), "Este usuario ya está en la base de datos!");
		if (c.getContactos() == null) {
			c.setContactos(new LinkedList<>());
		}
		creado = clienteRepository.save(c);
		return creado;
	}

	/**
	 * Método utilizado para crear un cliente a partir de una oportunidad
	 * 
	 * @param o Oportunidad original
	 * @param c Cliente con los datos extras de la clase Cliente
	 */
	public Cliente crearClienteOportunidad(Oportunidad o, Cliente c) {

		// Pasamos los campos correspondientes de la oportunidad al cliente
		c.setOportunidad(o);

		// TODO recorrer los contactos de o y actualizarlos a cliente guardando primero
		// cliente
		return null;

	}

	/**
	 * Actualización de un cliente, usa el ID del cliente pasado por parámetros como
	 * el ID con el que guardar
	 * 
	 * @param c Cliente con los datos ya actualizados
	 * @return el cliente actualizado tras ser guardado en la base de datos
	 * @exception IllegalArgumentException si no existe el ID en cuestión
	 */
	public Cliente editar(Cliente c) {
		Optional<Cliente> op = this.clienteRepository.findById(c.getId());
		Assert.isTrue(op.isPresent(), "No existe el id");
		Cliente editado = null;
		// TODO recorrer todos los contactos y guardarlos
		editado = this.clienteRepository.save(c);
		return editado;
	}

	/**
	 * Método para el borrado de un cliente a partir de su id
	 * 
	 * @param id ID del cliente que debe ser borrado
	 * @exception IllegalArgumentException si no existe el ID en cuestión
	 */
	public void delete(Integer id) {

		Optional<Cliente> op = this.clienteRepository.findById(id);
		Assert.isTrue(op.isPresent(), "No existe el id");
		// TODO borrar todos los contactos??
		this.clienteRepository.deleteById(id);
	}

}
