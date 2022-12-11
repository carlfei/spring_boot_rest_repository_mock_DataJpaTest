package com.example.controller;

import com.example.model.Libros;
import com.example.repository.Biblioteca;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/libros")
@Api(tags = "libros")
public class Controlador {
    @Autowired
    Biblioteca biblioteca;

    Libros libros1 = new Libros();

    List<Libros> libros = new ArrayList<>();

    @GetMapping("/listar/{id}")
    @ApiOperation(value = "${Controlador.listar}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "hops "), //
            @ApiResponse(code = 422, message = "Invalid id supplied")})
    public List<Libros> listar(@ApiParam("Id") @PathVariable(value = "id") Long id){

        return (biblioteca.findById(id)).stream().collect(Collectors.toList());

    }

    @GetMapping("/autor/{id}")
    @ApiOperation(value = "${Controlador.autor}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "hops "), //
            @ApiResponse(code = 422, message = "Invalid id supplied")})
    public String  autor(@ApiParam("Id") @PathVariable(value = "id") Long id){

         (biblioteca.findById(id)).stream().forEach(a->{
            libros1.setAutor(a.getAutor());



        });
       return libros1.getAutor();
    }


    @GetMapping("/listAdd/{id}/{titulo}/{autor}")
    @ApiOperation(value = "${Controlador.listAdd}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "hops "), //
            @ApiResponse(code = 422, message = "Invalid id supplied")})
    public List<Libros> listAdd(@ApiParam("Id") @PathVariable(value = "id") Long id,
                                @ApiParam("Titulo") @PathVariable(value = "titulo") String titulo,
                                @ApiParam("Autor") @PathVariable(value = "autor") String autor){
                             libros.add(new Libros(id,titulo,autor));
                             biblioteca.saveAll(libros);


        return libros
                .stream()
                .collect(Collectors.toList());

    }

    @GetMapping("/autores/{autor}")
    @ApiOperation(value = "${Controlador.autores}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "hops "), //
            @ApiResponse(code = 422, message = "Invalid id supplied")})
    public Libros autores(@ApiParam("Autor") @PathVariable(value = "autor") String autor){

        return biblioteca.findByAutor(autor);

    }

}
