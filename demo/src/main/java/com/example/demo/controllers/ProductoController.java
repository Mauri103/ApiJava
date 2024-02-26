package com.example.demo.controllers;

import com.example.demo.models.Producto;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping("obtener")
    public List<Producto> obtenerProduct(){
        return this.productoService.listProduct();
    }

    @PostMapping("alta")
    public ResponseEntity<String> addProduct(@RequestBody Producto producto){
        return (this.productoService.addProduct(producto));
    }

    @PutMapping("modificar/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Producto producto){
        return this.productoService.updateProduct(id,producto);
    }

    @DeleteMapping("baja/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
       return this.productoService.deleteProduct(id);
    }


}
