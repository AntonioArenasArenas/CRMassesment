package com.crm.javaCRM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.services.ContactoService;

/** Controlador de Contacto con los métodos CRUD */
@RestController
public class ContactoController {

	@Autowired
	private ContactoService contactoService;

	/**
	 * Método que lista todos los contactos en el sistema
	 * 
	 * @return lista de contactos
	 */
	@GetMapping("/contactos")
	List<Contacto> listado() {
		return contactoService.list();
	}

	/**
	 * Método que obtiene un contacto a partir de su ID
	 * 
	 * @param id el id del contacto que se quiere obtener
	 * @return el contacto concreto o un mensaje de error si se produce algún fallo
	 */
	@GetMapping("/contactos/{id}")
	Object uno(@PathVariable Integer id) {
		Contacto c;
		try {
			c = contactoService.listOne(id);
			return c;
		} catch (IllegalArgumentException i) {
			return i.getMessage();
		}
	}

	/**
	 * Método que crea un Contacto , se le pasa el ID de la persona a la que
	 * pertenece por URL
	 * 
	 * @param id ID de la persona a la que pertenece el contacto
	 * @param c  contacto a crear
	 * @return el contacto creado o un mensaje de error si algo falla
	 */
	@PostMapping("/contactos/{id}")
	Object crearContacto(@PathVariable Integer id, @RequestBody Contacto c) {
		try {
			return this.contactoService.crearContacto(c, id);
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		}
	}

	/**
	 * Método que modifica un Contacto, se le pasa el ID de la persona a la que
	 * pertenece por URL
	 * 
	 * @param id ID de la persona a la que pertenece el contacto
	 * @param c  contacto modificado
	 * @return el contacto modificado o un mensaje de error si algo falla
	 */
	@PutMapping("/contactos/{id}")
	Object actualizarContacto(@PathVariable Integer id, @RequestBody Contacto c) {
		try {
			return this.contactoService.editar(c, id);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Método que borra un contacto
	 * 
	 * @param id ID del contacto a borrar
	 * @return Un mensaje que certifica si se ha borrado o no el contacto
	 */
	@DeleteMapping("/contactos/{id}")
	String borrarContacto(@PathVariable Integer id) {
		try {
			this.contactoService.delete(id);
			return "Borrado completado";
		} catch (Exception e) {
			return "Hubo un problema";
		}
	}

}
