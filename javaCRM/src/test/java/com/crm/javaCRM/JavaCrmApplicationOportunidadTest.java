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
class JavaCrmApplicationOportunidadTest {

	@Autowired
	OportunidadService oportunidadService;

	@Test
	void listOportunidadPass() {
		String name = this.oportunidadService.listOne(1).getNombre();
		assertEquals(name, "Antonio Arenas Arenas");
	}

	@Test
	void listOportunidadWrongIdFail() {
		try {
			String name = this.oportunidadService.listOne(16).getNombre();
			assertEquals(name, "Antonio Arenas Arenas");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No existe una oportunidad con ese ID");
		}
	}

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
	void crearOportunidadNullNameWrong() {
		String email = "antonio.arenas@solera.com";
		Oportunidad o = new Oportunidad();
		Oportunidad creada = null;
		o.setNombre(null);
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

	@Test
	void editarOportunidadCorreoIncorrectoFail() {
		Oportunidad editada = null;
		Oportunidad o = this.oportunidadService.listOne(1);
		o.setEmail("@@@.2@@");
		try {
			editada = this.oportunidadService.editar(o);
		} catch (Exception e) {

		}
		assertEquals(editada, null);

	}

	@Test
	void editarOportunidadTelefonoExistenteFail() {
		Oportunidad editada = null;
		Oportunidad o = this.oportunidadService.listOne(2);
		o.setTelefono("638453173");
		try {
			editada = this.oportunidadService.editar(o);
		} catch (Exception e) {
		}
		assertEquals(editada, null);
	}

	@Test
	void editarOportunidadNombreVacioFail() {
		Oportunidad editada = null;
		Oportunidad o = this.oportunidadService.listOne(1);
		o.setNombre("");
		try {
			editada = this.oportunidadService.editar(o);
		} catch (Exception e) {
		}
		assertEquals(editada, null);
	}

	@Test
	void editarOportunidadNombreNullFail() {
		Oportunidad editada = null;
		Oportunidad o = this.oportunidadService.listOne(1);
		o.setNombre(null);
		try {
			editada = this.oportunidadService.editar(o);
		} catch (Exception e) {
		}
		assertEquals(editada, null);
	}

	// Los métodos de borrado pueden falsear el resultado por lo que deben
	// ejecutarse individualmente
	@Test
	void borrarOportunidadPass() {
		try {
			this.oportunidadService.delete(1);
		} catch (Exception e) {

		}
		assertEquals(this.oportunidadService.list().size(), 3);
	}

	@Test
	void borrarOportunidadIdNotExistFail() {
		try {
			this.oportunidadService.delete(50);
		} catch (Exception e) {

		}
		assertEquals(this.oportunidadService.list().size(), 4);
	}

	// TODO metodo login
}
