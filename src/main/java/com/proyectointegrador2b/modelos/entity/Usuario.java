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
@Table(name="usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = -3697787198056416697L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idusuario")
	private Integer id;
	@NotBlank
	@Column(name="nombre")
	private String nombre;
	@NotBlank
	@Column(name="apellido")
	private String apellido;
	@NotBlank
	@Column(name="username")
	private String username;
	@NotBlank
	@Column(name="password")
	private String password;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="iduser_role")
	@NotNull
	private UsuarioRol iduserRole;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsuarioRol getIduserRole() {
		return iduserRole;
	}
	public void setIduserRole(UsuarioRol iduserRole) {
		this.iduserRole = iduserRole;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", username=" + username
				+ ", password=" + password + ", iduserRole=" + iduserRole + "]";
	}
	
	

}
