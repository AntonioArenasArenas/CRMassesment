package com.crm.javaCRM;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.javaCRM.services.ContactoService;

@SpringBootTest
public class JavaCrmApplicationContactoTest {

	@Autowired
	ContactoService contactoService;

	@Test
	void listClientePass() {
		String name = this.contactoService.listOne(0).getMotivo();
		assertEquals(name, "Interesado en una vivienda de 2 dormitorios");
	}

	@Test
	void listClienteWrongIdFail() {
		try {
			String name = this.contactoService.listOne(19).getMotivo();
			assertEquals(name, "Interesado en una vivienda de 2 dormitorios");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No existe un cliente con ese ID");
		}
	}
}
