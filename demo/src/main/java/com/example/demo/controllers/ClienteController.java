package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;



    @GetMapping("obtener")
    public List<Cliente> obtenerClient(){
        return this.clienteService.listClient();
    }

    @PostMapping("alta")
    public ResponseEntity<String> addClient(@RequestBody Cliente cliente){
        return (this.clienteService.addClient(cliente));
    }

    @PutMapping("modificar/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody Cliente cliente){
        return this.clienteService.updateClient(id,cliente);
    }

    @DeleteMapping("baja/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id){
        return this.clienteService.deleteClient(id);
    }

}
