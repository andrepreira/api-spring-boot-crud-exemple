package br.com.andre.apispringbootcrudexemple.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.andre.apispringbootcrudexemple.entities.Cliente;
import br.com.andre.apispringbootcrudexemple.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PutMapping("/{id}")
    public Cliente atualizarPorId(@PathVariable Long id, @RequestBody Cliente cliente) {
        var clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Long id) {
        var clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        clienteRepository.delete(clienteOptional.get());
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        var clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return clienteOptional.get();
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
