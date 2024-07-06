package com.mobiauto.sistema.fila;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "fila-oportunidades";

    @Bean
    public Queue minhaFila() {
        return new Queue(QUEUE_NAME, true);
    }

}
