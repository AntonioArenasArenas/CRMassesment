package com.crm.javaCRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.javaCRM.model.Persona;

/**
 * Interfaz que controla la base de datos de Personas en cuanto a métodos
 * generales
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
