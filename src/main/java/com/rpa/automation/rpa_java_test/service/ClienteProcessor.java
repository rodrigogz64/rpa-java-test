package com.rpa.automation.rpa_java_test.service;

import com.rpa.automation.rpa_java_test.model.Cliente;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class ClienteProcessor {

    private List<Cliente> clientes = new ArrayList<>();
    private List<String> errores = new ArrayList<>();

    // 1. Leer el archivo y parsear los datos
    public void leerArchivoClientes(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroLinea = 0;
            
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                procesarLinea(linea, numeroLinea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            errores.add("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void procesarLinea(String linea, int numeroLinea) {
        String[] datos = linea.split(",");
        
        // Verificar formato correcto (5 campos)
        if (datos.length != 5) {
            String error = "Línea " + numeroLinea + ": formato incorrecto - " + linea;
            errores.add(error);
            return;
        }
        
        try {
            int id = Integer.parseInt(datos[0].trim());
            String nombre = datos[1].trim();
            double saldo = Double.parseDouble(datos[2].trim());
            
            // Parsear fecha con formato yyyy-MM-dd
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(datos[3].trim(), formatter);
            
            String tipoCliente = datos[4].trim();
            
            // 2. Almacenar los datos en la estructura
            Cliente cliente = new Cliente(id, nombre, saldo, fecha, tipoCliente);
            clientes.add(cliente);
            
        } catch (NumberFormatException e) {
            String error = "Línea " + numeroLinea + ": error al parsear números - " + linea;
            errores.add(error);
        } catch (DateTimeParseException e) {
            String error = "Línea " + numeroLinea + ": error en formato de fecha - " + linea;
            errores.add(error);
        } catch (Exception e) {
            String error = "Línea " + numeroLinea + ": " + e.getMessage() + " - " + linea;
            errores.add(error);
        }
    }

    // 3. Ordenar los clientes por saldo de forma descendente
    public void ordenarClientesPorSaldo() {
        // Implementación del algoritmo de ordenamiento por selección
        for (int i = 0; i < clientes.size() - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < clientes.size(); j++) {
                if (clientes.get(j).getSaldo() > clientes.get(maxIdx).getSaldo()) {
                    maxIdx = j;
                }
            }
            // Intercambiar los elementos
            Cliente temp = clientes.get(maxIdx);
            clientes.set(maxIdx, clientes.get(i));
            clientes.set(i, temp);
        }
    }

    // 4. Filtrar clientes por fecha y tipo
    public List<Cliente> filtrarClientesPorFechaYTipo(String fechaLimite, String tipoCliente) {
        List<Cliente> clientesFiltrados = new ArrayList<>();
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaLimiteDate = LocalDate.parse(fechaLimite, formatter);
            
            for (Cliente cliente : clientes) {
                if (cliente.getUltimaFecha().isBefore(fechaLimiteDate) && 
                    cliente.getTipoCliente().equals(tipoCliente)) {
                    clientesFiltrados.add(cliente);
                }
            }
        } catch (DateTimeParseException e) {
            errores.add("Error al parsear la fecha límite: " + fechaLimite);
        }
        
        return clientesFiltrados;
    }

    // 5. Agrupar los clientes por tipo y calcular el saldo promedio
    public Map<String, Double> calcularSaldoPromedioPorTipo() {
        Map<String, List<Cliente>> clientesPorTipo = new HashMap<>();
        Map<String, Double> saldoPromedioPorTipo = new HashMap<>();
        
        // Agrupar clientes por tipo
        for (Cliente cliente : clientes) {
            String tipo = cliente.getTipoCliente();
            if (!clientesPorTipo.containsKey(tipo)) {
                clientesPorTipo.put(tipo, new ArrayList<>());
            }
            clientesPorTipo.get(tipo).add(cliente);
        }
        
        // Calcular promedio para cada tipo
        for (Map.Entry<String, List<Cliente>> entry : clientesPorTipo.entrySet()) {
            double sumaSaldos = 0;
            for (Cliente cliente : entry.getValue()) {
                sumaSaldos += cliente.getSaldo();
            }
            double promedio = sumaSaldos / entry.getValue().size();
            saldoPromedioPorTipo.put(entry.getKey(), promedio);
        }
        
        return saldoPromedioPorTipo;
    }

    // 6. Generar un nuevo archivo de texto con los clientes ordenados
    public void generarArchivoClientesOrdenados(String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
            errores.add("Error al escribir el archivo: " + e.getMessage());
        }
    }

    // Generar archivo de errores
    public void generarArchivoErrores(String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String error : errores) {
                writer.write(error);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de errores: " + e.getMessage());
        }
    }

    // Getters para mostrar resultados
    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<String> getErrores() {
        return errores;
    }
}