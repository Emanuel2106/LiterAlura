package com.example.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaAPI {

    @JsonAlias("count")
    private int totalResultados;

    @JsonAlias("results")
    private List<Libro> libros;

    // Constructor vacío para Jackson
    public RespuestaAPI() {}

    // Getters y setters
    public int getTotalResultados() {
        return totalResultados;
    }

    public void setTotalResultados(int totalResultados) {
        this.totalResultados = totalResultados;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
