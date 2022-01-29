package br.com.andre.apispringbootcrudexemple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andre.apispringbootcrudexemple.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
