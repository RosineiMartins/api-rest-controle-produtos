package com.rosineimartins.projetoprodutos.service;

import com.rosineimartins.projetoprodutos.model.Produto;
import com.rosineimartins.projetoprodutos.model.exception.ResourceNotFoundException;
import com.rosineimartins.projetoprodutos.repository.ProdutoRepository;
import com.rosineimartins.projetoprodutos.shared.ProdutoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo que retorna uma lista de produtos
     *
     * @return lista de produtoDto
     */
    public List<ProdutoDto> obterTodos() {
        //retorna uma lista de produto model
        List<Produto> produtoList = produtoRepository.findAll();

        return produtoList.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Metodo para cadastro de produtos
     *
     * @param produtoDto
     * @return produto
     */
    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto) {
        //1o remover o id para fazer o cadastro e para garantir que é uma inserção e não atualização
        produtoDto.setId(null);
        //criar u objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        //converter um produtoDto em produto
        Produto produto = mapper.map(produtoDto, Produto.class);
        //salvar o produto no banco
        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());
        //retornar o produtoDto atualiazado
        return produtoDto;
    }

    /**
     * Metodo que retorna o produto encontrado pelo su Id
     *
     * @param id do produto que será localizado
     * @return retorna um produto caso seja encontrado
     */
    public Optional<ProdutoDto> obterPorId(Long id) {
        //Obtendo Optional de produto por id
        Optional<Produto> produto = produtoRepository.findById(id);
        //se não for encontrar lança exception
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto informado com id: " + id + "não foi encontrado");
        }
        //convertendo o meu Optional de produto em produttoDto
        ProdutoDto produtoDto = new ModelMapper().map(produto.get(), ProdutoDto.class);
        //retornando um Optional de produtoDto
        return Optional.of(produtoDto);
    }

    /**
     * Metodo que deleta o produto por id
     *
     * @param id do produto a ser deletado
     */
    public void deletarProduto(Long id) {
        //1o verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);
        //2o se não existir lnça uma exception
        if (obterPorId(id).isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id: " + id + "produto não encontrado");
        }
        //3o deleta o produto pelo id
        produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar na lista
     *
     * @param produtoDto que será aualizado
     * @return o produto após atualizar
     */
    public ProdutoDto atualizar(Long id, ProdutoDto produtoDto) {
        //1o passar o id para o produtoDto
        produtoDto.setId(id);
        //2o criar o objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        //3o converter o Dto em um Produto
        Produto produto = mapper.map(produtoDto, Produto.class);
        //4o Atualizar o produto no banco
        produtoRepository.save(produto);
        //retornar o produtoDto atualizado
        return produtoDto;
    }
}