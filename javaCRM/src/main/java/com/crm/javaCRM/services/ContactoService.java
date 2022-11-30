package com.crm.javaCRM.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.repositories.ContactoRepository;

@Service
@Transactional
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

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

	public Contacto crearContacto(Contacto contacto, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
