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
		assertEquals("Interesado en una vivienda de 2 dormitorios", name);
	}

	@Test
	void listClienteContactoIdFail() {
		try {
			String name = this.contactoService.listOne(19).getMotivo();
			assertEquals("Interesado en una vivienda de 2 dormitorios", name);
		} catch (IllegalArgumentException e) {
			assertEquals("No existe un cliente con ese ID", e.getMessage());
		}
	}

	@Test
	void createContactoPass() {
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = new Contacto(MetodoContacto.EMAIL, "Porque estaba aburrido en casa", df.parse(fecha), null);
			contacto = this.contactoService.crearContacto(contacto, 1);
		} catch (Exception e) {

		}
		assertEquals("Antonio Arenas Arenas", contacto.getPersona().getNombre());
	}

	@Test
	void createContactoMetodoNullFail() {
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = this.contactoService
					.crearContacto(new Contacto(null, "Porque estaba aburrido en casa", df.parse(fecha), null), 1);
		} catch (Exception e) {

		}
		assertEquals(null, contacto);
	}

	@Test
	void createContactoMotivoEmptyFail() {
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = this.contactoService.crearContacto(new Contacto(MetodoContacto.EMAIL, "", df.parse(fecha), null),
					1);
		} catch (Exception e) {

		}
		assertEquals(null, contacto);
	}

	@Test
	void createContactoPersonaWrongFail() {
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Contacto contacto = null;
		try {
			contacto = this.contactoService.crearContacto(
					new Contacto(MetodoContacto.EMAIL, "Porque estaba aburrido en casa", df.parse(fecha), null), 50);
		} catch (Exception e) {

		}
		assertEquals(null, contacto);
	}

	@Test
	void actualizarContactoPass() {
		Contacto contacto = null;
		try {
			Contacto original = this.contactoService.listOne(2);
			original.setMotivo("Actualizacion estoy aburrido");
			contacto = this.contactoService.editar(original, 1);
		} catch (Exception e) {

		}
		assertEquals("Actualizacion estoy aburrido", contacto.getMotivo());

	}

	@Test
	void actualizarContactoMetodoNullFail() {
		Contacto contacto = null;
		try {
			Contacto original = this.contactoService.listOne(2);
			original.setMetodo(null);
			contacto = this.contactoService.editar(original, 1);
		} catch (Exception e) {

		}
		assertEquals(null, contacto);
	}

	@Test
	void actualizarContactoMotivoEmptyFail() {
		Contacto contacto = null;
		try {
			Contacto original = this.contactoService.listOne(2);
			original.setMotivo("");
			contacto = this.contactoService.editar(original, 1);
		} catch (Exception e) {

		}
		assertEquals(null, contacto);
	}

	@Test
	void actualizarContactoPersonaWrongFail() {
		Contacto contacto = null;
		try {
			Contacto original = this.contactoService.listOne(2);
			contacto = this.contactoService.editar(original, null);
		} catch (Exception e) {

		}
		assertEquals(null, contacto);
	}

	// Los test de borrado pueden falsear el resultado por lo que se deben ejecutar
	// individualmente

	@Test
	void borrarContactoPass() {
		try {
			this.contactoService.delete(1);
		} catch (Exception e) {

		}
		assertEquals(10, this.contactoService.list().size());

	}

	@Test
	void borrarContactoWrongIdFail() {
		try {
			this.contactoService.delete(15);
		} catch (Exception e) {

		}
		assertEquals(11, this.contactoService.list().size());
	}
}
