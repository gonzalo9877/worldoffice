package com.worldoffice.productos.conversor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.worldoffice.productos.dominio.Producto;
import com.worldoffice.productos.dto.ProductoDTO;

@Component
public class ProductoConversor {

	public Page<ProductoDTO> pageEntidadADto(Page<Producto> obtenerPaginado) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ProductoDTO entidadADto(Producto entidad) {
		ProductoDTO dto = new ProductoDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setMarca(entidad.getMarca());
		dto.setPrecio(entidad.getPrecio());
		dto.setStock(entidad.getStock());
		dto.setEstado(entidad.getEstado());
		dto.setDescuento(entidad.getDescuento());
		return dto;
	}

}
