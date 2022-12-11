package com.example.controller;

import com.example.model.Libros;
import com.example.repository.Biblioteca;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(Controlador.class)
public class ControladorTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private Biblioteca biblioteca;




    @Test
  public  void autores() throws Exception
    {

        Libros libros = new Libros();
        libros.setAutor("bob");
        given(biblioteca.findByAutor("bob")).willReturn(libros);
        mvc.perform(get("/libros")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.autor").value("bob"));
    }
}