package com.proyectointegrador2b.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyectointegrador2b.modelos.entity.Direccion;

@Repository
public interface DireccionRepository extends CrudRepository<Direccion, Integer>{

}
