package com.maosencantadas.model.repository;

import com.maosencantadas.model.domain.artista.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findByCategoriaId(long categoriaId);
}