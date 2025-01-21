package com.example.repository;

import com.example.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {


    List<Autor> findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(int nacimiento, int fallecimiento);
}
