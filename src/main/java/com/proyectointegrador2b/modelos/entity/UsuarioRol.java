package com.proyectointegrador2b.modelos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user_role")
public class UsuarioRol implements Serializable{
	private static final long serialVersionUID = -8740526149169869341L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name="iduser_role")
	private Integer id;
	@Column(name="codigo")
	private String codigo;
	@Column(name="descripcion")
	private String descripcion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "UsuarioRol [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
	

}
