package com.crm.javaCRM.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.model.Oportunidad;
import com.crm.javaCRM.repositories.OportunidadRepository;

@Service
@Transactional
public class OportunidadService {

	@Autowired
	private OportunidadRepository oportunidadRepository;

	@Autowired
	private ContactoService contactoService;

	// Metodos CRUD

	/**
	 * Método para el listado de todos los objetos Oportunidad
	 * 
	 * @return listado de objetos oportunidad
	 */
	public List<Oportunidad> list() {

		List<Oportunidad> lista = new LinkedList<>();
		lista = oportunidadRepository.findAll();
		return lista;
	}

	/**
	 * Obtención de una Oportunidad a partir de su ID
	 * 
	 * @param opId ID de la oportunidad
	 * @return la oportunidad obtenida
	 * @exception IllegalArgumentException si no existe el ID en cuestión
	 */
	public Oportunidad listOne(Integer opId) {
		Optional<Oportunidad> o;
		o = oportunidadRepository.findById(opId);
		Assert.isTrue(o.isPresent(), "No existe una oportunidad con ese ID");
		return o.get();

	}

	/**
	 * Guardado de una oportunidad en la base de datos
	 * 
	 * @param o oportunidad a guardar
	 * @return la oportunidad una vez ya guardada
	 * @exception IllegalArgumentException si la oportunidad tiene el nombre vacio o
	 *                                     su email o teléfono coinciden con uno ya
	 *                                     existente en la base de datos
	 */
	public Oportunidad crearOportunidad(Oportunidad o) {

		// Comprobamos que no es la misma persona creada dos veces, para ello miramos el
		// email y el teléfono que son las dos propiedades que pueden definir la
		// unicidad de una persona
		Assert.notNull(o, "Objeto pasado sin parámetros");
		Assert.notNull(o.getNombre(), "El cliente no tiene nombre!");
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
		if (o.getContactos() == null) {
			o.setContactos(new LinkedList<>());
		}
		creada = oportunidadRepository.save(o);
		return creada;
	}

	/**
	 * Actualización de una Oportunidad, usa el ID de la oportunidad pasada por
	 * parámetros como el ID con el que guardar
	 * 
	 * @param o Oportunidad con los datos ya actualizados
	 * @return la oportunidad actualizada tras ser guardada en la base de datos
	 * @exception IllegalArgumentException si no existe el ID en cuestión
	 */
	public Oportunidad editar(Oportunidad o) {
		Optional<Oportunidad> op = this.oportunidadRepository.findById(o.getId());
		Assert.isTrue(op.isPresent(), "No existe el id");
		Oportunidad editada = null;
		editada = this.oportunidadRepository.save(o);
		return editada;
	}

	/**
	 * Método para el borrado de una oportunidad a partir de su id
	 * 
	 * @param id ID de la oportunidad que debe ser borrada
	 * @exception IllegalArgumentException si no existe el ID en cuestión
	 */
	public void delete(Integer id) {

		Optional<Oportunidad> op = this.oportunidadRepository.findById(id);
		Assert.isTrue(op.isPresent(), "No existe el id");
		for (Contacto c : op.get().getContactos()) {
			this.contactoService.delete(c.getId());
		}
		this.oportunidadRepository.deleteById(id);
	}

	// TODO metodo login
}
