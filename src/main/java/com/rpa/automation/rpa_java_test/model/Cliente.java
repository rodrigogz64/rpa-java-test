package com.rpa.automation.rpa_java_test.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente {
    private int id;
    private String nombre;
    private double saldo;
    private LocalDate ultimaFecha;
    private String tipoCliente;
    
    // Constructor
    public Cliente(int id, String nombre, double saldo, LocalDate ultimaFecha, String tipoCliente) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
        this.ultimaFecha = ultimaFecha;
        this.tipoCliente = tipoCliente;
    }
    
    // Getters y setters
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public LocalDate getUltimaFecha() {
        return ultimaFecha;
    }
    
    public String getTipoCliente() {
        return tipoCliente;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "ID: " + id + ", Nombre: " + nombre + ", Saldo: " + String.format("%.2f", saldo) + 
               ", Fecha: " + ultimaFecha.format(formatter) + ", Tipo: " + tipoCliente;
    }
    
    // Método para convertir el cliente a formato de archivo
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return id + "," + nombre + "," + String.format("%.2f", saldo) + "," + 
               ultimaFecha.format(formatter) + "," + tipoCliente;
    }
}