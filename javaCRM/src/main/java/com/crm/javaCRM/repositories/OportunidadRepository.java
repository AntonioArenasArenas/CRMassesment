package com.crm.javaCRM.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.javaCRM.model.Oportunidad;

@Repository
public interface OportunidadRepository extends JpaRepository<Oportunidad, Integer> {

	List<Oportunidad> findByEmail(String email);
	
	List<Oportunidad> findByTelefono(String telefono);

}
