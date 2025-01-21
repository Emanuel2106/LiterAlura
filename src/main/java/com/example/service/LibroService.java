package com.example.service;

import com.example.entity.Autor;
import com.example.entity.Libro;
import com.example.model.RespuestaAPI;
import com.example.repository.AutorRepository;
import com.example.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void guardarLibro(RespuestaAPI respuesta) {
        respuesta.getLibros().forEach(libroModel -> {
            Autor autor = new Autor(
                    libroModel.getAutores().get(0).getNombre(),
                    libroModel.getAutores().get(0).getAnioNacimiento(),
                    libroModel.getAutores().get(0).getAnioFallecimiento()
            );

            autorRepository.save(autor);

            Libro libro = new Libro(
                    libroModel.getTitulo(),
                    libroModel.getIdiomas().get(0),
                    libroModel.getNumeroDescargas(),
                    autor
            );

            libroRepository.save(libro);

            System.out.println("✅ Libro guardado en la base de datos: " + libro.getTitulo());
        });
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Nuevo método para listar autores
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    // Nuevo método para buscar autores vivos en un año específico
    public List<Autor> buscarAutoresVivos(int anio) {
        return autorRepository.findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(anio, anio);
    }

    public long contarLibrosPorIdioma(String idioma) {
        return libroRepository.countByIdioma(idioma);
    }

}
