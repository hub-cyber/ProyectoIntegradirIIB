package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectointegrador2b.modelos.entity.Pedido;
import com.proyectointegrador2b.modelos.entity.Producto;
import com.proyectointegrador2b.repositories.PedidoRepository;
import com.proyectointegrador2b.repositories.ProductoRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class PedidoServiceImpl implements ServiceGeneric<Pedido, Integer>{

	@Autowired
	ProductoRepository Prepository;
	@Autowired
	PedidoRepository Frepository;
	
	
	public Producto getProductobyId(Integer id){
		return  Prepository.findById(id).orElse(null);
	}
	
	public List<Producto> getProductobyName(String name){
		return Prepository.findByNombreLikeIgnoreCase("%"+name+"%");
	}
	
	@Override
	public Pedido crear(Pedido entity) {
		return Frepository.save(entity);
	}

	@Override
	public List<Pedido> getAll() {
		List<Pedido> lista = new ArrayList<Pedido>();
		Frepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public Pedido getById(Integer id) {
		
		return Frepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		Frepository.deleteById(id);
		
	}

	@Override
	public Pedido actualizacion(Integer id, Pedido body) {
		// TODO Auto-generated method stub
		return null;
	}

}
