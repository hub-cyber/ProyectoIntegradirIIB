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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="direccion")
public class Direccion implements Serializable{

	private static final long serialVersionUID = -6027069643414712449L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="iddireccion")
	private Integer id;
	@Column(name="colonia")
	@NotBlank
	private String colonia;
	@Column(name="calle")
	@NotBlank
	private String calle;
	@Column(name="numext")
	@NotNull
	private Integer numext;
	@Column(name="referencia")
	private String referencia;
	@Column(name="codigopostal")
	@NotNull
	private Integer zip;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idmunicipio")
	private Municipio idmunicipio;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcliente")
	private Cliente idcliente;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public Integer getNumext() {
		return numext;
	}
	public void setNumext(Integer numext) {
		this.numext = numext;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	public Municipio getIdmunicipio() {
		return idmunicipio;
	}
	public void setIdmunicipio(Municipio idmunicipio) {
		this.idmunicipio = idmunicipio;
	}
	public Cliente getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(Cliente idcliente) {
		this.idcliente = idcliente;
	}
	@Override
	public String toString() {
		return "Direccion [id=" + id + ", colonia=" + colonia + ", calle=" + calle + ", numext=" + numext
				+ ", referencia=" + referencia + ", zip=" + zip + ", idmunicipio=" + idmunicipio + ", idcliente="
				+ idcliente + "]";
	}
	
	
}
