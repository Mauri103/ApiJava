package com.example.demo.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "montoTotal")
    private int montoTotal;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToMany(mappedBy = "ventas")
    private Set<Producto> productos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
