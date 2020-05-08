package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.TipoPago;
import com.proyectointegrador2b.repositories.TipodePagoRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class TipodePagoServiceImpl implements ServiceGeneric<TipoPago, Integer>{

	@Autowired
	TipodePagoRepository TPrepository;
	
	@Override
	public TipoPago crear(TipoPago entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoPago> getAll() {
		List<TipoPago> lista = new ArrayList<TipoPago>();
		TPrepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public TipoPago getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoPago actualizacion(Integer id, TipoPago body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TipoPago> getAll(Pageable paginable) {
		// TODO Auto-generated method stub
		return null;
	}

}
