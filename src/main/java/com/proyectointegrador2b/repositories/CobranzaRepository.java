package com.proyectointegrador2b.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.proyectointegrador2b.modelos.entity.Cobranza;

@Repository
public interface CobranzaRepository extends PagingAndSortingRepository<Cobranza, Integer>{

	Cobranza findByFolio(String folio);
	
}
