package com.worldoffice.productos.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worldoffice.productos.conversor.ProductoConversor;
import com.worldoffice.productos.dominio.Producto;
import com.worldoffice.productos.servicio.ProductoServicio;

@RestController
@RequestMapping("producto")
@CrossOrigin
public class ProductoControlador {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductoControlador.class);

	@Autowired
	private ProductoServicio servicio;

	@Autowired
	private ProductoConversor conversor;

	@GetMapping("/pagina")
	public Page<Producto> buscarPaginado(@RequestParam(name = "nombre", required = true) String nombre,
			@RequestParam(name = "precio-min", required = true) double precioMin,
			@RequestParam(name = "precio-max", required = true) double precioMax,
			@RequestParam(name = "marca", required = true) String marca,
			@RequestParam(value = "paginaTamano", required = true) int paginaTamano,
			@RequestParam(value = "paginaNumero", required = true) int paginaNumero) {

		LOGGER.info("Inicio consumo API Producto buscarPaginado");

		try {
			return servicio.obtenerPaginado(nombre, marca, precioMin, precioMax, paginaTamano, paginaNumero);
		} finally {
			LOGGER.info("Finalizando consumo API Producto buscarPaginado");
		}

	}
}
