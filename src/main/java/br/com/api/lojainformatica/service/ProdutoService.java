package br.com.api.lojainformatica.service;

import br.com.api.lojainformatica.dto.ProdutoDTO;
import br.com.api.lojainformatica.model.ProdutoEntity;
import br.com.api.lojainformatica.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNome(produtoDTO.getNome());
        produtoEntity.setPreco(produtoDTO.getPreco());

        ProdutoEntity produtoSalvo = produtoRepository.save(produtoEntity);

        ProdutoDTO produtoDTOSalvo = new ProdutoDTO();
        produtoDTOSalvo.setNome(produtoSalvo.getNome());
        produtoDTOSalvo.setPreco(produtoSalvo.getPreco());

        return produtoDTOSalvo;

    }

    public List<ProdutoDTO> listarProdutos() {
        List<ProdutoEntity> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        for (ProdutoEntity produto : produtos) {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setNome(produto.getNome());
            produtoDTO.setPreco(produto.getPreco());
            produtosDTO.add(produtoDTO);
        }
        return produtosDTO;

    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id).orElse(null);
        if (produtoEntity == null) {
            return null;
        }
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome(produtoEntity.getNome());
        produtoDTO.setPreco(produtoEntity.getPreco());
        return produtoDTO;

    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);

    }

    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id).orElse(null);
        if (produtoEntity == null) {
            return null;
        }
        produtoEntity.setNome(produtoDTO.getNome());
        produtoEntity.setPreco(produtoDTO.getPreco());

        ProdutoEntity produtoSalvo = produtoRepository.save(produtoEntity);

        ProdutoDTO produtoDTOSalvo = new ProdutoDTO();
        produtoDTOSalvo.setNome(produtoSalvo.getNome());
        produtoDTOSalvo.setPreco(produtoSalvo.getPreco());

        return produtoDTOSalvo;

    }
}
