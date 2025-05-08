package com.rpa.automation.rpa_java_test.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class ClienteEntity {
    
    @Id
    @Column(name = "id_cliente")
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "saldo")
    private Double saldo;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    
    // Constructores
    public ClienteEntity() {
    }
    
    public ClienteEntity(Long id, String nombre, String email, String estado, String tipo, Double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
        this.tipo = tipo;
        this.saldo = saldo;
    }
    
    // Getters y setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", estado='" + estado + '\'' +
                ", tipo='" + tipo + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}