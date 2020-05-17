package com.proyectointegrador2b.controllers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectointegrador2b.modelos.entity.Cliente;
import com.proyectointegrador2b.modelos.entity.Cobranza;
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
import com.proyectointegrador2b.service.implementations.CobranzaServiceImpl;
import com.proyectointegrador2b.service.implementations.DireccionServiceImpl;
import com.proyectointegrador2b.service.implementations.ItemPedidoServiceImpl;
import com.proyectointegrador2b.service.implementations.PedidoServiceImpl;
import com.proyectointegrador2b.service.implementations.TipodePagoServiceImpl;
import com.proyectointegrador2b.util.paginator.PageRender;

@Controller
@RequestMapping("/modulo-ventas")
public class VentasController {
	private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
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
	@Autowired
	CobranzaServiceImpl CobService;
	@ModelAttribute("clientes")
	public List<Cliente> clientes() {
		return vsImpl.getAll();
	}
	@ModelAttribute("direcciones")
	public List<Direccion> direciones() {
		return Dservice.getAll();
	}
	@ModelAttribute("tipoCredito")
	public List<TipoCredito> creditos() {
		List<TipoCredito> lista = new ArrayList<TipoCredito>();
		TCrepository.findAll().forEach(e -> lista.add(e));
		return lista;
	}
	@ModelAttribute("tipoPago")
	public List<TipoPago> tipodepagos() {
		return TpagoService.getAll();
	}

//	<--------Metodos del Controllador--------->
	public List<Direccion> obtenerDirecciondeVenta(Cliente cliente) {
		List<Direccion> address = new ArrayList<Direccion>();
		for (Direccion dir : this.direciones()) {
			if (cliente.getId() == dir.getIdcliente().getId()) {
				address.add(dir);

			}
		}
		return address;
	}

//	<--------Metodos Hadler-------->
	@GetMapping("/clientes")
	public ModelAndView clientes(ModelAndView mv) {
		mv.addObject("titulo", "Listado de Clientes");
		mv.setViewName("vistas/ventas/listclivent");
		return mv;
	}

	// ver detalle las ventas del cliente
	@GetMapping("/ver/{id}")
	public ModelAndView pedidos(@PathVariable("id") Integer id, @RequestParam(name="page",defaultValue = "0") int page,
			ModelAndView mv) {
		 // Esta parte del codigo este for sirve para mostar las facturas de el cliente escogido, en el tamplate de ver 
		int saldo =0;
		int totalventas =0;
		int totalCobranza=0;
		Pageable pageRequestPedido = PageRequest.of(page, 5);
		Page<Pedido> pedidos = Pservice.getAll(pageRequestPedido);
		PageRender<Pedido> pageRenderPedido= new PageRender<>("/ver/{id}", pedidos);
		List<Pedido> ventas= new ArrayList<Pedido>();
		for(Pedido ped: pedidos) {
			if(id == ped.getCliente().getId()) {
				ventas.add(ped);
				totalventas += ped.getTotal();
			}
		}
		Pageable pageRequest = PageRequest.of(page, 10);
		PageRender<Cobranza> pageRenderCobranza = new PageRender<>("/ver/{id}",CobService.getAll(pageRequest));
		List<Cobranza> cobranzas = new ArrayList<Cobranza>();
		for(Cobranza cob: CobService.getAll(pageRequest)) {
			if(id == cob.getIdpedido().getCliente().getId()) {
				cobranzas.add(cob);	
				totalCobranza += cob.getImporte();
			}
		}
		saldo= totalventas - totalCobranza;
		mv.addObject("titulo", "Informacion del Cliente");
		mv.addObject("titulo2", "Listado de Pedidos");
		mv.addObject("titulo3", "Listado de Cobranza");
		mv.addObject("cliente", vsImpl.getById(id));
		mv.addObject("ventaslista", ventas);
		mv.addObject("cobranza", cobranzas);
		mv.addObject("saldo", saldo);
		mv.addObject("page", pageRenderPedido);
		mv.addObject("page", pageRenderCobranza);
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
			mv.addObject("direccion", this.obtenerDirecciondeVenta(cliente));
			mv.setViewName("redirect:/modulo-ventas/clientes");
			return mv;
		}
		
		int folio = 0;
		folio ++;
		
		/*
		 * esto es para que en el formulario del cliente aparazca su direccion; es una
		 * lista ya que el cliente puede tener mass de una direccion
		 */
		mv.addObject("direccion", this.obtenerDirecciondeVenta(cliente));
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		mv.addObject("pedido", pedido);
		mv.addObject("titulo", "Datos Personales");
		mv.addObject("titulo2", "Agregar Productos");
		mv.addObject("folio", folio);
		mv.setViewName("vistas/ventas/formpedido");
		return mv;
	}

	// carga producto
	@GetMapping(value = "/carga-producto/{term}", produces = { "application/json" })
	public @ResponseBody List<Producto> buscarproducto(@PathVariable("term") String term, ItemPedido ip,
			ModelAndView mv) {
		return Pservice.getProductobyName(term);
	}

//Guardar venta en bd
	@PostMapping("/crear/{clienteid}")
	public ModelAndView guardarPedido(@Valid Pedido pedido, BindingResult result,
			@RequestParam(name = "item_id[]", required = false) Integer[] itemid,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
			@RequestParam(name = "descuento[]", required = false) Integer[] descuento, ModelAndView mv,
			RedirectAttributes flash, @PathVariable("clienteid") Integer id) {
		Cliente cliente = vsImpl.getById(id);
		if (result.hasFieldErrors()) {
			mv.addObject("titulo", "Crear Venta");
		mv.addObject("direccion", this.obtenerDirecciondeVenta(cliente));
			mv.setViewName("vistas/ventas/formpedido");
			return mv;
		}
		if (itemid == null || itemid.length == 0) {
			mv.addObject("titulo", "Crear Venta");
			mv.addObject("direccion", this.obtenerDirecciondeVenta(cliente));
			mv.addObject("error", "La Venta NO puede no llevar productos");
			mv.setViewName("vistas/ventas/formpedido");
			return mv;
		}

		for (int i = 0; i < itemid.length; i++) {
			Producto producto = Pservice.getProductobyId(itemid[i]);
			ItemPedido linea = new ItemPedido();
			linea.setCantidad(cantidad[i]);
			linea.setDescuento(descuento[i]);
			linea.setProducto(producto);
			pedido.addItemPedido(linea);
			log.info("ID: " + itemid[i].toString() + "Cantidad: " + cantidad[i].toString() + "Desceunto:"
					+ descuento[i].toString());
		}
		mv.addObject("clienteid", id);
		Pservice.crear(pedido);

		flash.addFlashAttribute("mensaje", "Venta realizada con Exito");
		mv.setViewName("redirect:/modulo-ventas/clientes");
		return mv;
	}

	// Ver el detalle de la factura
	@GetMapping("/ver/detalle/venta/{folio}")
	public ModelAndView DetalleVenta(@PathVariable("folio") String folio, ModelAndView mv, RedirectAttributes flash) {
		Pedido pedido = Pservice.getDetallePedidobyFolio(folio);
		if (pedido == null) {
			flash.addFlashAttribute("error", "El pedido No existe en la BD");
			mv.setViewName("redirect:/modulo-ventas/clientes");
			return mv;
		}
		mv.addObject("pedido", pedido);
		mv.addObject("titulo", "Pedido: ".concat(pedido.getFolio()));
		mv.setViewName("vistas/ventas/detallePedido");
		return mv;
	}

	//Listar Pedido
	@GetMapping("/listadepedidos")
	public ModelAndView listadepedidos(@RequestParam(name="page",defaultValue = "0") int page,ModelAndView mv) {
		Pageable pageRequestPedido = PageRequest.of(page, 5);
		Page<Pedido> pedidos = Pservice.getAll(pageRequestPedido);
		PageRender<Pedido> pageRenderPedido= new PageRender<>("/listadepedidos", pedidos);
		
		mv.addObject("titulo", "Listado de Todos los Pedidos");
		mv.addObject("page", pageRenderPedido);
		mv.addObject("pedidos",  pedidos );
		mv.setViewName("vistas/ventas/listadodepedidos");
		return mv;
	}
	//ELIMINAR PEDIDO
	@GetMapping("/eliminarpedido/{id}")
	public ModelAndView eliminarpedido(@PathVariable("id") Integer id,ModelAndView mv, RedirectAttributes flash) {
		Pservice.delete(id);
		mv.setViewName("redirect:/modulo-ventas/listadepedidos");
		return mv;
	}

}
