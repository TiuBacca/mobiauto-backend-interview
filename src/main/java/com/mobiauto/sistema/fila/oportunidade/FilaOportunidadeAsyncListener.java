package com.mobiauto.sistema.fila.oportunidade;

import com.mobiauto.sistema.domain.Oportunidade;
import com.mobiauto.sistema.fila.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FilaOportunidadeAsyncListener {

    // Nome da fila a ser consumida
    private static final String QUEUE_NAME = RabbitMQConfig.QUEUE_NAME;

    @RabbitListener(queues = QUEUE_NAME)
    public void processarMensagem(@Payload Oportunidade objeto) {
        System.out.println("Recebido objeto da fila: " + objeto);
        realizarProcessamentoAssincrono(objeto);
    }

    private void realizarProcessamentoAssincrono(Oportunidade objeto) {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Processamento assíncrono concluído para: " + objeto);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Erro durante o processamento assíncrono para: " + objeto);
            }
        }).start();
    }
}
