package com.mobiauto.sistema.fila.oportunidade;

import com.mobiauto.sistema.domain.Oportunidade;
import com.mobiauto.sistema.fila.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilaOportinudadeService {

    private final RabbitTemplate rabbitTemplate;
    private static final String QUEUE_NAME = RabbitMQConfig.QUEUE_NAME;

    public void enviarParaFila(List<Oportunidade> listaObjetos) {
        listaObjetos.forEach(objeto -> {
            rabbitTemplate.convertAndSend(QUEUE_NAME, objeto);
            System.out.println("Objeto enviado para a fila: " + objeto);
        });
    }
}
