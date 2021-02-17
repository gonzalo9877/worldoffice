package com.worldoffice.productos.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.worldoffice.productos.dominio.Producto;
import com.worldoffice.productos.repositorio.ProductoJPA;

@Service
public class ProductoServicio {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductoServicio.class);

	@Autowired
	private ProductoJPA dao;
	
	public Page<Producto> obtenerPaginado(String nombre, String marca, double precioMin, double precioMax, int paginaTamano,
			int paginaNumero) {
		
		LOGGER.info("BD");
		Pageable page = PageRequest.of(paginaNumero, paginaTamano);
		return dao.findAllByNombreContainingAndMarcaContainingAndPrecioBetween(nombre,marca,precioMin,precioMax,page);
	}
}
