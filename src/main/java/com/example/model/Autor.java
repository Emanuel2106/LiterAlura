package com.example.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private int anioNacimiento;

    @JsonAlias("death_year")
    private int anioFallecimiento;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(int anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    @Override
    public String toString() {
        return String.format("Autor: %s (Nacimiento: %d, Fallecimiento: %d)", nombre, anioNacimiento, anioFallecimiento);
    }
}
