package com.proyectointegrador2b.modelos.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 8023251967788302221L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcliente")
	private Integer id;
	@Column(name="identificador")
	@NotNull
	private Integer identificador;
	@Column(name="nombre")
	@NotBlank
	private String nombre;
	@Column(name="apellidos")
	@NotBlank
	private String apellidos;
	@Column(name="email")
	@NotBlank
	@Email
	private String email;
	@Column(name="rfc")
	@NotBlank
	private String rfc;
	@Column(name="saldo")
	private Integer saldo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idusuario")
	private Usuario vendedor;
	//lista de sus pedidos
	@OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY)
	private List<Pedido> pedidos;
	public Cliente() {
		pedidos = new ArrayList<Pedido>();
	}
	
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public Integer getSaldo() {
		return saldo;
	}
	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void agregarPedidos(Pedido pedido) {
		pedidos.add(pedido);
	}

//	@Override
//	public String toString() {
//		return "Cliente [id=" + id + ", identificador=" + identificador + ", nombre=" + nombre + ", apellidos="
//				+ apellidos + ", email=" + email + ", rfc=" + rfc + ", saldo=" + saldo + ", vendedor=" + vendedor
//				+ ", pedidos=" + pedidos + "]";
//	}
	

	
	

}
