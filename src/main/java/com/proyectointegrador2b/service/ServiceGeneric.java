package com.proyectointegrador2b.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceGeneric<T,K> {

	//Craer los metodos que va a implementar 
	
	//Crear
	public T crear(T entity);
	//listar
	public List<T> getAll();
	//obtener por id 
	public T getById(K id);
	//eliminar 
	public void  delete(K id);
	//Actualizar 
	public T actualizacion(final K id, T body);
	
	public Page<T> getAll(Pageable paginable);
}
