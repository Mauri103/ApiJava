package com.example.demo.controllers;


import com.example.demo.dto.DetalleFacturaDTO;
import com.example.demo.models.Venta;
import com.example.demo.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("obtener")
    public List<Venta> obtenerVenta(){
        return this.ventaService.listSell();
    }

    @PostMapping("alta")
    public Venta addSale(@RequestBody DetalleFacturaDTO detalleFactura) {
        return this.ventaService.addSale(detalleFactura);
    }

    @DeleteMapping("baja/{id}")
    public ResponseEntity<String> deleteSell(@PathVariable Long id){
        return this.ventaService.deleteSell(id);
    }



}
