package com.crm.javaCRM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.model.MetodoContacto;
import com.crm.javaCRM.model.Oportunidad;
import com.crm.javaCRM.services.OportunidadService;

@SpringBootTest
class JavaCrmApplicationTests {

	@Autowired
	OportunidadService oportunidadService;

	@Test
	void crearOportunidadPass() {
		String nombre = "Marcial";
		String email = "marcial@solera.com";
		String direccion = "Calle Pepe 26";
		String telefono = "123456987";
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

		List<Contacto> contactos = new LinkedList<>();
		Oportunidad o = new Oportunidad();
		Oportunidad creada = null;
		o.setNombre(nombre);
		o.setTelefono(telefono);
		o.setDireccion(direccion);
		o.setEmail(email);
		o.setContactos(contactos);
		creada = oportunidadService.crearOportunidad(o);
		try {
			o.getContactos().add(
					new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones", df.parse(fecha), o));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(creada);

	}

	@Test
	void crearOportunidadSameTelephoneWrong() {
		String nombre = "Jesus";
		String telefono = "638453173";
		Oportunidad o = new Oportunidad();
		Oportunidad creada = null;
		o.setNombre(nombre);
		o.setTelefono(telefono);
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), o));
		o.setContactos(contactos);
		try {
			creada = oportunidadService.crearOportunidad(o);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Este usuario ya está en la base de datos!");
		}
		assertEquals(creada, null);
	}

	@Test
	void crearOportunidadSameEmailWrong() {
		String nombre = "Jesus";
		String email = "antonio.arenas@solera.com";
		Oportunidad o = new Oportunidad();
		Oportunidad creada = null;
		o.setNombre(nombre);
		o.setEmail(email);
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), o));
		o.setContactos(contactos);
		try {
			creada = oportunidadService.crearOportunidad(o);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Este usuario ya está en la base de datos!");
		}
		assertEquals(creada, null);
	}

	@Test
	void crearOportunidadBlankNameWrong() {
		String nombre = "";
		String email = "antonio.arenas@solera.com";
		Oportunidad o = new Oportunidad();
		Oportunidad creada = null;
		o.setNombre(nombre);
		o.setEmail(email);
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), o));
		o.setContactos(contactos);
		try {
			creada = oportunidadService.crearOportunidad(o);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "El cliente no tiene nombre!");
		}
		assertEquals(creada, null);
	}

	@Test
	void editarOportunidadPass() {
		Oportunidad o = this.oportunidadService.listOne(1);
		o.setNombre("Triple A");
		Oportunidad editada = this.oportunidadService.editar(o);
		assertEquals(editada.getNombre(), "Triple A");
	}
}
