package com.proyectointegrador2b.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyectointegrador2b.modelos.entity.TipoPago;
@Repository
public interface TipodePagoRepository extends CrudRepository<TipoPago, Integer> {
}
