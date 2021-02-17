package com.worldoffice.productos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worldoffice.productos.conversor.CarritoConversor;
import com.worldoffice.productos.dto.CarritoDTO;
import com.worldoffice.productos.dto.ProductoDTO;
import com.worldoffice.productos.servicio.CarritoServicio;

import javassist.NotFoundException;

@RestController
@RequestMapping("carrito")
@CrossOrigin
public class CarritoControlador {

	@Autowired
	private CarritoServicio servicio;

	@Autowired
	private CarritoConversor conversor;

	@GetMapping
	public List<CarritoDTO> obtenerTodos() {
		return conversor.listEntidadADto(servicio.obtenerTodos());
	}

	@GetMapping("/{id}")
	public CarritoDTO obtenerPorId(@PathVariable(name = "id") Long id) throws NotFoundException {
		return conversor.entidadADto(servicio.obtenerPorId(id));
	}

	@PostMapping
	public CarritoDTO crearCarrito() {
		return conversor.entidadADto(servicio.crearCarrito());
	}

	@PutMapping("/{id}")
	public CarritoDTO agregarProducto(@PathVariable(name = "id") Long id, @RequestBody ProductoDTO producto)
			throws Exception {
		return conversor.entidadADto(servicio.agregarProducto(id, producto.getId(), producto.getCantidad()));
	}

	@PostMapping("/vaciar-carrito/{id}")
	public CarritoDTO vaciarCarrito(@PathVariable(name = "id") Long id) throws NotFoundException {
		return conversor.entidadADto(servicio.vaciarCarrito(id));
	}

	@PostMapping("/finalizar-compra/{id}")
	public CarritoDTO finalizarCompra(@PathVariable(name = "id") Long id) throws NotFoundException {
		return conversor.entidadADto(servicio.finalizarCompra(id));
	}
}
