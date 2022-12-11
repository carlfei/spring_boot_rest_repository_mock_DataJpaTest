package com.example.repository;

import com.example.model.Libros;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BibliotecaTest{

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private Biblioteca biblioteca;



    @Test
    public void repositoryTest(){
        String nombre = "james";
        Libros libro = new Libros(4L,"la calle","james");
      //  libro.setAutor(libro.getAutor());
        testEntityManager.persist(libro);
        testEntityManager.flush();

        Libros encuentra = biblioteca.findByAutor(libro.getAutor());
        Assert.assertEquals(encuentra.getAutor(),nombre);


    }
}