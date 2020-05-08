package com.proyectointegrador2b.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.proyectointegrador2b.modelos.entity.Pedido;

@Repository
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Integer>{

	Pedido findByFolio(String folio);

}
