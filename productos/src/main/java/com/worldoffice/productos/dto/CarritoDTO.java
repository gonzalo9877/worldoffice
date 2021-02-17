package com.worldoffice.productos.dto;

import java.util.Date;
import java.util.List;

public class CarritoDTO {

	private Long id;
	private Date fecha;
	private boolean facturado;
	private List<ProductoDTO> productos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isFacturado() {
		return facturado;
	}
	public void setFacturado(boolean facturado) {
		this.facturado = facturado;
	}
	public List<ProductoDTO> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductoDTO> productos) {
		this.productos = productos;
	}
	
	
}
