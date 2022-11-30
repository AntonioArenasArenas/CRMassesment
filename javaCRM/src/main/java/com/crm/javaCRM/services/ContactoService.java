package com.crm.javaCRM.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.model.Persona;
import com.crm.javaCRM.repositories.ContactoRepository;
import com.crm.javaCRM.repositories.PersonaRepository;

@Service
@Transactional
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

	@Autowired
	private PersonaRepository personaRepository;

	// Metodos CRUD

	/**
	 * Método para el listado de todos los objetos Contacto
	 * 
	 * @return listado de objetos contacto
	 */
	public List<Contacto> list() {
		List<Contacto> lista = new LinkedList<>();
		lista = contactoRepository.findAll();
		return lista;
	}

	/**
	 * Obtención de un contacto a partir de su ID
	 * 
	 * @param opId ID del contacto
	 * @return el contacto obtenido
	 * @exception IllegalArgumentException si no existe el ID en cuestión
	 */
	public Contacto listOne(Integer id) {
		Optional<Contacto> c;
		c = contactoRepository.findById(id);
		Assert.isTrue(c.isPresent(), "No existe un cliente con ese ID");
		return c.get();
	}

	/**
	 * Creación de un contacto
	 * 
	 * @param contacto el contacto que queremos crear, se pasará con la Persona
	 *                 puesta a null
	 * @param id       id de la persona a la que pertenece el contacto
	 * 
	 * @return el contacto ya creado
	 * 
	 * @exception IllegalArgumentException si el ID de la persona no existe, el
	 *                                     motivo está vacío, el método está vacío o
	 *                                     la fecha esta vacía
	 */
	public Contacto crearContacto(Contacto contacto, Integer id) {
		// Comprobación ID
		Optional<Persona> personaContacto = personaRepository.findById(id);
		Assert.isTrue(personaContacto.isPresent(), "No existe el usuario relacionado con este contacto!");

		// Comprobación método
		Assert.notNull(contacto.getMetodo(), "No existe un método de contacto");

		// Comprobación motivo
		Assert.notNull(contacto.getMotivo(), "No existe un motivo de contacto");
		Assert.isTrue(!contacto.getMotivo().isBlank(), "No existe un motivo de contacto");

		// Comprobacion de fecha
		Assert.notNull(contacto.getFecha(), "No existe fecha para este contacto!");

		// Actualización de persona y guardado del contacto
		Persona personaGuardar = personaContacto.get();
		personaGuardar.getContactos().add(contacto);
		Persona guardada = this.personaRepository.save(personaGuardar);
		contacto.setPersona(guardada);
		return this.contactoRepository.save(contacto);
	}

	/**
	 * Método para actualizar los contactos
	 * 
	 * @param actualizado contacto nuevo modificado
	 * @param personId    ID de la persona a la que pertenece el contacto
	 */
	public Contacto editar(Contacto actualizado, Integer personId) {
		// Comprobaciones acerca del contacto general
		Assert.notNull(actualizado, "El Contacto no existe");
		Optional<Contacto> original = this.contactoRepository.findById(actualizado.getId());
		Assert.isTrue(original.isPresent(), "No existe dicho contacto");
		Contacto editado = null;
		// Comprobación de la persona pasada por ID
		Assert.notNull(personId, "No existe la persona asociada a este contacto");
		Optional<Persona> persona = this.personaRepository.findById(personId);
		Assert.isTrue(persona.isPresent(), "No existe dicho contacto");
		actualizado.setPersona(persona.get());
		editado = this.contactoRepository.save(actualizado);
		return editado;
	}

}
