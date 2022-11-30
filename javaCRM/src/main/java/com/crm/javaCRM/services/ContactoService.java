package com.crm.javaCRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.repositories.ContactoRepository;

@Service
@Transactional
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

	public Contacto listOne(int i) {
		return new Contacto(null, null, null, null);
	}

}
