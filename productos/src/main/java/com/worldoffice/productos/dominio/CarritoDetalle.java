package com.worldoffice.productos.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrito_detalle")
public class CarritoDetalle {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "carrito_id")
	private Long carritoId;
	
	@Column(name = "producto_id")
	private Long productoId;
	
	@Column(name = "cantidad")
	private Long cantidad;

	@OneToOne
	@JoinColumn(name = "id",referencedColumnName = "producto_id")
	private Producto producto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCarritoId() {
		return carritoId;
	}

	public void setCarritoId(Long carritoId) {
		this.carritoId = carritoId;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
