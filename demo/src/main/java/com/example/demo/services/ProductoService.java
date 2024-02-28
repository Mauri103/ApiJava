package com.example.demo.services;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repositorio;

    public ResponseEntity<String> addProduct(Producto producto){
        try{
            if (producto.getDescripcion() == null){
                return ResponseEntity.status(400).body("400 -> Ha ocurrido un error\n");
            }else {
                repositorio.save(producto);
                return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
            }
        }catch (Exception e) {
            return ResponseEntity.status(400).body("400 -> Ha ocurrido un error\n");
        }
    }
    public List<Producto> listProduct(){
        return repositorio.findAll();

    }
    public List<Producto> getProductsByIds(List<Long> ids) {
        return repositorio.findAllById(ids);
    }

    public ResponseEntity<String> updateProduct(Long id, Producto producto){
        try{
            Optional<Producto> update = repositorio.findById(id);
            if(update.isPresent()){
                Producto updateProdcuto = repositorio.findById(id).get();
                updateProdcuto.setDescripcion(producto.getDescripcion());
                updateProdcuto.setPrecio(producto.getPrecio());
                updateProdcuto.setStock(producto.getStock());
                repositorio.save(updateProdcuto);
                return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
            }else {
                return ResponseEntity.status(400).body("400 -> Ha ocurrido un error o el producto no existe\n");
            }
        }catch (Exception e) {
            return ResponseEntity.status(400).body("400 -> Ha ocurrido un error o el producto no existe\n");
        }

    }

    public ResponseEntity<String> deleteProduct(Long id){
       try {
            Optional<Producto> delete = repositorio.findById(id);
            if (delete.isPresent()){
                Producto productoDelete = repositorio.findById(id).get();
                repositorio.delete(productoDelete);
                return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
            }else {
                return ResponseEntity.status(400).body("400 -> El producto no existe\n");
            }
       }catch (Exception e) {
           return ResponseEntity.status(400).body("400 -> El producto no existe\n");
       }

    }

}
