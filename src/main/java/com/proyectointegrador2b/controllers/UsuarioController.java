package com.proyectointegrador2b.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.proyectointegrador2b.modelos.entity.Usuario;
import com.proyectointegrador2b.modelos.entity.UsuarioRol;
import com.proyectointegrador2b.service.implementations.UsuarioRolServiceImpl;
import com.proyectointegrador2b.service.implementations.UsuarioServiceImpl;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioServiceImpl Uservice;
	@Autowired
	UsuarioRolServiceImpl UrolService;
	
	
	@ModelAttribute("roles")
	public List<UsuarioRol> roles(){
		return UrolService.getAll();
	}
	
	
	@GetMapping("/usuarios")
	public ModelAndView listadeUsuario( ModelAndView mv, SessionStatus status){
		mv.addObject("usuarios", Uservice.getAll());
		mv.addObject("titulo", "Lista de Usuarios Registrados");
		mv.setViewName("vistas/usuario/listadeusuarios");
		status.setComplete();
		return mv;
	}
	
	@GetMapping("/formUsuario")
	public ModelAndView formulario(ModelAndView mv) {
		mv.addObject("roles", UrolService.getAll());
		mv.addObject("titulo", "Dar de alta Usuario");
		mv.addObject("usuario", new Usuario());
		mv.setViewName("vistas/usuario/formdeUsuario");
		return mv;
	}
	
	@PostMapping("/formUsuario")
	public ModelAndView creaciondeUsuario( @Valid Usuario usuario,BindingResult result, ModelAndView mv) {
		if(result.hasFieldErrors()) {
			mv.addObject("roles", UrolService.getAll());
			mv.addObject("titulo", "Dar de alta Usuario");
			mv.setViewName("vistas/usuario/formdeUsuario");
			return mv;
		}
		Uservice.crear(usuario);
		mv.setViewName("redirect:/usuarios");
		
		return mv;
	}
	
	//handeler para editar 
	@GetMapping("/perfilusuario/{id}")
	public ModelAndView editarUsuario(@PathVariable(value="id") Integer id, ModelAndView mv) {
	mv.addObject("titulo", "Perfil del Usuario");
	mv.setViewName("vistas/usuario/perfilusuario");
	Usuario usuario = Uservice.getById(id);
	mv.addObject("usuario", usuario);
	return mv;
	}
	@PostMapping("/perfilusuario/{id}")
	public ModelAndView actualizarUsuario(@PathVariable("id") Integer id, Usuario usuario, ModelAndView mv) {
		Uservice.crear(usuario);
		mv.setViewName("redirect:/usuarios");
		return mv;
	}
	@GetMapping("/deleteusuario/{id}")
	public ModelAndView eliminarUsuario(@PathVariable("id") Integer id, ModelAndView mv) {
		Uservice.delete(id);
		mv.setViewName("redirect:/usuarios");
		return mv;
	}
}
