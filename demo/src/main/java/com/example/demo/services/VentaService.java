package com.example.demo.services;



import com.example.demo.models.Venta;
import com.example.demo.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repositorio;

    public ResponseEntity<String> addSell(Venta venta){
        repositorio.save(venta);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");

    }
    public List<Venta> listSell(){
        return repositorio.findAll();
    }

    public ResponseEntity<String> updateSell(Long id, Venta venta){
        Venta updateSell = repositorio.findById(id).get();
        updateSell.setMontoTotal(venta.getMontoTotal());
        updateSell.setCliente(venta.getCliente());
        repositorio.save(updateSell);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }

    public ResponseEntity<String> deleteSell(Long id){
        Venta deleteSell = repositorio.findById(id).get();
        repositorio.delete(deleteSell);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }
}
