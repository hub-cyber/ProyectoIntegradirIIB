package com.proyectointegrador2b.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectointegrador2b.editors.DatosIngresadosenMayuscula;
import com.proyectointegrador2b.modelos.entity.Cliente;
import com.proyectointegrador2b.modelos.entity.Producto;
import com.proyectointegrador2b.service.implementations.ProductoServiceImpl;
import com.proyectointegrador2b.util.paginator.PageRender;

@Controller
@RequestMapping("/modulo-inventario")
public class ProductoController {

	@Autowired
	ProductoServiceImpl ProductoService;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "nombre", new DatosIngresadosenMayuscula());
		binder.registerCustomEditor(String.class, "marca", new DatosIngresadosenMayuscula());
		binder.registerCustomEditor(String.class, "proveedor", new DatosIngresadosenMayuscula());
	}
	
	
	
	@GetMapping("/listadeproductos")
	public ModelAndView lisatadodeProductos(@RequestParam(name="page",defaultValue = "0") int page, ModelAndView mv) {
		Pageable pageRequestProducto = PageRequest.of(page, 20);
		Page<Producto> productos = ProductoService.getAll(pageRequestProducto);
		PageRender<Producto> pageRenderProducto = new PageRender<>("/modulo-inventario/listadeproductos", productos);
		
		mv.addObject("titulo", "Listado de Productos disponibles");
		mv.addObject("productos", productos);
		mv.addObject("page",pageRenderProducto);
		mv.setViewName("vistas/producto/listadeproductos");
		return mv;
	}
	//eliminar producto
	@GetMapping("/eliminarproducto/{id}")
	public ModelAndView eliminarproducto(@PathVariable("id") Integer id, ModelAndView mv, RedirectAttributes flash) {
		ProductoService.delete(id);
		mv.setViewName("redirect:/modulo-inventario/listadeproductos");
		return mv;
	}
	// Dar de ALta un producto formulario
	@GetMapping("/dardealta/producto")
	public ModelAndView crearproducto(ModelAndView mv) {
		mv.addObject("titulo", "Dar de Alta Nuevo Producto");
		mv.addObject("producto", new Producto());
		mv.setViewName("vistas/producto/formularioproducto");
		return mv;
	}
	//Guardar producto en bd
	@PostMapping("/dardealta/producto")
	public ModelAndView guardarProducto(@Valid Producto producto, BindingResult result, ModelAndView mv, 
			RedirectAttributes flash){
		
		if(result.hasFieldErrors()) {
			mv.addObject("titulo", "Dar de Alta Nuevo Producto");
			mv.setViewName("vistas/producto/formularioproducto");
			return mv;
		}
		ProductoService.crear(producto);
		mv.setViewName("redirect:/modulo-inventario/listadeproductos");
		return mv;
		
	}
	//Editar producto
	@GetMapping("/editarproducto/{id}")
	public ModelAndView editarProducto(@PathVariable("id") Integer id, ModelAndView mv) {
		mv.addObject("titulo", "Editar Producto");
		mv.addObject("producto", ProductoService.getById(id));
		mv.setViewName("vistas/producto/editarproducto");
		return mv;
	}
	@PostMapping("/editarproducto/{id}")
	public ModelAndView actualizacionCliente(@PathVariable("id") Integer id, Producto producto, ModelAndView mv) {
		ProductoService.crear(producto);
		mv.setViewName("redirect:/modulo-inventario/listadeproductos");
		return mv;
	}
	
}
