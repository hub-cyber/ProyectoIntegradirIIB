package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.Cobranza;
import com.proyectointegrador2b.repositories.CobranzaRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class CobranzaServiceImpl implements ServiceGeneric<Cobranza, Integer> {

	@Autowired
	CobranzaRepository Corepository;
	

	public Cobranza getDetallebyFolio(String folio) {
		return Corepository.findByFolio(folio);
	}
	
	@Override
	public Cobranza crear(Cobranza entity) {
		return Corepository.save(entity);
	}

	@Override
	public List<Cobranza> getAll() {
		List<Cobranza> lista = new ArrayList<Cobranza>();
		Corepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public Cobranza getById(Integer id) {
		return Corepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		Corepository.deleteById(id);
		
	}

	@Override
	public Cobranza actualizacion(Integer id, Cobranza body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Cobranza> getAll(Pageable paginable) {
		return Corepository.findAll(paginable);
	}

}
