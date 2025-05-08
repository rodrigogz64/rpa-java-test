package com.rpa.automation.rpa_java_test.service;

import com.rpa.automation.rpa_java_test.repository.ClienteRepository;
import com.rpa.automation.rpa_java_test.repository.PedidoRepository;
import com.rpa.automation.rpa_java_test.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SQLService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    // Ejercicio SQL 1: Selección de Clientes Activos
    public List<Object[]> obtenerClientesActivos() {
        return clienteRepository.findClientesActivos();
    }
    
    // Ejercicio SQL 2: Contar Pedidos por Cliente
    public List<Object[]> contarPedidosPorCliente() {
        return clienteRepository.countPedidosPorCliente();
    }
    
    // Ejercicio SQL 3: Encontrar el Producto Más Vendido
    public String encontrarProductoMasVendido() {
        return productoRepository.findProductoMasPedido();
    }
    
    // Ejercicio SQL 4: Actualizar el Estado de Pedidos Antiguos
    public int actualizarEstadoPedidosAntiguos(String fechaStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse(fechaStr);
        return pedidoRepository.actualizarEstadoPedidosAntiguos(fecha);
    }
    
    // Método para imprimir las consultas SQL nativas que responden a los ejercicios
    public void imprimirConsultasSQL() {
        System.out.println("\n== CONSULTAS SQL NATIVAS ==");
        
        System.out.println("\nEjercicio SQL 1: Selección de Clientes Activos");
        System.out.println("----------------------------------------");
        System.out.println("SELECT nombre, email FROM Cliente WHERE estado = 'activo';");
        
        System.out.println("\nEjercicio SQL 2: Contar Pedidos por Cliente");
        System.out.println("----------------------------------------");
        System.out.println("SELECT c.nombre, COUNT(p.id_pedido) as total_pedidos\n" + 
                           "FROM Cliente c\n" + 
                           "LEFT JOIN Pedido p ON c.id_cliente = p.id_cliente\n" + 
                           "GROUP BY c.nombre;");
        
        System.out.println("\nEjercicio SQL 3: Encontrar el Producto Más Vendido");
        System.out.println("----------------------------------------");
        System.out.println("SELECT p.nombre, SUM(dp.cantidad) as total_vendido\n" + 
                           "FROM Producto p\n" + 
                           "JOIN DetallePedido dp ON p.id_producto = dp.id_producto\n" + 
                           "GROUP BY p.nombre\n" + 
                           "ORDER BY total_vendido DESC\n" + 
                           "LIMIT 1;");
        
        System.out.println("\nEjercicio SQL 4: Actualizar el Estado de Pedidos Antiguos");
        System.out.println("----------------------------------------");
        System.out.println("UPDATE Pedido\n" + 
                           "SET estado = 'archivado'\n" + 
                           "WHERE fecha_pedido < '2024-01-01';");
    }
}