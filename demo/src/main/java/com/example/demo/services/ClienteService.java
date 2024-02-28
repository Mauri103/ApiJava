package com.example.demo.services;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repositorio;

    public ResponseEntity<String> addClient(Cliente cliente){
        if (cliente.getNombre() == null || cliente.getEmail() == null){
            return ResponseEntity.status(400).body("400 -> Rellene todos los campos!\n");
        }else {
            repositorio.save(cliente);
            return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
        }

    }
    public List<Cliente> listClient(){
        return repositorio.findAll();
    }

    public ResponseEntity<String> updateClient(Long id, Cliente cliente){
        try{


        Optional<Cliente> update = repositorio.findById(id);
        Cliente updateCient = repositorio.findById(id).get();
            if (update.isPresent()) {
                updateCient.setNombre(cliente.getNombre());
                updateCient.setEmail(cliente.getEmail());
                repositorio.save(updateCient);
                return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
            } else {
                return ResponseEntity.status(400).body("400 -> Cliente no existe!\n");
            }
        }catch (Exception e) {
            return ResponseEntity.status(400).body("400 -> Hubo un error o cliente no existe!\n");
        }

    }

    public ResponseEntity<String> deleteClient(Long id){
        try{
            Optional<Cliente> delete = repositorio.findById(id);
            Cliente deleteClient = repositorio.findById(id).get();
            if (delete.isPresent()) {
                repositorio.delete(deleteClient);
                return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
            } else {
                return ResponseEntity.status(400).body("400 -> Cliente no existe!\n");
            }
        }catch (Exception e) {
            return ResponseEntity.status(400).body("400 -> Cliente no existe!\n");
        }

    }

    public Optional<Cliente> getClientById(Long id) {
        if (repositorio.findById(id).isPresent()) {
            return repositorio.findById(id);
        } else {
            return null;
        }
    }


}

