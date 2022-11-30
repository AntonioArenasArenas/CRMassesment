package com.crm.javaCRM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import com.crm.javaCRM.services.ContactoService;
import com.crm.javaCRM.services.OportunidadService;

@SpringBootTest
class JavaCrmApplicationClienteTest {

	@Autowired
	ClienteService clienteService;

	@Autowired
	ContactoService contactoService;

	@Autowired
	OportunidadService oportunidadService;

	// Aunque es el primer cliente, tiene ID 5 porque todos son personas y hay 4
	// oportunidades creadas
	@Test
	void listClientePass() {
		String name = this.clienteService.listOne(5).getNombre();
		assertEquals("Pepe", name);
	}

	@Test
	void listClienteWrongIdFail() {
		try {
			String name = this.clienteService.listOne(16).getNombre();
			assertEquals("Pepe", name);
		} catch (IllegalArgumentException e) {
			assertEquals("No existe un cliente con ese ID", e.getMessage());
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
					new Contacto(MetodoContacto.CALL, "Interesado en un piso de 3 habitaciones", df.parse(fecha), c));
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
		contactos.add(new Contacto(MetodoContacto.CALL, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals("Este usuario ya está en la base de datos!", e.getMessage());
		}
		assertEquals(null, creada);
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
		contactos.add(new Contacto(MetodoContacto.CALL, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals("Este usuario ya está en la base de datos!", e.getMessage());
		}
		assertEquals(null, creada);
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
		contactos.add(new Contacto(MetodoContacto.CALL, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals("El cliente no tiene nombre!", e.getMessage());
		}
		assertEquals(null, creada);
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
		contactos.add(new Contacto(MetodoContacto.CALL, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals("Este usuario ya está en la base de datos!", e.getMessage());
		}
		assertEquals(null, creada);
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
		contactos.add(new Contacto(MetodoContacto.CALL, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), c));
		c.setContactos(contactos);
		try {
			creada = clienteService.crearCliente(c);

		} catch (IllegalArgumentException e) {
			assertEquals("El cliente no tiene DNI!", e.getMessage());
		}
		assertEquals(null, creada);
	}

	@Test
	void editarClientePass() {
		Cliente c = this.clienteService.listOne(5);
		c.setNombre("Triple A");
		Cliente editado = this.clienteService.editar(c);
		assertEquals("Triple A", editado.getNombre());
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
		assertEquals(null, editado);

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
		assertEquals(null, editado);
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
		assertEquals(null, editado);
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
		assertEquals(null, editado);
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
		assertEquals(null, editado);
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
		assertEquals(null, editado);
	}

	// Los métodos que involucran algún borrado pueden falsear el resultado por lo
	// que deben ejecutarse individualmente
	@Test
	void borrarClientePass() {
		try {
			this.clienteService.delete(5);
		} catch (Exception e) {

		}
		assertEquals(3, this.clienteService.list().size());
	}

	@Test
	void borrarOportunidadIdNotExistFail() {
		try {
			this.clienteService.delete(50);
		} catch (Exception e) {

		}
		assertEquals(4, this.clienteService.list().size());
	}

	@Test
	void crearClienteDeOportunidadPass() {
		Cliente creado = null;
		Cliente datosIncompletos = new Cliente("77845374K", 50, new LinkedList<>());
		try {
			creado = this.clienteService.crearClienteOportunidad(1, datosIncompletos);
		} catch (IllegalArgumentException i) {
			System.out.println(i.getMessage());
		}
		assertNotNull(creado);
		assertEquals(3, this.oportunidadService.list().size());
		assertEquals(5, this.clienteService.list().size());
	}

	@Test
	void crearClienteDeOportunidadWrongIdFail() {
		Cliente creado = null;
		Cliente datosIncompletos = new Cliente("77845374K", 50, new LinkedList<>());
		try {
			creado = this.clienteService.crearClienteOportunidad(50, datosIncompletos);
		} catch (IllegalArgumentException i) {

		}
		assertNull(creado);
	}

	@Test
	void borrarClienteYContactosPass() {
		try {
			this.clienteService.delete(5);
		} catch (Exception e) {

		}
		assertEquals(3, this.clienteService.list().size());
		assertEquals(10, this.contactoService.list().size());
	}

	@Test
	void borrarClienteYContactosFail() {
		try {
			this.clienteService.delete(55);
		} catch (Exception e) {

		}
		assertEquals(4, this.clienteService.list().size());
		assertEquals(11, this.contactoService.list().size());
	}

}
