package com.rosineimartins.projetoprodutos.view.controller;

import com.rosineimartins.projetoprodutos.model.Produto;
import com.rosineimartins.projetoprodutos.service.ProdutoService;
import com.rosineimartins.projetoprodutos.shared.ProdutoDto;
import com.rosineimartins.projetoprodutos.view.model.ProdutoRequest;
import com.rosineimartins.projetoprodutos.view.model.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {
        List<ProdutoDto> produtoDtoList = produtoService.obterTodos();
        ModelMapper mapper = new ModelMapper();
        List<ProdutoResponse> responses = produtoDtoList.stream()
                .map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDto produtoDto = mapper.map(produtoRequest, ProdutoDto.class);
        produtoDto = produtoService.cadastrarProduto(produtoDto);
        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Long id) {
        Optional<ProdutoDto> dto = produtoService.obterPorId(id);
        ProdutoResponse produtoResponse = new ModelMapper().map(dto.get(), ProdutoResponse.class);
        return new ResponseEntity<>(Optional.of(produtoResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoRequest, @PathVariable Long id) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDto produtoDto = mapper.map(produtoRequest, ProdutoDto.class);
        produtoDto = produtoService.atualizar(id, produtoDto);
        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.OK);
    }
}