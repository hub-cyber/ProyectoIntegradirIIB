package com.proyectointegrador2b.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyectointegrador2b.modelos.entity.Producto;
@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

	Producto findByIdentificador(Integer identificador);
	
	List<Producto> findByNombreLikeIgnoreCase(String nombre);
	
	
}
