package com.crm.javaCRM;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.model.MetodoContacto;
import com.crm.javaCRM.services.ContactoService;

@SpringBootTest
public class JavaCrmApplicationContactoTest {

	@Autowired
	ContactoService contactoService;

	@Test
	void listContactoPass() {
		String name = this.contactoService.listOne(1).getMotivo();
		assertEquals(name, "Interesado en una vivienda de 2 dormitorios");
	}

	@Test
	void listClienteContactoIdFail() {
		try {
			String name = this.contactoService.listOne(19).getMotivo();
			assertEquals(name, "Interesado en una vivienda de 2 dormitorios");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No existe un cliente con ese ID");
		}
	}

	@Test
	void createContactoPass() {
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = new Contacto(MetodoContacto.email, "Porque estaba aburrido en casa", df.parse(fecha), null);
			contacto = this.contactoService.crearContacto(contacto, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(contacto.getPersona().getNombre(), "Antonio Arenas Arenas");
	}

	@Test
	void createContactoMetodoNullFail() {
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = new Contacto(null, "Porque estaba aburrido en casa", df.parse(fecha), null);
			contacto = this.contactoService.crearContacto(contacto, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(contacto, null);
	}

	@Test
	void createContactoMotivoEmptyFail() {
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = new Contacto(MetodoContacto.email, "", df.parse(fecha), null);
			contacto = this.contactoService.crearContacto(contacto, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(contacto, null);
	}

	@Test
	void createContactoFechaWrongFail() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = new Contacto(MetodoContacto.email, "Porque estaba aburrido en casa", df.parse("patata"), null);
			contacto = this.contactoService.crearContacto(contacto, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(contacto, null);
	}

	@Test
	void createContactoPersonaWrongFail() {
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = new Contacto(MetodoContacto.email, "Porque estaba aburrido en casa", df.parse(fecha), null);
			contacto = this.contactoService.crearContacto(contacto, 50);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(contacto, null);
	}

}
