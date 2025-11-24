package br.com.api.lojainformatica.kafka;

import br.com.api.lojainformatica.config.KafkaTopicConfig;
import br.com.api.lojainformatica.dto.CompraEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, CompraEmailDTO> kafkaTemplate;

    public void enviarEmailCompra(CompraEmailDTO emailDTO) {
        kafkaTemplate.send(KafkaTopicConfig.TOPIC_NAME, emailDTO);
        System.out.println("Mensagem enviada para o Kafka: Compra ID " + emailDTO.getIdCompra());
    }
}
