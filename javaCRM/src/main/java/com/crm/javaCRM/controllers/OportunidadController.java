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

import com.crm.javaCRM.model.Oportunidad;
import com.crm.javaCRM.services.OportunidadService;

/** Controlador de Oportunidad con los métodos CRUD */
@RestController
public class OportunidadController {

	@Autowired
	private OportunidadService oportunidadService;

	/**
	 * Método que lista todas las oportunidades en el sistema
	 * 
	 * @return lista de oportunidades
	 */
	@GetMapping("/oportunidades")
	List<Oportunidad> listado() {
		return oportunidadService.list();
	}

	/**
	 * Método que obtiene una oportunidad a partir de su ID
	 * 
	 * @param id el id de la oportunidad que se quiere obtener
	 * @return la oportunidad concreta o un mensaje de error si se produce algún
	 *         fallo
	 */
	@GetMapping("/oportunidades/{id}")
	Object uno(@PathVariable Integer id) {
		Oportunidad o;
		try {
			o = oportunidadService.listOne(id);
			return o;
		} catch (IllegalArgumentException i) {
			return i.getMessage();
		}
	}

	/**
	 * Método que crea una oportunidad nueva en la base de datos
	 * 
	 * @param nuevaOportunidad oportunidad a guardar en la base de datos
	 * @return la Oportunidad ya creada o un mensaje de error si se produce algún
	 *         fallo
	 */
	@PostMapping("/oportunidades")
	Object nuevaOportunidad(@RequestBody Oportunidad nuevaOportunidad) {
		try {
			return this.oportunidadService.crearOportunidad(nuevaOportunidad);
		} catch (IllegalArgumentException i) {
			return i.getMessage();
		}
	}

	/**
	 * Método para modificar una oportunidad según su ID
	 * 
	 * @param nuevaOportunidad la oportunidad con los datos actualizados
	 * @param id               el id de la oportunidad que queremos actualizar
	 * @return la oportunidad actualizada o un mensaje de error si se produce algún
	 *         fallo
	 */
	@PutMapping("/oportunidades")
	Object actualizarOportunidad(@RequestBody Oportunidad nuevaOportunidad) {
		try {
			return this.oportunidadService.editar(nuevaOportunidad);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Método que controla el borrado de una oportunidad según su ID
	 * 
	 * @param id ID de la oportunidad a borrar
	 * @return Un mensaje mostrando si se ha realizado correctamente o no el borrado
	 */
	@DeleteMapping("/oportunidades/{id}")
	String deleteEmployee(@PathVariable Integer id) {
		try {
			this.oportunidadService.delete(id);
			return "Borrado completado";
		} catch (Exception e) {
			return "Hubo un problema";
		}
	}
}
