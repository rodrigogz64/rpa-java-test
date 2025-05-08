package com.rpa.automation.rpa_java_test.repository;

import com.rpa.automation.rpa_java_test.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    // Ejercicio SQL 3: Encontrar el Producto Más Vendido
    @Query("SELECT p.nombre FROM Producto p JOIN p.detalles d GROUP BY p.nombre ORDER BY SUM(d.cantidad) DESC LIMIT 1")
    String findProductoMasPedido();
}