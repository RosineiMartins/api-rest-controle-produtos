package com.rosineimartins.projetoprodutos.repository;

import com.rosineimartins.projetoprodutos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
