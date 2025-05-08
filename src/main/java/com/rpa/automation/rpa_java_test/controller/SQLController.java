package com.rpa.automation.rpa_java_test.controller;

import com.rpa.automation.rpa_java_test.service.SQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SQLController {

    @Autowired
    private SQLService sqlService;
    
    @GetMapping("/api/clientes/activos")
    public List<Map<String, String>> getClientesActivos() {
        List<Object[]> clientesActivos = sqlService.obtenerClientesActivos();
        
        return clientesActivos.stream()
                .map(cliente -> Map.of(
                    "nombre", (String) cliente[0],
                    "email", (String) cliente[1]
                ))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/api/clientes/pedidos")
    public List<Map<String, Object>> getPedidosPorCliente() {
        List<Object[]> pedidosPorCliente = sqlService.contarPedidosPorCliente();
        
        return pedidosPorCliente.stream()
                .map(resultado -> Map.of(
                    "nombre", (String) resultado[0],
                    "totalPedidos", resultado[1]
                ))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/api/productos/mas-vendido")
    public Map<String, String> getProductoMasVendido() {
        String productoMasVendido = sqlService.encontrarProductoMasVendido();
        return Map.of("productoMasVendido", productoMasVendido);
    }
    
    @PostMapping("/api/pedidos/actualizar-estado")
    public Map<String, Object> actualizarEstadoPedidos(@RequestParam String fecha) {
        try {
            int pedidosActualizados = sqlService.actualizarEstadoPedidosAntiguos(fecha);
            return Map.of(
                "success", true,
                "pedidosActualizados", pedidosActualizados
            );
        } catch (ParseException e) {
            return Map.of(
                "success", false,
                "error", "Formato de fecha inválido. Use: yyyy-MM-dd"
            );
        }
    }
    
    @GetMapping("/api/consultas/sql")
    public String getConsultasSQL() {
        sqlService.imprimirConsultasSQL();
        return "Consultas SQL impresas en la consola";
    }
}