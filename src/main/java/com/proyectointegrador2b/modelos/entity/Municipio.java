package com.proyectointegrador2b.modelos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="municipio")
public class Municipio implements Serializable{

	private static final long serialVersionUID = 6368523394018999577L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idmunicipio")
	private Integer id;
	@Column(name="descripcion")
	private String descripcion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Municipio [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
	
}
