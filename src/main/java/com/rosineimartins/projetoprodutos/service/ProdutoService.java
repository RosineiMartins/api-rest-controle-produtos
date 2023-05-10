package com.rosineimartins.projetoprodutos.service;

import com.rosineimartins.projetoprodutos.model.Produto;
import com.rosineimartins.projetoprodutos.model.exception.ResourceNotFoundException;
import com.rosineimartins.projetoprodutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo que retorna uma lista de produtos
     * @return lista de produto
     */
    public List<Produto> obterTodos() {
       return produtoRepository.obterTodos();
    }

    /**
     * Metodo para cadastro de produtos
     * @param produto
     * @return produto
     */
    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.cadastrarProduto(produto);
    }

    /**
     * Metodo que retorna o produto encontrado pelo su Id
     * @param id do produto que será localizado
     * @return retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtoRepository.obterPorId(id);
    }

    /**
     * Metodo que deleta o produto por id
     * @param id do produto a ser deletado
     */
    public void deletarProduto(Integer id) {
        produtoRepository.deletarProduto(id);
    }

    /**
     * Metodo para atualizar na lista
     * @param produto que será aualizado
     * @return o produto após atualizar
     */
    public Produto atualizar(Integer id, Produto produto) {
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }
}
