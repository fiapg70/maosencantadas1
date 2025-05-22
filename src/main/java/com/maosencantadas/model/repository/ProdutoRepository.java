package com.maosencantadas.model.repository;

import com.maosencantadas.model.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByArtistaId(Long artistaId);

    List<Produto> findByCategoriaId(long categoriaId);

}