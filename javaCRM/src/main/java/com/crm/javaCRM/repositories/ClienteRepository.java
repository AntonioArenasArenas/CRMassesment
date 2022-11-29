package com.crm.javaCRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.javaCRM.model.Cliente;

/** Interfaz que controla la base de datos de Clientes */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
