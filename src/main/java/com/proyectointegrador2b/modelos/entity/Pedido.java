package com.proyectointegrador2b.modelos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{
	private static final long serialVersionUID = 4233320137206146107L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idpedido")
	private Integer id;
	@Column(name="folio")
	private String folio;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="observacion")
	private String observacion;
	@Temporal(TemporalType.DATE)
	@Column(name="creacion")
	private Date createAt;
	@Column(name="total")
	private Integer total;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idtipodecredito")
	private TipoCredito tc;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idtipodepago")
	private TipoPago tp;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcliente")
	private Cliente cliente;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "idpedido")
	private List<ItemPedido> items;
	
	
	public Pedido() {
		this.items= new ArrayList<ItemPedido>();
	}

	//<------Metodos de la Clase------>
	//Metodo que se encarga de crear la fecha de manera interna sin necesidad del formulario
	@PrePersist
	public void  persist() {
		createAt= new Date();
	}
	
	
	
//	public Double SubTotal() {
//		double tcd=0.0;
//		double tsd=0.0;
//		double subTotal = 0.0;
//		int size = items.size();
//		for(int i=0; i< size; i++) {
//			if(items.get(i).getDescuento()!= null) {
//				tcd += items.get(i).calcularImporteconDescuento();
//			}else {
//				tsd += items.get(i).calcularImporte();
//			}
//			subTotal= tcd + tsd;
//		}
//		return subTotal;
//	}
//	
//	public Double descuento() {
//		double tcd=0.0;
//		int size = items.size();
//		for(int i=0; i< size; i++) {
//			if(items.get(i).getDescuento()!= null) {
//				tcd += items.get(i).calcularImporteconDescuento();
//			}
//		}
//		return tcd;
//	}
//	
//	public Double CalcularImpuesto() {
//		return this.SubTotal() * 1.16;
//	}

//--------------getters y setters------------
	

	public void addItemPedido(ItemPedido item) {
		this.items.add(item);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public TipoCredito getTc() {
		return tc;
	}

	public void setTc(TipoCredito tc) {
		this.tc = tc;
	}

	public TipoPago getTp() {
		return tp;
	}

	public void setTp(TipoPago tp) {
		this.tp = tp;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItems() {
		return items;
	}

	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", folio=" + folio + ", descripcion=" + descripcion + ", observacion=" + observacion
				+ ", createAt=" + createAt + ", total=" + total + ", tc=" + tc + ", tp=" + tp + ", cliente=" + cliente
				+ ", items=" + items + "]";
	}
	

	


	
	
	

	
	
}
