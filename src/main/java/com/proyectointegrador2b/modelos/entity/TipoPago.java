package com.proyectointegrador2b.modelos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipodepago")
public class TipoPago implements Serializable {

	private static final long serialVersionUID = -8081511089365890979L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtipodepago")
	private Integer Id;
	@Column(name = "descripcion")
	private String descripcion;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "TipoCredito [Id=" + Id + ", descripcion=" + descripcion + "]";
	}

}
