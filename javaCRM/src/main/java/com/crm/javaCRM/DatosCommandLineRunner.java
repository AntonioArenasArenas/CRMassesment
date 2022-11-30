package com.crm.javaCRM;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.crm.javaCRM.model.Cliente;
import com.crm.javaCRM.model.Contacto;
import com.crm.javaCRM.model.MetodoContacto;
import com.crm.javaCRM.model.Oportunidad;
import com.crm.javaCRM.repositories.ClienteRepository;
import com.crm.javaCRM.repositories.ContactoRepository;
import com.crm.javaCRM.repositories.OportunidadRepository;

/**
 * Clase CommandLineRuner que rellena nuestra base de datos con datos de prueba,
 * se ejecuta con en run del proyecto
 */
@Component
public class DatosCommandLineRunner implements CommandLineRunner {
	OportunidadRepository repository;
	ContactoRepository crepository;
	ClienteRepository clirepository;

	@Autowired
	public DatosCommandLineRunner(OportunidadRepository repository, ContactoRepository crepository,
			ClienteRepository clirepository) {
		this.repository = repository;
		this.crepository = crepository;
		this.clirepository = clirepository;
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		System.out.println("Introduciendo datos por defecto...");

		// Oportunidad 1
		Oportunidad op1 = new Oportunidad();
		op1.setNombre("Antonio Arenas Arenas");
		op1.setDireccion("Calle Tharsis 26");
		op1.setEmail("antonio.arenas@solera.com");
		op1.setTelefono("638453173");
		op1.setContactos(new LinkedList<>());
		Contacto c1 = new Contacto(MetodoContacto.CALL, "Interesado en una vivienda de 2 dormitorios",
				df.parse("06/09/22"), op1);
		Contacto c2 = new Contacto(MetodoContacto.EMAIL, "Interesado en el 3ºA", df.parse("07/10/22"), op1);
		Contacto c3 = new Contacto(MetodoContacto.SALE_VISIT, "Visita la vivienda en la que está interesado",
				df.parse("28/11/22"), op1);
		Contacto c4 = new Contacto(MetodoContacto.CALL, "Llamada para concretar las condiciones y firmar el pago",
				df.parse("05/12/22"), op1);
		op1.getContactos().add(c1);
		op1.getContactos().add(c2);
		op1.getContactos().add(c3);
		op1.getContactos().add(c4);
		this.repository.save(op1);
		this.crepository.save(c1);
		this.crepository.save(c2);
		this.crepository.save(c3);
		this.crepository.save(c4);
		System.out.println("Oportunidad completa guardada");

		// Oportunidad 2
		Oportunidad op2 = new Oportunidad();
		op2.setNombre("Marina García Miranda");
		op2.setDireccion("Calle San Benito 14");
		op2.setEmail("smanervion@hotmail.com");
		op2.setContactos(new LinkedList<>());
		Contacto c5 = new Contacto(MetodoContacto.EMAIL, "Interesada en una vivienda de 2 dormitorios",
				df.parse("11/09/22"), op2);
		Contacto c6 = new Contacto(MetodoContacto.EMAIL, "Interesada en el 1ºB", df.parse("15/10/22"), op2);
		Contacto c7 = new Contacto(MetodoContacto.SALE_VISIT, "Visita la vivienda con su pareja y también el 3ºA",
				df.parse("28/11/22"), op2);
		op2.getContactos().add(c5);
		op2.getContactos().add(c6);
		op2.getContactos().add(c7);
		this.repository.save(op2);
		this.crepository.save(c5);
		this.crepository.save(c6);
		this.crepository.save(c7);
		System.out.println("Oportunidad sin teléfono guardada");

		// Oportunidad 3
		Oportunidad op3 = new Oportunidad();
		op3.setNombre("Francisco Pérez Vergara");
		op3.setDireccion("Calle Tharsis 26");
		op3.setContactos(new LinkedList<>());
		Contacto c8 = new Contacto(MetodoContacto.SALE_VISIT, "Visita un estudio cerca del centro",
				df.parse("13/06/22"), op3);
		Contacto c9 = new Contacto(MetodoContacto.SALE_VISIT, "Visita un estudio cerca de la alameda",
				df.parse("04/10/22"), op3);
		op3.getContactos().add(c8);
		op3.getContactos().add(c9);
		this.repository.save(op3);
		this.crepository.save(c8);
		this.crepository.save(c9);
		System.out.println("Oportunidad sin email ni teléfono guardada");

		// Oportunidad 4
		Oportunidad op4 = new Oportunidad();
		op4.setNombre("Antonio Manuel Díaz Arenas");
		op4.setContactos(new LinkedList<>());
		Contacto c10 = new Contacto(MetodoContacto.SALE_VISIT, "Visita un estudio cerca del centro",
				df.parse("13/06/22"), op4);
		op4.getContactos().add(c10);
		this.repository.save(op4);
		this.crepository.save(c10);
		System.out.println("Oportunidad con solo nombre guardada");

		// Inserción de clientes
		List<String> productos = new LinkedList<>();
		productos.add("Compra de una vivienda en Carmona, calle Pepito, Sevilla");
		productos.add("Alquiler de un estudio en la calle Alcalá, Madrid");
		productos.add("Casa en venta en calle Arroyo, Sevilla");
		productos.add("Terrenos comprados en las afueras de Paterna del Campo, Huelva");
		Cliente cliente1 = new Cliente("78965214E", 45, productos);
		cliente1.setContactos(new LinkedList<>());
		cliente1.setNombre("Pepe");
		cliente1.setDireccion("Calle cualquiera");
		cliente1.setEmail("emailvaido@gmail.com");
		cliente1.setTelefono("6587452396");
		cliente1.getContactos().add(c1);
		Cliente cliente2 = new Cliente("78965214P", 45, productos);
		cliente2.setContactos(new LinkedList<>());
		cliente2.setNombre("Manolo");
		cliente2.setDireccion("Calle cualquiera2");
		cliente2.setEmail("emailvalido1@gmail.com");
		cliente2.getContactos().add(c2);
		cliente2.getContactos().add(c3);
		Cliente cliente3 = new Cliente("78965214F", 45, productos);
		cliente3.setContactos(new LinkedList<>());
		cliente3.setNombre("Santiago");
		cliente3.setDireccion("Calle cualquiera3");
		cliente3.getContactos().add(c4);
		cliente3.getContactos().add(c5);
		cliente3.getContactos().add(c6);
		Cliente cliente4 = new Cliente("78965214S", 45, productos);
		cliente4.setContactos(new LinkedList<>());
		cliente4.setNombre("Jose Luis");
		cliente4.getContactos().add(c7);
		cliente4.getContactos().add(c8);
		cliente4.getContactos().add(c9);
		cliente4.getContactos().add(c10);
		clirepository.save(cliente1);
		clirepository.save(cliente2);
		clirepository.save(cliente3);
		clirepository.save(cliente4);
		System.out.println("Clientes introducidos correctamente");

	}

}
