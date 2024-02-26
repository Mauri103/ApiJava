package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="detalleVenta")

public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad_producto")
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto Producto;

    @Column(name = "precioProducto")
    private Double precioProducto;

    @ManyToOne
    @JoinColumn(name = "fk-venta", nullable = false, updatable = false)
    private Venta venta;
}
