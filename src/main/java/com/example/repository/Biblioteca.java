package com.example.repository;

import com.example.model.Libros;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Biblioteca extends CrudRepository<Libros, Long > {
    public Libros findByAutor(String autor);
}
