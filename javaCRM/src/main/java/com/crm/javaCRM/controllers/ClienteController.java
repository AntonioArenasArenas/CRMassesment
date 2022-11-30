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

import com.crm.javaCRM.model.Cliente;
import com.crm.javaCRM.model.Oportunidad;
import com.crm.javaCRM.services.ClienteService;

/** Controlador de Oportunidad con los métodos CRUD */
@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * Método que lista todos los clientes en el sistema
	 * 
	 * @return lista de clientes
	 */
	@GetMapping("/clientes")
	List<Cliente> listado() {
		List<Cliente> clList = clienteService.list();
		return clList;
	}

	/**
	 * Método que obtiene un cliente a partir de su ID
	 * 
	 * @param id el id del cliente que se quiere obtener
	 * @return el cliente concreto o un mensaje de error si se produce algún fallo
	 */
	@GetMapping("/clientes/{id}")
	Object uno(@PathVariable Integer id) {
		Cliente c;
		try {
			c = clienteService.listOne(id);
			return c;
		} catch (IllegalArgumentException i) {
			return i.getMessage();
		}
	}

	/**
	 * Método que crea un cliente nuevo en la base de datos
	 * 
	 * @param nuevoCliente cliente a guardar en la base de datos
	 * @return el cliente ya creada o un mensaje de error si se produce algún fallo
	 */
	@PostMapping("/clientes")
	Object nuevoCliente(@RequestBody Cliente nuevoCliente) {
		try {
			Cliente creada = this.clienteService.crearCliente(nuevoCliente);
			return creada;
		} catch (IllegalArgumentException i) {
			return i.getMessage();
		}
	}
//
//	/**
//	 * Método para modificar una oportunidad según su ID
//	 * 
//	 * @param nuevaOportunidad la oportunidad con los datos actualizados
//	 * @param id               el id de la oportunidad que queremos actualizar
//	 * @return la oportunidad actualizada o un mensaje de error si se produce algún
//	 *         fallo
//	 */
//	@PutMapping("/oportunidades/{id}")
//	Object actualizarOportunidad(@RequestBody Oportunidad nuevaOportunidad, @PathVariable Integer id) {
//		try {
//			Oportunidad editada = this.oportunidadService.editar(nuevaOportunidad);
//			return editada;
//		} catch (Exception e) {
//			return e.getMessage();
//		}
//	}
//
//	/**
//	 * Método que controla el borrado de una oportunidad según su ID
//	 * 
//	 * @param id ID de la oportunidad a borrar
//	 * @return Un mensaje mostrando si se ha realizado correctamente o no el borrado
//	 */
//	@DeleteMapping("/oportunidades/{id}")
//	String deleteEmployee(@PathVariable Integer id) {
//		try {
//			this.oportunidadService.delete(id);
//			return "Borrado completado";
//		} catch (Exception e) {
//			return "Hubo un problema";
//		}
//	}
}
