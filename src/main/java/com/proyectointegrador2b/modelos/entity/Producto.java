package com.proyectointegrador2b.modelos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = -6811199445978341180L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idproducto")
	private Integer id;
	@Column(name="identificador")
	@NotNull
	private Integer identificador;
	@NotBlank
	@Column(name="nombre")
	private String nombre;
	@NotBlank
	@Column(name="marca")
	private String marca;
	@NotBlank
	@Column(name="proveedor")
	private String proveedor;
	@NotNull
	@Column(name="precioenlista")
	private Double precio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", identificador=" + identificador + ", nombre=" + nombre + ", marca=" + marca
				+ ", proveedor=" + proveedor + ", precio=" + precio + "]";
	}
	
	
	
	
	
}
