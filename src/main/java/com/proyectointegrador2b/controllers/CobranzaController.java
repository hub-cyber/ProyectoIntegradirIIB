package com.proyectointegrador2b.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectointegrador2b.modelos.entity.Cliente;
import com.proyectointegrador2b.modelos.entity.Cobranza;
import com.proyectointegrador2b.modelos.entity.Pedido;
import com.proyectointegrador2b.modelos.entity.TipoPago;
import com.proyectointegrador2b.service.implementations.ClienteServiceImpl;
import com.proyectointegrador2b.service.implementations.CobranzaServiceImpl;
import com.proyectointegrador2b.service.implementations.PedidoServiceImpl;
import com.proyectointegrador2b.service.implementations.TipodePagoServiceImpl;
import com.proyectointegrador2b.util.paginator.PageRender;

import javassist.expr.NewArray;

@Controller
@RequestMapping("/modulo-cobranza")
public class CobranzaController {

	@Autowired
	CobranzaServiceImpl CoService;
	@Autowired
	ClienteServiceImpl Cservice;
	@Autowired
	PedidoServiceImpl Pservice;
	@Autowired
	TipodePagoServiceImpl TpagoService;

	@Autowired
	CobranzaServiceImpl CobService;
	
	@ModelAttribute("tipoPago")
	public List<TipoPago> tipodepagos() {
		return TpagoService.getAll();
	}

	@GetMapping("/crear/{clienteid}")
	public ModelAndView formCobranza(@PathVariable("clienteid") Integer idcliente, ModelAndView mv) {
		Cliente cliente = Cservice.getById(idcliente);

		List<Pedido> ventas = new ArrayList<Pedido>();
		for (Pedido ped : Pservice.getAll()) {
			if (idcliente == ped.getCliente().getId()) {
				ventas.add(ped);

			}
		}
		mv.addObject("titulo", "Crear Cobranza a Cliente: " + cliente.getNombre() + " " + cliente.getApellidos());
		mv.addObject("ventaslista", ventas);
		mv.addObject("cliente", cliente);
		mv.addObject("cobranza", new Cobranza());
		mv.setViewName("vistas/cobranza/formulariocobranza");
		return mv;
	}

	@PostMapping("/crear/{clienteid}")
	public ModelAndView crearCobranza(@Valid Cobranza cobranza, BindingResult result,
			@PathVariable("clienteid") Integer idcliente, ModelAndView mv, RedirectAttributes flash) {
		Cliente cliente = Cservice.getById(idcliente);
		List<Pedido> ventas = new ArrayList<Pedido>();
		for (Pedido ped : Pservice.getAll()) {
			if (idcliente == ped.getCliente().getId()) {
				ventas.add(ped);

			}
		}
		if (result.hasFieldErrors()) {
			mv.addObject("titulo", "Crear Cobranza a Cliente: " + cliente.getNombre() + " " + cliente.getApellidos());
			mv.addObject("ventaslista", ventas);
			mv.setViewName("vistas/cobranza/formulariocobranza");
			return mv;
		}
		
		mv.addObject("cobranza", CoService.crear(cobranza));
		flash.addFlashAttribute("mensaje", "Venta realizada con Exito");
		mv.setViewName("redirect:/modulo-ventas/clientes/");
		return mv;

	}
	//Ver detale de cobranza 
	@GetMapping("/ver/detalle/cobranza/{folio}")
	public ModelAndView DetalleCobranza(@PathVariable("folio") String folio, ModelAndView mv, RedirectAttributes flash) {
		Cobranza cobranza = CobService.getDetallebyFolio(folio);
		if (cobranza == null) {
			flash.addFlashAttribute("error", "El pedido No existe en la BD");
			mv.setViewName("redirect:/modulo-ventas/clientes");
			return mv;
		}
		mv.addObject("cobranza", cobranza);
		mv.addObject("titulo", "Cobranza: ".concat(cobranza.getFolio()));
		mv.setViewName("vistas/cobranza/detalleCobranza");
		return mv;
	}
	
	///listado de Cobranza
	@GetMapping("/listadodecobranza")
	public ModelAndView listadoCobranza(@RequestParam(name="page",defaultValue = "0") int page,ModelAndView mv) {
		Pageable pageRequestCobranza= PageRequest.of(page, 5);
		Page<Cobranza> cobranzas = CobService.getAll(pageRequestCobranza);
		PageRender<Cobranza> pageRenderCobranza= new PageRender<>("/listadodecobranza", cobranzas);
		
		mv.addObject("titulo", "Listado de Cobranza Realizada");
		mv.addObject("cobranza", cobranzas);
		mv.addObject("page", pageRenderCobranza);
		mv.setViewName("vistas/cobranza/listadodecobranza");
		return mv;
	}

	//eliminar cobranza
	@GetMapping("/eliminarcobranza/{id}")
	public ModelAndView eliminarcobranza(@PathVariable("id") Integer id, ModelAndView mv, RedirectAttributes flash) {
		CoService.delete(id);
		mv.setViewName("redirect:/modulo-cobranza/listadodecobranza");
		return mv;
	}
}
