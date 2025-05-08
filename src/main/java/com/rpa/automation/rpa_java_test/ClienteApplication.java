package com.rpa.automation.rpa_java_test;

import  com.rpa.automation.rpa_java_test.model.Cliente;
import  com.rpa.automation.rpa_java_test.service.ClienteProcessor;
import  com.rpa.automation.rpa_java_test.service.SQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class ClienteApplication implements CommandLineRunner {

    @Autowired
    private ClienteProcessor clienteProcessor;
    
    @Autowired
    private SQLService sqlService;

    public static void main(String[] args) {
        SpringApplication.run(ClienteApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Evaluación de Habilidades Java y Base de Datos");
        System.out.println("==============================================");
        
        // SECCIÓN I - PROCESAMIENTO DE ARCHIVOS
        System.out.println("\nSECCIÓN I - RESOLUCIÓN DE PROBLEMAS JAVA");
        System.out.println("--------------------------------------------");
        
        String rutaArchivo;
        if (args.length > 0) {
            rutaArchivo = args[0];
        } else {
            System.out.print("Ingrese la ruta del archivo de clientes: ");
            rutaArchivo = scanner.nextLine();
            if (rutaArchivo.trim().isEmpty()) {
                rutaArchivo = "src/main/resources/clientes.txt";
            }
        }
        
        // 1. Leer el archivo y parsear los datos
        clienteProcessor.leerArchivoClientes(rutaArchivo);
        
        // 3. Ordenar los clientes por saldo de forma descendente
        clienteProcessor.ordenarClientesPorSaldo();
        
        // Imprimir los clientes ordenados por saldo
        System.out.println("\nClientes ordenados por saldo (descendente):");
        System.out.println("----------------------------------------");
        for (Cliente cliente : clienteProcessor.getClientes()) {
            System.out.println(cliente);
        }
        
        // 4. Filtrar clientes por fecha y tipo
        System.out.print("\nIngrese la fecha límite (formato yyyy-MM-dd): ");
        String fechaLimite = scanner.nextLine();
        if (fechaLimite.trim().isEmpty()) {
            fechaLimite = "2024-12-31"; // Usar fecha por defecto
        }
        
        System.out.print("Ingrese el tipo de cliente para filtrar: ");
        String tipoCliente = scanner.nextLine();
        if (tipoCliente.trim().isEmpty()) {
            tipoCliente = "Premium"; // Usar tipo por defecto
        }
        
        List<Cliente> clientesFiltrados = clienteProcessor.filtrarClientesPorFechaYTipo(fechaLimite, tipoCliente);
        
        // Imprimir los clientes filtrados
        System.out.println("\nClientes filtrados por fecha (anterior a " + fechaLimite + ") y tipo (" + tipoCliente + "):");
        System.out.println("----------------------------------------");
        for (Cliente cliente : clientesFiltrados) {
            System.out.println(cliente);
        }
        
        // 5. Agrupar los clientes por tipo y calcular el saldo promedio
        Map<String, Double> saldoPromedioPorTipo = clienteProcessor.calcularSaldoPromedioPorTipo();
        
        // Imprimir el saldo promedio por tipo de cliente
        System.out.println("\nSaldo promedio por tipo de cliente:");
        System.out.println("--------------------------------");
        for (Map.Entry<String, Double> entry : saldoPromedioPorTipo.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.format("%.2f", entry.getValue()));
        }
        
        // 6. Generar un nuevo archivo de texto con los clientes ordenados
        String rutaArchivoOrdenado = "clientes_ordenados.txt";
        clienteProcessor.generarArchivoClientesOrdenados(rutaArchivoOrdenado);
        System.out.println("\nArchivo de clientes ordenados generado: " + rutaArchivoOrdenado);
        
        // Generar archivo de errores
        String rutaArchivoErrores = "errores.log";
        clienteProcessor.generarArchivoErrores(rutaArchivoErrores);
        
        // Mostrar errores si los hay
        if (!clienteProcessor.getErrores().isEmpty()) {
            System.out.println("\nSe encontraron errores durante el procesamiento:");
            System.out.println("----------------------------------------------");
            for (String error : clienteProcessor.getErrores()) {
                System.out.println(error);
            }
            System.out.println("\nArchivo de errores generado: " + rutaArchivoErrores);
        }
        
        // SECCIÓN II - SQL
        System.out.println("----------------------------------------");
        System.out.println("\nSECCIÓN II - CONOCIMIENTO DE SQL");
        System.out.print("¿Desea ver las consultas SQL? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("------------------------------------");
            sqlService.imprimirConsultasSQL();
        }
        
        scanner.close();
    }
}