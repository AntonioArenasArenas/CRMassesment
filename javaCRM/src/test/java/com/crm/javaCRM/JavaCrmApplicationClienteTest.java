package com.crm.javaCRM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.javaCRM.model.Cliente;
import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.model.MetodoContacto;
import com.crm.javaCRM.services.ClienteService;

@SpringBootTest
class JavaCrmApplicationClienteTest {

	@Autowired
	ClienteService clienteService;

	// Aunque es el primer cliente, tiene ID 5 porque todos son personas y hay 4
	// oportunidades creadas
	@Test
	void listClientePass() {
		String name = this.clienteService.listOne(5).getNombre();
		assertEquals(name, "Pepe");
	}

	@Test
	void listClienteWrongIdFail() {
		try {
			String name = this.clienteService.listOne(16).getNombre();
			assertEquals(name, "Pepe");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No existe un cliente con ese ID");
		}
	}

	@Test
	void crearClientePass() {
		String nombre = "Cliente 1";
		String email = "cliente1@solera.com";
		String direccion = "Calle Pepe 26";
		String telefono = "004630057";
		String fecha = "07/10/22";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

		List<Contacto> contactos = new LinkedList<>();
		Cliente c = new Cliente();
		Cliente creada = null;
		c.setNombre(nombre);
		c.setTelefono(telefono);
		c.setDireccion(direccion);
		c.setEmail(email);
		c.setContactos(contactos);
		c.setDni("77845374A");
		c.setProductos(new ArrayList<String>());
		creada = clienteService.crearCliente(c);
		try {
			c.getContactos().add(
					new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones", df.parse(fecha), c));
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertNotNull(creada);

	}

	@Test
	void crearClienteSameTelephoneWrong() {
		String nombre = "Jesus";
		String telefono = "6587452396";
		Cliente c = new Cliente();
		Cliente creada = null;
		c.setNombre(nombre);
		c.setTelefono(telefono);
		c.setDni("77845374C");
		c.setProductos(new ArrayList<String>());
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Este usuario ya está en la base de datos!");
		}
		assertEquals(creada, null);
	}

	@Test
	void crearClienteSameEmailWrong() {
		String nombre = "Jesus";
		String email = "emailvalido1@gmail.com";
		Cliente c = new Cliente();
		Cliente creada = null;
		c.setNombre(nombre);
		c.setEmail(email);
		c.setDni("77845374D");
		c.setProductos(new ArrayList<String>());
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Este usuario ya está en la base de datos!");
		}
		assertEquals(creada, null);
	}

	@Test
	void crearClienteBlankNameWrong() {
		String nombre = "";
		String email = "antonio.arenas@solera.com";
		Cliente c = new Cliente();
		Cliente creada = null;
		c.setNombre(nombre);
		c.setEmail(email);
		c.setDni("77845374A");
		c.setProductos(new ArrayList<String>());
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "El cliente no tiene nombre!");
		}
		assertEquals(creada, null);
	}

	@Test
	void crearClienteSameDNIWrong() {
		String nombre = "Paises Bajos ha quedado primera de grupo";
		String email = "manolitografotas@solera.com";
		Cliente c = new Cliente();
		Cliente creada = null;
		c.setNombre(nombre);
		c.setEmail(email);
		c.setDni("78965214E");
		c.setProductos(new ArrayList<String>());
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Este usuario ya está en la base de datos!");
		}
		assertEquals(creada, null);
	}

	@Test
	void crearClienteBlankDNIWrong() {
		String nombre = "Paises Bajos ha quedado primera de grupo";
		String email = "manolitografotas@solera.com";
		Cliente c = new Cliente();
		Cliente creada = null;
		c.setNombre(nombre);
		c.setEmail(email);
		c.setDni("");
		c.setProductos(new ArrayList<String>());
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "El cliente no tiene DNI!");
		}
		assertEquals(creada, null);
	}

	@Test
	void editarClientePass() {
		Cliente c = this.clienteService.listOne(5);
		c.setNombre("Triple A");
		Cliente editado = this.clienteService.editar(c);
		assertEquals(editado.getNombre(), "Triple A");
	}

	@Test
	void editarClienteCorreoIncorrectoFail() {
		Cliente editado = null;
		Cliente c = this.clienteService.listOne(5);
		c.setEmail("@@@.2@@");
		try {
			editado = this.clienteService.editar(c);
		} catch (Exception e) {

		}
		assertEquals(editado, null);

	}

	@Test
	void editarOportunidadTelefonoExistenteFail() {
		Cliente editado = null;
		Cliente c = this.clienteService.listOne(6);
		c.setTelefono("6587452396");
		try {
			editado = this.clienteService.editar(c);
		} catch (Exception e) {
		}
		assertEquals(editado, null);
	}

	@Test
	void editarOportunidadNombreVacioFail() {
		Cliente editado = null;
		Cliente c = this.clienteService.listOne(5);
		c.setNombre("");
		try {
			editado = this.clienteService.editar(c);
		} catch (Exception e) {
		}
		assertEquals(editado, null);
	}

	@Test
	void editarOportunidadNombreNullFail() {
		Cliente editado = null;
		Cliente c = this.clienteService.listOne(5);
		c.setNombre(null);
		try {
			editado = this.clienteService.editar(c);
		} catch (Exception e) {
		}
		assertEquals(editado, null);
	}

	@Test
	void editarOportunidadDniVacioFail() {
		Cliente editado = null;
		Cliente c = this.clienteService.listOne(5);
		c.setDni("");
		try {
			editado = this.clienteService.editar(c);
		} catch (Exception e) {
		}
		assertEquals(editado, null);
	}

	@Test
	void editarOportunidadDniNullFail() {
		Cliente editado = null;
		Cliente c = this.clienteService.listOne(5);
		c.setDni(null);
		try {
			editado = this.clienteService.editar(c);
		} catch (Exception e) {
		}
		assertEquals(editado, null);
	}

//
//	// Los métodos de borrado pueden falsear el resultado por lo que deben
//	// ejecutarse individualmente
//	@Test
//	void borrarOportunidadPass() {
//		try {
//			this.oportunidadService.delete(1);
//		} catch (Exception e) {
//
//		}
//		assertEquals(this.oportunidadService.list().size(), 3);
//	}
//
//	@Test
//	void borrarOportunidadIdNotExistFail() {
//		try {
//			this.oportunidadService.delete(50);
//		} catch (Exception e) {
//
//		}
//		assertEquals(this.oportunidadService.list().size(), 4);
//	}
}
