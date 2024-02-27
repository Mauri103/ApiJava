package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="detalleVenta")

public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalleVenta;

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

    public Long getId_detalleVenta() {
        return id_detalleVenta;
    }

    public void setId_detalleVenta(Long id_detalleVenta) {
        this.id_detalleVenta = id_detalleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public com.example.demo.models.Producto getProducto() {
        return Producto;
    }

    public void setProducto(com.example.demo.models.Producto producto) {
        Producto = producto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }
}
