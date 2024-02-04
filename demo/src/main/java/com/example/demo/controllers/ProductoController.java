package com.example.demo.controllers;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository repo;
    private Producto producto;


    @GetMapping("producto/obtener")
    public List<Producto> getProductos(){
        return repo.findAll();
    }

    @PostMapping("producto/alta")
    public String post(@RequestBody Producto producto){
        repo.save(producto);
        return "Producto guardado";
    }

    @PutMapping("producto/modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Producto producto){
        Producto updateProdcuto = repo.findById(id).get();
        updateProdcuto.setDescripcion(producto.getDescripcion());
        updateProdcuto.setPrecio(producto.getPrecio());
        updateProdcuto.setStock(producto.getStock());
        repo.save(updateProdcuto);
        return "Producto modificado";
    }

    @DeleteMapping("producto/baja/{id}")
    public String delete(@PathVariable Long id){
        Producto productoDelete = repo.findById(id).get();
        repo.delete(productoDelete);
        return "Producto eliminado";
    }


}
