package com.worldoffice.productos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.worldoffice.productos.dominio.CarritoDetalle;

public interface CarritoDetalleJPA extends JpaRepository<CarritoDetalle, Long> {

	@Modifying
	@Transactional
	void deleteAllByCarritoId(Long carritoId);
}
