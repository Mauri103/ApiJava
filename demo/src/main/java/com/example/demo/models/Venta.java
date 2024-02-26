package com.example.demo.models;

import jakarta.persistence.*;

import java.util.List;
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

    @OneToMany(mappedBy = "venta", fetch=FetchType.EAGER,  cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVenta;


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
