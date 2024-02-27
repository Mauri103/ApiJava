package com.example.demo.controllers;


import com.example.demo.dto.DetalleFacturaDTO;
import com.example.demo.models.Venta;
import com.example.demo.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("fecha")
    public LocalDateTime obtenerFecha(){
        return this.ventaService.getCurrentDateTime();
    }


    @GetMapping("obtener")
    public List<Venta> obtenerVenta(){
        return this.ventaService.listSell();
    }

    @PostMapping("alta")
    public ResponseEntity<Venta> addSale(@RequestBody DetalleFacturaDTO detalleFactura) {
        Venta createdSale = ventaService.addSale(detalleFactura);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }


    @PutMapping("modificar/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody Venta venta){
        return this.ventaService.updateSell(id,venta);
    }

    @DeleteMapping("baja/{id}")
    public ResponseEntity<String> deleteSell(@PathVariable Long id){
        return this.ventaService.deleteSell(id);
    }



}
