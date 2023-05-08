package com.rosineimartins.projetoprodutos.repository;


import com.rosineimartins.projetoprodutos.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {
    List<Produto> produtoList = new ArrayList<>();
    private Integer ultimoId = 0;

    /**
     * Metodo que retorna uma lista de produtos
     * @return lista de produto
     */
    public List<Produto> obterTodos() {
        return produtoList;
    }

    /**
     * Metodo para cadastro de produtos
     *
     * @param produto
     * @return produto
     */
    public Produto cadastrarProduto(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtoList.add(produto);
        return produto;
    }

    /**
     * Metodo que retorna o produto encontrado pelo su Id
     *
     * @param id do produto que será localizado
     * @return retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtoList.stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    /**
     * Metodo que deleta o produto por id
     *
     * @param id do produto a ser deletado
     */
    public void deletarProduto(Integer id) {
        produtoList.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar na lista
     * @param produto que será aualizado
     * @return o produto após atualizar
     */
    public Produto atualizar(Produto produto) {
        //1o encontrar o produto na lista
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if (produtoEncontrado.isEmpty()) {
            throw new InputMismatchException("Produto nao encontrado");
        }
        //2o remover o poduto da lista
        deletarProduto(produto.getId());
        //3o adiciona produto
        produtoList.add(produto);
        return produto;
    }
}