package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.Direccion;
import com.proyectointegrador2b.repositories.DireccionRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class DireccionServiceImpl implements ServiceGeneric<Direccion, Integer> {

	@Autowired
	DireccionRepository Drepository;
	
	@Override
	public Direccion crear(Direccion entity) {
		// TODO Auto-generated method stub
		return Drepository.save(entity);
	}

	@Override
	public List<Direccion> getAll() {
		List<Direccion> lista = new ArrayList<Direccion>();
		Drepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public Direccion getById(Integer id) {
		// TODO Auto-generated method stub
		return Drepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		Drepository.deleteById(id);
		
	}

	@Override
	public Direccion actualizacion(Integer id, Direccion body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Direccion> getAll(Pageable paginable) {
		// TODO Auto-generated method stub
		return null;
	}

}
