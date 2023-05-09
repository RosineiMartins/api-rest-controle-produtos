package com.rosineimartins.projetoprodutos.controller;

import com.rosineimartins.projetoprodutos.model.Produto;
import com.rosineimartins.projetoprodutos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> obterTodos() {
        return produtoService.obterTodos();
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoService.cadastrarProduto(produto);
    }

    @GetMapping("/{id}")
    public Optional<Produto> obterPorId(@PathVariable Integer id) {
        return produtoService.obterPorId(id);
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return "Produto com id: " + id + " deletado com sucesso";
    }

    @PutMapping("/{id}")
    public Produto atualizar(@RequestBody Produto produto, @PathVariable Integer id) {
        return produtoService.atualizar(id,produto);
    }
}