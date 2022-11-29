package com.crm.javaCRM.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.javaCRM.model.Cliente;
import com.crm.javaCRM.model.Oportunidad;
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

	public Cliente listOne(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
