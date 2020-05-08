package com.proyectointegrador2b.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.proyectointegrador2b.modelos.entity.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente,Integer>{

}
