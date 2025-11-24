package br.com.api.lojainformatica.kafka;

import br.com.api.lojainformatica.config.KafkaTopicConfig;
import br.com.api.lojainformatica.dto.CompraEmailDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = KafkaTopicConfig.TOPIC_NAME, groupId = "loja-informatica-group")
    public void consumirEmailCompra(CompraEmailDTO emailDTO) {
        System.out.println("=== SIMULAÇÃO DE ENVIO DE EMAIL ===");
        System.out.println("Destinatário: " + emailDTO.getEmail());
        System.out.println("Compra ID: " + emailDTO.getIdCompra());
        System.out.println("Cliente: " + emailDTO.getNomeCliente());
        System.out.println("Produto: " + emailDTO.getNomeProduto());
        System.out.println("Quantidade: " + emailDTO.getQuantidade());
        System.out.println("Total: R$ " + emailDTO.getPrecoTotal());
        System.out.println("====================================");
    }
}
