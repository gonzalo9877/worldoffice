package com.worldoffice.productos.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.worldoffice.productos.dominio.Carrito;
import com.worldoffice.productos.dto.CarritoDTO;
import com.worldoffice.productos.dto.ProductoDTO;

@Component
public class CarritoConversor {

	@Autowired
	private ProductoConversor productoConversor;
	
	public List<CarritoDTO> listEntidadADto(List<Carrito> listaEntidad) {
		List<CarritoDTO> listaDto = new ArrayList<>();
		listaEntidad.forEach(entidad -> listaDto.add(entidadADto(entidad)));
		return listaDto;
	}

	public CarritoDTO entidadADto(Carrito entidad) {
		CarritoDTO dto = new CarritoDTO();
		List<ProductoDTO> productosDTO = new ArrayList<>();
		dto.setId(entidad.getId());
		dto.setFecha(entidad.getFecha());
		dto.setFacturado(entidad.isFacturado());
		entidad.getDetalle().forEach(detalle -> {
			ProductoDTO producto = productoConversor.entidadADto(detalle.getProducto());
			producto.setCantidad(detalle.getCantidad());
			productosDTO.add(producto);
		});
		dto.setProductos(productosDTO);
		return dto;
	}

}
