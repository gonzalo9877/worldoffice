package com.worldoffice.productos.dominio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carrito")
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "facturado")
	private boolean facturado;
	
	@OneToMany
	@JoinColumn(name = "carrito_id",referencedColumnName = "id")
	private List<CarritoDetalle> detalle;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public boolean isFacturado() {
		return facturado;
	}

	public void setFacturado(boolean facturado) {
		this.facturado = facturado;
	}

	public List<CarritoDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<CarritoDetalle> detalle) {
		this.detalle = detalle;
	}
	
	
}
