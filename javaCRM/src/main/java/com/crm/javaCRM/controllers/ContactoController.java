package com.crm.javaCRM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		List<Contacto> clList = contactoService.list();
		return clList;
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

	@PostMapping("/contactos/{id}")
	Object crearContacto(@PathVariable Integer id, @RequestBody Contacto c) {
		try {
			Contacto creado = this.contactoService.crearContacto(c, id);
			return creado;
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		}
	}
}
