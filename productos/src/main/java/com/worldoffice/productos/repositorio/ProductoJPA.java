package com.worldoffice.productos.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.worldoffice.productos.dominio.Producto;

@Repository
public interface ProductoJPA extends JpaRepository<Producto, Long>{

	Page<Producto> findAllByNombreContainingAndMarcaContainingAndPrecioBetween(String nombre, String Marca,
			Double precioMin, Double precioMax, Pageable pageable);
}
