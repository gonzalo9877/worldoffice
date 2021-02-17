package com.worldoffice.productos.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldoffice.productos.dominio.Carrito;
import com.worldoffice.productos.dominio.CarritoDetalle;
import com.worldoffice.productos.dominio.Producto;
import com.worldoffice.productos.repositorio.CarritoDetalleJPA;
import com.worldoffice.productos.repositorio.CarritoJPA;
import com.worldoffice.productos.repositorio.ProductoJPA;

import javassist.NotFoundException;

@Service
public class CarritoServicio {

	@Autowired
	private CarritoJPA dao;
	
	@Autowired
	private CarritoDetalleJPA daoDetalle;
	
	@Autowired
	private ProductoJPA daoProducto;
	
	public List<Carrito> obtenerTodos() {
		return dao.findAll();
	}

	public Carrito obtenerPorId(Long id) throws NotFoundException {
		Optional<Carrito> opt = dao.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		} else {
			throw new NotFoundException("Id de Carrito no encontrado");
		}
	}

	public Carrito agregarProducto(Long id, Long idProducto, Long cantidad) throws Exception {
		Carrito carrito = obtenerPorId(id);
		Optional<Producto> opt = daoProducto.findById(idProducto);
		if(opt.isPresent()) {
			if(cantidad > opt.get().getStock()) {
				throw new Exception("No hay existencias suficientes");
			}
		CarritoDetalle carritoDetalle = new CarritoDetalle();
		carritoDetalle.setCarritoId(id);
		carritoDetalle.setCantidad(cantidad);
		carritoDetalle.setProductoId(idProducto);
		carritoDetalle.setProducto(opt.get());
		daoDetalle.save(carritoDetalle);
		return carrito;
		} else {
			throw new NotFoundException("Id de Carrito no encontrado");
		}
	}

	public Carrito crearCarrito() {
		Carrito carrito = new Carrito();
		carrito.setFacturado(false);
		carrito.setFecha(new Date());
		carrito.setDetalle(new ArrayList<>());
		return dao.save(carrito);
	}

	public Carrito vaciarCarrito(Long id) throws NotFoundException {
		Carrito carrito = obtenerPorId(id);
		daoDetalle.deleteAllByCarritoId(carrito.getId());		
		return carrito;
	}

	public Carrito finalizarCompra(Long id) throws NotFoundException {
		Carrito carrito = obtenerPorId(id);
		carrito.getDetalle().forEach(detalle -> {
			Producto producto = detalle.getProducto();
			producto.setStock(producto.getStock()-detalle.getCantidad());
		});
		return dao.save(carrito);
	}

}
