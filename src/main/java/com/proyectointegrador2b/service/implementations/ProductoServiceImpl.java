package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.Producto;
import com.proyectointegrador2b.repositories.ProductoRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class ProductoServiceImpl implements ServiceGeneric<Producto, Integer>{

	@Autowired
	ProductoRepository ProdRepository;
	
	@Override
	public Producto crear(Producto entity) {
		return ProdRepository.save(entity);
	}

	@Override
	public List<Producto> getAll() {
		List<Producto> lista = new ArrayList<Producto>();
		ProdRepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public Producto getById(Integer id) {
		return ProdRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		ProdRepository.deleteById(id);
	}

	@Override
	public Producto actualizacion(Integer id, Producto body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Producto> getAll(Pageable paginable) {
	
		return  ProdRepository.findAll(paginable);
	}

}
