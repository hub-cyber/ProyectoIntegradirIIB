package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.Cliente;
import com.proyectointegrador2b.repositories.ClienteRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class ClienteServiceImpl implements ServiceGeneric<Cliente, Integer> {

	@Autowired
	ClienteRepository Crepository;
	@Override
	public Cliente crear(Cliente entity) {
		return Crepository.save(entity);
	}

	@Override
	public List<Cliente> getAll() {
		List<Cliente> lista = new ArrayList<Cliente>();
		Crepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public Cliente getById(Integer id) {
		
		return Crepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		Crepository.deleteById(id);
	}

	@Override
	public Cliente actualizacion(Integer id, Cliente body) {
		// TODO Auto-generated method stub
		return null;
	}

}
