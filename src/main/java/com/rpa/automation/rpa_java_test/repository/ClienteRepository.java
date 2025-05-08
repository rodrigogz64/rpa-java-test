package com.rpa.automation.rpa_java_test.repository;

import com.rpa.automation.rpa_java_test.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    
    // Ejercicio SQL 1: Selección de Clientes Activos
    @Query("SELECT c.nombre, c.email FROM ClienteEntity c WHERE c.estado = 'activo'")
    List<Object[]> findClientesActivos();
    
    // Ejercicio SQL 2: Contar Pedidos por Cliente
    @Query("SELECT c.nombre, COUNT(p) FROM ClienteEntity c LEFT JOIN c.pedidos p GROUP BY c.nombre")
    List<Object[]> countPedidosPorCliente();
}