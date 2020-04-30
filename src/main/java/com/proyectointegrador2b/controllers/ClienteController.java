package com.proyectointegrador2b.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyectointegrador2b.editors.DatosIngresadosenMayuscula;
import com.proyectointegrador2b.modelos.entity.Cliente;
import com.proyectointegrador2b.modelos.entity.Direccion;
import com.proyectointegrador2b.modelos.entity.Municipio;
import com.proyectointegrador2b.modelos.entity.Usuario;
import com.proyectointegrador2b.service.implementations.ClienteServiceImpl;
import com.proyectointegrador2b.service.implementations.DireccionServiceImpl;
import com.proyectointegrador2b.service.implementations.MunicipioServiceImpl;
import com.proyectointegrador2b.service.implementations.UsuarioServiceImpl;

@Controller
public class ClienteController {

	@Autowired
	ClienteServiceImpl Cservice;

	@Autowired
	UsuarioServiceImpl Uservice;
	@Autowired
	DireccionServiceImpl Dservice;
	@Autowired
	MunicipioServiceImpl Mservice;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "nombre", new DatosIngresadosenMayuscula());
		binder.registerCustomEditor(String.class, "apellidos", new DatosIngresadosenMayuscula());
		binder.registerCustomEditor(String.class, "rfc", new DatosIngresadosenMayuscula());
		binder.registerCustomEditor(String.class, "colonia", new DatosIngresadosenMayuscula());
		binder.registerCustomEditor(String.class, "calle", new DatosIngresadosenMayuscula());
	}

	@ModelAttribute("vendedores")
	public List<Usuario> vendedores() {
		return Uservice.getAll();
	}

	@ModelAttribute("municipios")
	public List<Municipio> municipios() {
		return Mservice.getAll();
	}

	@ModelAttribute("direcciones")
	public List<Direccion> direciones() {
		return Dservice.getAll();
	}

	@GetMapping("/clientes")
	public ModelAndView listadodeClientes(ModelAndView mv) {
		mv.addObject("clientes", Cservice.getAll());
		mv.addObject("titulo", "Listado de Clienetes");
		mv.setViewName("vistas/cliente/listadeCliente");
		return mv;
	}

	@GetMapping("/nuevocliente")
	public ModelAndView formularioCliente(ModelAndView mv) {
		mv.addObject("titulo", "Añadir Cliente Nuevo");
		mv.addObject("cliente", new Cliente());
		mv.setViewName("vistas/cliente/formularioCliente");
		return mv;
	}

	@PostMapping("/nuevocliente")
	public ModelAndView añadirCliente(@Valid Cliente cliente, BindingResult result, ModelAndView mv) {
		if (result.hasFieldErrors()) {
			mv.addObject("titulo", "Añadir Cliente Nuevo");
			mv.setViewName("vistas/cliente/formularioCliente");
			return mv;
		}
		Cservice.crear(cliente);
		mv.setViewName("redirect:/clientes");
		return mv;
	}

	// hadler para editar
	@GetMapping("/perfilcliente/{id}")
	public ModelAndView editarperfil(@PathVariable("id") Integer id, ModelAndView mv) {
		mv.addObject("titulo", "Perfil del Cliente");
		mv.setViewName("vistas/cliente/perfilcliente");
		Cliente cliente = Cservice.getById(id);
		mv.addObject("cliente", cliente);
		List<Direccion> address = new ArrayList<Direccion>();
		for (Direccion dir : this.direciones()) {
			if (cliente.getId() == dir.getIdcliente().getId()) {
				address.add(dir);
				
			}
		}
		mv.addObject("direccion", address);
		return mv;
	}

	@PostMapping("/perfilcliente/{id}")
	public ModelAndView actualizacionCliente(@PathVariable("id") Integer id, Cliente cliente, ModelAndView mv) {
		Cservice.crear(cliente);
		mv.setViewName("redirect:/clientes");

		return mv;
	}

	@GetMapping("/deletecliente/{id}")
	public ModelAndView eliminarCliente(@PathVariable("id") Integer id, ModelAndView mv) {
		Cservice.delete(id);
		mv.setViewName("redirect:/clientes");
		return mv;
	}

	// <----------Seccion del Manejo de la Direcciones del Cliente---------->
	@GetMapping("/perfilcliente/{id}/direccion")
	public ModelAndView añadirDireccion(@PathVariable("id") Integer id, Cliente cliente, ModelAndView mv) {
		mv.addObject("titulo", "Añadir Direccion del Cliente");
		mv.addObject("cliente", Cservice.getById(id));
		mv.addObject("direccion", new Direccion());
		mv.setViewName("vistas/cliente/formularioDireccion");
		return mv;
	}

	@PostMapping("/perfilcliente/{id}/direccion")
	public ModelAndView añadirDireccion(@Valid Direccion direccion, BindingResult result,
			@PathVariable("id") Integer id, ModelAndView mv) {
		if (result.hasFieldErrors()) {
			mv.addObject("titulo", "Añadir Direccion");
			mv.setViewName("vistas/clieste/formularioDireccion");
			return mv;
		}
		Dservice.crear(direccion);
		mv.setViewName("redirect:/perfilcliente/{id}");
		return mv;
	}

	// Editar direccion
	@GetMapping("/perfilcliente/{id}/direccion/{address}")
	public ModelAndView editarDireccion(@PathVariable("id") Integer id, @PathVariable("address") Integer address,
			ModelAndView mv) {
		mv.addObject("titulo", "Editar Direccion");
		mv.addObject("direccion", Dservice.getById(address));
		mv.addObject("cliente", Cservice.getById(id));
		mv.setViewName("vistas/cliente/formularioDireccion");
		return mv;
	}

	@PostMapping("/perfilcliente/{id}/direccion/{address}")
	public ModelAndView actualizarDireccion(@PathVariable("id") Integer id, @PathVariable("address") Integer address,
			Direccion direccion, ModelAndView mv) {
		Dservice.crear(direccion);
		mv.setViewName("redirect:/perfilcliente/{id}");
		return mv;
	}

	// eliminar
	@GetMapping("/perfilcliente/{id}/eliminardireccion/{address}")
	public ModelAndView eliminardireccion( @PathVariable("id") Integer id, @PathVariable("address")  Integer idaddress,ModelAndView mv) {
		Dservice.delete(idaddress);
		Cliente cliente = Cservice.getById(id);
		mv.setViewName("redirect:/perfilcliente/" + cliente.getId());
		return mv;
	}
}
