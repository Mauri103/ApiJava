package com.example.demo.services;

import com.example.demo.models.Cliente;
import com.example.demo.models.Producto;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repositorio;

    public ResponseEntity<String> addClient(Cliente cliente){
        repositorio.save(cliente);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");

    }
    public List<Cliente> listClient(){
        return repositorio.findAll();
    }

    public ResponseEntity<String> updateClient(Long id, Cliente cliente){
        Cliente updateCient = repositorio.findById(id).get();
        updateCient.setNombre(cliente.getNombre());
        updateCient.setApellido(cliente.getApellido());
        updateCient.setEmail(cliente.getEmail());
        repositorio.save(updateCient);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }

    public ResponseEntity<String> deleteClient(Long id){
        Cliente deleteClient = repositorio.findById(id).get();
        repositorio.delete(deleteClient);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }

}

