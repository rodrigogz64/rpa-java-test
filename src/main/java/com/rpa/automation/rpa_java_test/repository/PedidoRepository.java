package com.rpa.automation.rpa_java_test.repository;

import com.rpa.automation.rpa_java_test.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    // Ejercicio SQL 4: Actualizar el Estado de Pedidos Antiguos
    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.estado = 'archivado' WHERE p.fechaPedido < :fecha")
    int actualizarEstadoPedidosAntiguos(@Param("fecha") Date fecha);
}