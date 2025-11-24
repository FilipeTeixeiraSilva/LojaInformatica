package br.com.api.lojainformatica.service;

import br.com.api.lojainformatica.dto.CompraDTO;
import br.com.api.lojainformatica.dto.CompraEmailDTO;
import br.com.api.lojainformatica.kafka.KafkaProducerService;
import br.com.api.lojainformatica.model.CompraEntity;
import br.com.api.lojainformatica.repository.ClienteRepository;
import br.com.api.lojainformatica.repository.CompraRepository;
import br.com.api.lojainformatica.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    public CompraDTO salvarCompra(CompraDTO compraDTO) {
        CompraEntity compraEntity = new CompraEntity();
        compraEntity.setCliente(clienteRepository.findById(compraDTO.getClienteId()).orElse(null));
        compraEntity.setProduto(produtoRepository.findById(compraDTO.getProdutoId()).orElse(null));
        compraEntity.setQuantidade(compraDTO.getQuantidade());
        compraEntity.setPrecoTotal(compraDTO.getPrecoTotal());

        CompraEntity compraSalva = compraRepository.save(compraEntity);

        kafkaProducerService.enviarEmailCompra(criarEmailDTO(compraSalva));

        CompraDTO compraDTOSalva = new CompraDTO();
        compraDTOSalva.setClienteId(compraSalva.getCliente().getId());
        compraDTOSalva.setProdutoId(compraSalva.getProduto().getId());
        compraDTOSalva.setQuantidade(compraSalva.getQuantidade());
        compraDTOSalva.setPrecoTotal(compraSalva.getPrecoTotal());

        return  compraDTOSalva;
    }

    public List<CompraDTO> listarCompras() {
        List<CompraEntity> compras = compraRepository.findAll();
        List<CompraDTO> comprasDTO = new ArrayList<>();
        for (CompraEntity compra : compras) {
            CompraDTO compraDTO = new CompraDTO();
            compraDTO.setClienteId(compra.getCliente().getId());
            compraDTO.setProdutoId(compra.getProduto().getId());
            compraDTO.setQuantidade(compra.getQuantidade());
            compraDTO.setPrecoTotal(compra.getPrecoTotal());
            comprasDTO.add(compraDTO);
        }
        return comprasDTO;
    }
    public CompraDTO buscarCompraPorId(Long id) {
        CompraEntity compraEntity = compraRepository.findById(id).orElse(null);
        if (compraEntity == null) {
            return null;
        }
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setClienteId(compraEntity.getCliente().getId());
        compraDTO.setProdutoId(compraEntity.getProduto().getId());
        compraDTO.setQuantidade(compraEntity.getQuantidade());
        compraDTO.setPrecoTotal(compraEntity.getPrecoTotal());
        return compraDTO;
    }
    public void deletarCompra(Long id) {
        compraRepository.deleteById(id);
    }
    public CompraDTO atualizarCompra(Long id, CompraDTO compraDTO) {
        CompraEntity compraEntity = compraRepository.findById(id).orElse(null);
        if (compraEntity == null) {
            return null;
        }
        compraEntity.setCliente(clienteRepository.findById(compraDTO.getClienteId()).orElse(null));
        compraEntity.setProduto(produtoRepository.findById(compraDTO.getProdutoId()).orElse(null));
        compraEntity.setQuantidade(compraDTO.getQuantidade());
        compraEntity.setPrecoTotal(compraDTO.getPrecoTotal());

        CompraEntity compraSalva = compraRepository.save(compraEntity);

        CompraDTO compraDTOSalva = new CompraDTO();
        compraDTOSalva.setClienteId(compraSalva.getCliente().getId());
        compraDTOSalva.setProdutoId(compraSalva.getProduto().getId());
        compraDTOSalva.setQuantidade(compraSalva.getQuantidade());
        compraDTOSalva.setPrecoTotal(compraSalva.getPrecoTotal());

        return  compraDTOSalva;
    }
    public CompraEmailDTO criarEmailDTO(CompraEntity compraEntity) {
        CompraEmailDTO compraEmailDTO = new CompraEmailDTO();
        compraEmailDTO.setIdCompra(compraEntity.getId());
        compraEmailDTO.setNomeCliente(compraEntity.getCliente().getNome());
        compraEmailDTO.setEmail(compraEntity.getCliente().getEmail());
        compraEmailDTO.setNomeProduto(compraEntity.getProduto().getNome());
        compraEmailDTO.setQuantidade(compraEntity.getQuantidade());
        compraEmailDTO.setPrecoTotal(compraEntity.getPrecoTotal());
        return compraEmailDTO;
    }
}
