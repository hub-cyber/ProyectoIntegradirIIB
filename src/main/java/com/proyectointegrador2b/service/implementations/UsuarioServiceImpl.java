package com.proyectointegrador2b.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyectointegrador2b.modelos.entity.Usuario;
import com.proyectointegrador2b.repositories.UsuarioRepository;
import com.proyectointegrador2b.service.ServiceGeneric;

@Service
public class UsuarioServiceImpl implements ServiceGeneric<Usuario, Integer> {

	@Autowired
	UsuarioRepository Urepository;
	@Override
	public Usuario crear(Usuario entity) {
		return Urepository.save(entity);
	}

	@Override
	public List<Usuario> getAll() {
		List<Usuario> lista = new ArrayList<Usuario>();
		Urepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}

	@Override
	public Usuario getById(Integer id) {
		
		return Urepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		Urepository.deleteById(id);
	}

	@Override
	public Usuario actualizacion(Integer id, Usuario body) {
		Usuario user = new Usuario();
		user.setNombre(body.getNombre()!= null ? body.getNombre(): user.getNombre());
		user.setApellido(body.getApellido()!= null? body.getApellido(): user.getApellido());
		user.setUsername(body.getUsername()!= null ? body.getUsername(): user.getUsername());
		user.setPassword(body.getPassword()!= null ? body.getPassword(): user.getPassword());
		user.setIduserRole(body.getIduserRole()!= null ? body.getIduserRole(): user.getIduserRole());
		return Urepository.save(user);
	}

	@Override
	public Page<Usuario> getAll(Pageable paginable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
