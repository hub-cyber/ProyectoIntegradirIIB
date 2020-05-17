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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cobranza")
public class Cobranza implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcobranza")
	private Integer id;
	@Column(name="folio")
	@NotEmpty
	private String folio;
	@Column(name="descripcion")
	@NotEmpty
	private String descripcion;
	@Column(name="observacion")
	private String observacion;
	@Column(name="referencia")
	@NotNull
	private String referencia;
	@Column(name="formadepago")
	@NotNull
	private String tp;
	@Column(name="importe")
	@NotNull
	private Integer importe;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idpedido")
	private Pedido idpedido;
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
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	public Integer getImporte() {
		return importe;
	}
	public void setImporte(Integer importe) {
		this.importe = importe;
	}
	public Pedido getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(Pedido idpedido) {
		this.idpedido = idpedido;
	}
	
	
}
