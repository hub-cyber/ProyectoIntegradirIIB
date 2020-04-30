package com.proyectointegrador2b.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyectointegrador2b.modelos.entity.Municipio;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Integer> {

}
