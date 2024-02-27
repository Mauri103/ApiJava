package com.example.demo.services;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repositorio;

    public ResponseEntity<String> addProduct(Producto producto){
        repositorio.save(producto);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }
    public List<Producto> listProduct(){
        return repositorio.findAll();

    }
    public List<Producto> getProductsByIds(List<Long> ids) {
        return repositorio.findAllById(ids);
    }

    public ResponseEntity<String> updateProduct(Long id, Producto producto){
        Producto updateProdcuto = repositorio.findById(id).get();
        updateProdcuto.setDescripcion(producto.getDescripcion());
        updateProdcuto.setPrecio(producto.getPrecio());
        updateProdcuto.setStock(producto.getStock());
        repositorio.save(updateProdcuto);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }

    public ResponseEntity<String> deleteProduct(Long id){
        Producto productoDelete = repositorio.findById(id).get();
        repositorio.delete(productoDelete);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }

}
