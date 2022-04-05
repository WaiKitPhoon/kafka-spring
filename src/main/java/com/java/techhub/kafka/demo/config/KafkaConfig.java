package com.java.techhub.kafka.demo.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka admin configuration
 *
 * @Bean - NewTopic - creates a new topic if doesnt exist. Add multiple @Bean if you would like to create new topic
 */
@Configuration
public class KafkaConfig {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    // Sample example to create a new topic -- it runs on startup
    @Bean
    public NewTopic springLocalKafka() {
        return new NewTopic("spring-local-kafka", 1, (short) 1);
    }

    // Eg new topic
    @Bean
    public NewTopic newMessage() {
        return new NewTopic("new-message", 1, (short) 1);
    }

}
