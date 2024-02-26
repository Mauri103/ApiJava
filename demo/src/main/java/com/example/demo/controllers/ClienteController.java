package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.SimpleTimeZone;

@RestController
@RequestMapping(path = "api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;
    private Cliente cliente;



    @GetMapping("obtener")
    public List<Cliente> getClientes(){
        return repo.findAll();
    }

    @PostMapping("alta")
    public String post(@RequestBody Cliente cliente){
        repo.save(cliente);
        return "Guardado";
    }

    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente updateCliente = repo.findById(id).get();
        updateCliente.setNombre(cliente.getNombre());
        updateCliente.setApellido(cliente.getApellido());
        updateCliente.setEmail(cliente.getEmail());
        repo.save(updateCliente);
        return "Cliente modificado";
    }

    @DeleteMapping("/baja/{id}")
    public String delete(@PathVariable Long id){
        Cliente clienteDelete = repo.findById(id).get();
        repo.delete(clienteDelete);
        return "Cliente eliminado";
    }

}
