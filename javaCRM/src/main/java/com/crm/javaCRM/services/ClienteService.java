package com.crm.javaCRM.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.crm.javaCRM.model.Cliente;
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

	public Cliente listOne(Integer id) {
		Optional<Cliente> c;
		c = clienteRepository.findById(id);
		Assert.isTrue(c.isPresent(), "No existe un cliente con ese ID");
		return c.get();
	}

}
