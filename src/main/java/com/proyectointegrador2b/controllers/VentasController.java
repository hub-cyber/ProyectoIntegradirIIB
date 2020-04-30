package com.proyectointegrador2b.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectointegrador2b.modelos.entity.Cliente;
import com.proyectointegrador2b.modelos.entity.Direccion;
import com.proyectointegrador2b.modelos.entity.ItemPedido;
import com.proyectointegrador2b.modelos.entity.Pedido;
import com.proyectointegrador2b.modelos.entity.Producto;
import com.proyectointegrador2b.modelos.entity.TipoCredito;
import com.proyectointegrador2b.modelos.entity.TipoPago;
import com.proyectointegrador2b.repositories.ProductoRepository;
import com.proyectointegrador2b.repositories.TipodePagoRepository;
import com.proyectointegrador2b.repositories.TipodewCreditoRepository;
import com.proyectointegrador2b.service.implementations.ClienteServiceImpl;
import com.proyectointegrador2b.service.implementations.DireccionServiceImpl;
import com.proyectointegrador2b.service.implementations.ItemPedidoServiceImpl;
import com.proyectointegrador2b.service.implementations.PedidoServiceImpl;
import com.proyectointegrador2b.service.implementations.TipodePagoServiceImpl;

@Controller
@RequestMapping("/modulo-ventas")

public class VentasController {

	private final Logger log= org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Autowired
	ClienteServiceImpl vsImpl;
	@Autowired
	DireccionServiceImpl Dservice;
	@Autowired
	PedidoServiceImpl Pservice;
	@Autowired
	ItemPedidoServiceImpl IPservice;
	@Autowired
	ProductoRepository Prepository;
	@Autowired
	TipodePagoServiceImpl TpagoService;
	@Autowired
	TipodewCreditoRepository TCrepository;

	@ModelAttribute("clientes")
	public List<Cliente> clientes() {
		return vsImpl.getAll();
	}

	@ModelAttribute("direcciones")
	public List<Direccion> direciones() {
		return Dservice.getAll();
	}
	
	@ModelAttribute("tipoCredito")
	public List<TipoCredito> creditos(){
		List<TipoCredito> lista = new ArrayList<TipoCredito>();
		TCrepository.findAll().forEach(e-> lista.add(e));
		return lista;
	}
	@ModelAttribute("tipoPago")
	public List<TipoPago> tipodepagos(){
		return TpagoService.getAll();
	}
	
	@ModelAttribute("productosavender")
	public List<ItemPedido> productoavender() {
		return IPservice.getAll();
	}
//	<--------Metodos del Controllador--------->

//	<--------Metodos Hadler-------->
	@GetMapping("/clientes")
	public ModelAndView clientes(ModelAndView mv) {
		mv.addObject("titulo", "Listado de Clientes");
		mv.setViewName("vistas/ventas/listclivent");
		return mv;
	}

	@GetMapping("/ver/{id}")
	public ModelAndView pedidos(@PathVariable("id") Integer id, ModelAndView mv) {
		/*
		 * Esta parte del codigo este for sirve para mostar las facturas de el cliente escogido, en el tamplate de ver 
		 */
		List<Pedido> ventas= new ArrayList<Pedido>();
		for(Pedido ped: Pservice.getAll()) {
			if(id == ped.getCliente().getId()) {
				ventas.add(ped);
			}
		}
		
		mv.addObject("titulo", "Informacion del Cliente");
		mv.addObject("titulo2", "Listado de Pedidos");
		mv.addObject("cliente", vsImpl.getById(id));
		mv.addObject("ventaslista", ventas);
		mv.setViewName("vistas/ventas/ver");
		return mv;
	}

	// crear pedido
	@GetMapping("/crear/{clienteid}")
	public ModelAndView crear(@PathVariable("clienteid") Integer id, ModelAndView mv, RedirectAttributes flash,
			Producto producto) {
		Cliente cliente = vsImpl.getById(id);
		if (cliente == null) {
			flash.addAttribute("error", "cliente No existe en La base de datos");
			mv.setViewName("redirect:/modulo-ventas/clientes");
			return mv;
		}
		/*esto es para que en el formulario del cliente aparazca su direccion; es una lista ya que el cliente puede
		tener mass de una direccion
		*/
		List<Direccion> address = new ArrayList<Direccion>();
		for (Direccion dir : this.direciones()) {
			if (cliente.getId() == dir.getIdcliente().getId()) {
				address.add(dir);
				
			}
		}
		mv.addObject("direccion", address);
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		mv.addObject("pedido", pedido);
		mv.addObject("titulo", "Datos Personales");
		mv.addObject("titulo2", "Agregar Productos");
		mv.setViewName("vistas/ventas/formpedido");
		return mv;
	}

	// carga producto
	@GetMapping(value = "/carga-producto/{term}", produces = { "application/json" })
	public @ResponseBody List<Producto> buscarproducto(@PathVariable("term") String term, ItemPedido ip,
			ModelAndView mv) {
		return Pservice.getProductobyName(term);
	}

	@PostMapping("/crear")
	public ModelAndView guardarPedido(Integer id, Pedido pedido,
			@RequestParam(name="item_id[]",required = false) Integer[] itemid
			,@RequestParam(name="cantidad[]",required = false) Integer[] cantidad,
			@RequestParam(name="descuento[]",required = false) Double[] descuento,
			ModelAndView mv,RedirectAttributes flash) {
		for(int i=0; i< itemid.length; i++) {
			Producto producto = Pservice.getProductobyId(itemid[i]);
			ItemPedido linea = new ItemPedido();
			linea.setCantidad(cantidad[i]);
			linea.setDescuento(descuento[i]);
			linea.setProducto(producto);
			pedido.addItemPedido(linea);
			log.info("ID: "+itemid[i].toString() + "Cantidad: "+ cantidad[i].toString()+ "Desceunto:" + descuento[i].toString());
		}
		mv.addObject("clienteid", id);
		Pservice.crear(pedido);
	
		flash.addFlashAttribute("mensaje", "Venta realizada con Exito");
		mv.setViewName("redirect:/modulo-ventas/clientes");
		return mv;
	}
}
