package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.Municipio;
import com.proyectointegrador2b.repositories.MunicipioRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class MunicipioServiceImpl implements ServiceGeneric<Municipio, Integer>{

	@Autowired
	MunicipioRepository Mrepository;
	
	@Override
	public Municipio crear(Municipio entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Municipio> getAll() {
		List<Municipio> lista = new ArrayList<Municipio>();
		Mrepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public Municipio getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Municipio actualizacion(Integer id, Municipio body) {
		// TODO Auto-generated method stub
		return null;
	}

}
