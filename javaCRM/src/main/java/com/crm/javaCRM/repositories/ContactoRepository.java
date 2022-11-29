package com.crm.javaCRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.javaCRM.model.Contacto;

/** Interfaz que controla la base de datos de Contactos */
@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

}
