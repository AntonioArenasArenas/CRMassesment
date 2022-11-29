package com.crm.javaCRM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.javaCRM.model.Oportunidad;
import com.crm.javaCRM.services.OportunidadService;

@RestController
public class OportunidadController {

	@Autowired
	private OportunidadService oportunidadService;

	@GetMapping("/oportunidades")
	List<Oportunidad> listado() {
		List<Oportunidad> opList = oportunidadService.list();
		return opList;
	}

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

	@PostMapping("/oportunidades")
	Object nuevaOportunidad(@RequestBody Oportunidad nuevaOportunidad) {
		try {
			Oportunidad creada = this.oportunidadService.crearOportunidad(nuevaOportunidad);
			return creada;
		} catch (IllegalArgumentException i) {
			return i.getMessage();
		}
	}

	@PutMapping("/oportunidades/{id}")
	Object actualizarOportunidad(@RequestBody Oportunidad nuevaOportunidad, @PathVariable Integer id) {
		try {
			Oportunidad editada = this.oportunidadService.editar(nuevaOportunidad);
			return editada;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

//	@DeleteMapping("/employees/{id}")
//	void deleteEmployee(@PathVariable Long id) {
//		repository.deleteById(id);
//	}
}
