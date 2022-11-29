package com.crm.javaCRM.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.javaCRM.model.Cliente;

/** Interfaz que controla la base de datos de Clientes */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByEmail(String email);

	List<Cliente> findByTelefono(String telefono);

	List<Cliente> findByDni(String dni);
}
