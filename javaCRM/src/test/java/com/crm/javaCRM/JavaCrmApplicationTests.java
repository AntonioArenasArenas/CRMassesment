package com.crm.javaCRM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.model.MetodoContacto;
import com.crm.javaCRM.model.Oportunidad;

@SpringBootTest
class JavaCrmApplicationTests {

	@Test
	void crearOportunidadPass() {
		String nombre = "Antonio";
		String email = "antonio.arenas@solera,com";
		String direccion = "Calle Pepe 26";
		String telefono = "954863219";
		List<Contacto> contactos = new LinkedList<>();
		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
				new Date(System.currentTimeMillis() - 1), o));
		Oportunidad o = null;
		o = crearOportunidad(nombre, email, direccion, telefono, contactos);

		assertNotNull(o);

	}

//	@Test
//	void personaIncorrecta() {
//		String nombre = "Antonio";
//		String email = "antonio.arenas@solera,com";
//		String direccion = "Calle Pepe 26";
//		String telefono = "954863219";
//		Oportunidad o = new Oportunidad();
//		o.setNombre(nombre);
//		o.setTelefono(telefono);
//		List<Contacto> contactos = new LinkedList<>();
//		contactos.add(new Contacto(MetodoContacto.call, "Interesado en un piso de 3 habitaciones",
//				new Date(System.currentTimeMillis() - 1), o));
//		o.setContactos(contactos);
//
//		assertEquals(o.getContactos().size(), 1);
//	}

}
