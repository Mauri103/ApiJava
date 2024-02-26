package com.example.demo.controllers;


import com.example.demo.models.Producto;
import com.example.demo.models.Venta;
import com.example.demo.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/venta")
public class VentaController {

    @Autowired
    private VentaRepository repo;
    private Venta venta;


    @GetMapping("obtener")
    public List<Venta> getVentas(){
        return repo.findAll();
    }

    @PostMapping("alta")
    public String post(@RequestBody Venta venta){
        repo.save(venta);
        return "Venta guardada";
    }


    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Venta venta){
        Venta updateVenta = repo.findById(id).get();
        updateVenta.setMontoTotal(venta.getMontoTotal());
        updateVenta.setCliente(venta.getCliente());
        repo.save(updateVenta);
        return "Venta modificada";
    }

    @DeleteMapping("baja/{id}")
    public String delete(@PathVariable Long id){
        Venta ventaDelete = repo.findById(id).get();
        repo.delete(ventaDelete);
        return "Venta eliminada";
    }

}
