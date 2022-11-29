package com.crm.javaCRM.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.crm.javaCRM.model.Oportunidad;
import com.crm.javaCRM.repositories.OportunidadRepository;

@Service
@Transactional
public class OportunidadService {

	@Autowired
	private OportunidadRepository oportunidadRepository;

	// Metodos CRUD

	public List<Oportunidad> list() {

		List<Oportunidad> lista = new LinkedList<>();
		lista = oportunidadRepository.findAll();
		return lista;
	}

	public Oportunidad listOne(Integer opId) {
		Optional<Oportunidad> o;
		o = oportunidadRepository.findById(opId);
		Assert.isTrue(o.isPresent(), "No existe una oportunidad con ese ID");
		return o.get();

	}

	public Oportunidad crearOportunidad(Oportunidad o) {

		// Comprobamos que no es la misma persona creada dos veces, para ello miramos el
		// email y el teléfono que son las dos propiedades que pueden definir la
		// unicidad de una persona
		Assert.notNull(o, "Objeto pasado sin parámetros");
		Assert.isTrue(!o.getNombre().isBlank(), "El cliente no tiene nombre!");
		Oportunidad creada = null;
		String email = o.getEmail();
		String telefono = o.getTelefono();
		List<Oportunidad> obbdd;
		if (email != null) {
			obbdd = oportunidadRepository.findByEmail(email);
			Assert.isTrue(obbdd.isEmpty(), "Este usuario ya está en la base de datos!");
		}
		if (telefono != null) {
			obbdd = oportunidadRepository.findByTelefono(telefono);
			Assert.isTrue(obbdd.isEmpty(), "Este usuario ya está en la base de datos!");
		}
		creada = oportunidadRepository.save(o);
		return creada;
	}

	public Oportunidad editar(Oportunidad o) {
		Optional<Oportunidad> op = this.oportunidadRepository.findById(o.getId());
		Assert.isTrue(op.isPresent(), "No existe el id");
		Oportunidad editada = null;
		editada = this.oportunidadRepository.save(o);
		return editada;
	}

}
