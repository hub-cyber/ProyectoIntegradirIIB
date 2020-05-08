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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{
	private static final long serialVersionUID = 4233320137206146107L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idpedido")
	private Integer id;
	@Column(name="folio")
	@NotEmpty
	private String folio;
	@Column(name="descripcion")
	@NotEmpty
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
	@NotNull
	private TipoCredito tc;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idtipodepago")
	@NotNull
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
	
	
	
	public Double SubTotal() {
		double tcd=0.0;
		double tsd=0.0;
		double subTotal = 0.0;
		int size = items.size();
		for(int i=0; i< size; i++) {
			if(items.get(i).getDescuento()>0) {
				tcd += items.get(i).calcularImporteconDescuento();
			}else {
				tsd += items.get(i).calcularImporte();
			}
			subTotal= tcd + tsd;
		}
		return subTotal;
	}
	public Integer ImporteNeto() {
		int tsd=0;
	int size = items.size();
	for(int i=0; i< size; i++) {
			tsd += items.get(i).calcularImporte();
		}
		return tsd;
	}
	public Integer descuento() {
		int tcd=0;
		int tsd=0;
	int size = items.size();
	for(int i=0; i< size; i++) {
			
			tcd += items.get(i).calcularImporteconDescuento();
			tsd += items.get(i).calcularImporte();
		}
		return tsd-tcd;
	}
	
	public Double CalcularImpuesto() {
		return (this.SubTotal() * 16)/100;
	}
	
	public Double GranTotal() {
		return this.SubTotal() + this.CalcularImpuesto();
	}

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
}
