package br.com.api.lojainformatica.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    public static final String TOPIC_NAME = "compras";

    @Bean
    public NewTopic compraTopic() {
        return new NewTopic(TOPIC_NAME, 1, (short) 1);
    }
}
