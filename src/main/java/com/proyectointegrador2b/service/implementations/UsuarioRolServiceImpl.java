package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.UsuarioRol;
import com.proyectointegrador2b.repositories.UsuarioRolRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class UsuarioRolServiceImpl implements ServiceGeneric<UsuarioRol, Integer>{

	@Autowired
	UsuarioRolRepository UroleRepository;
	
	@Override
	public UsuarioRol crear(UsuarioRol entity) {
		
		return UroleRepository.save(entity);
	}

	@Override
	public List<UsuarioRol> getAll() {
		List<UsuarioRol> lista = new ArrayList<UsuarioRol>();
		UroleRepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public UsuarioRol getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioRol actualizacion(Integer id, UsuarioRol body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UsuarioRol> getAll(Pageable paginable) {
		// TODO Auto-generated method stub
		return null;
	}

}
