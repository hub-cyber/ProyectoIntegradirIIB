package com.proyectointegrador2b.modelos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="itempedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 5633554762353419456L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="iditempedido")
	private Integer Id;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name = "descuento")
	private Double descuento;
	
//////	@ManyToOne(fetch = FetchType.LAZY)
//////	@JoinColumn(name = "idpedido")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Pedido idpedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idproducto")
	private Producto producto;
	
	//<-------Metodos de la clase--------->
	public Double calcularImporte() {
		return this.cantidad.doubleValue()* producto.getPrecio();
	}
	
	public Double calcularImporteconDescuento() {
		Double des= this.descuento;
		Double nt = (this.calcularImporte() * des)/100;
		return nt;
	}
	
	public void aumentarCantidad() {
		this.cantidad ++;
	}

	//<----------getters y setters------------>
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

//	public Pedido getIdpedido() {
//		return idpedido;
//	}
//
//	public void setIdpedido(Pedido idpedido) {
//		this.idpedido = idpedido;
//	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "ItemPedido [Id=" + Id + ", cantidad=" + cantidad + ", descuento=" + descuento  + ", producto=" + producto + "]";
	}
	
	
	


	
	
}
