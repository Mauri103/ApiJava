package com.example.demo.controllers;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;
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
    private ProductoRepository repo;
    private Producto producto;


    @GetMapping("obtener")
    public List<Producto> obtener(){
        return this.productoService.listar();
    }

    @PostMapping("alta")
    public ResponseEntity<String> agregar(@RequestBody Producto producto){
        return (this.productoService.addProducto(producto));
    }

    @PutMapping("modificar/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Producto producto){
        return this.productoService.modificar(id,producto);
    }

    @DeleteMapping("baja/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
       return this.productoService.eliminar(id);
    }


}
