package com.worldoffice.productos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.worldoffice.productos.dominio.Carrito;

@Repository
public interface CarritoJPA extends JpaRepository<Carrito, Long> {

}
