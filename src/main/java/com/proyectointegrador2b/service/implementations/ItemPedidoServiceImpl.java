package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.ItemPedido;
import com.proyectointegrador2b.repositories.ItemsProductoRepository;
import com.proyectointegrador2b.repositories.ProductoRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class ItemPedidoServiceImpl implements ServiceGeneric<ItemPedido, Integer>{

	@Autowired
	ItemsProductoRepository IPrepository;
	@Autowired
	ProductoRepository Prepository;
	
	@Override
	public ItemPedido crear(ItemPedido entity) {
		
		return IPrepository.save(entity);
	}

	@Override
	public List<ItemPedido> getAll() {
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		IPrepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public ItemPedido getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemPedido actualizacion(Integer id, ItemPedido body) {
		// TODO Auto-generated method stub
		return null;
	}

}
