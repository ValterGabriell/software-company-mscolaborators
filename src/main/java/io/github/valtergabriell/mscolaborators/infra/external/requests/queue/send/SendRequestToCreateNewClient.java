package io.github.valtergabriell.mscolaborators.infra.external.requests.queue.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.valtergabriell.mscolaborators.application.domain.dto.ClientAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendRequestToCreateNewClient {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public SendRequestToCreateNewClient(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }
    public void createNewClient(ClientAccount clientAccount) {
        log.info("RECEBENDO DADOS: {}", clientAccount);
        String payload = convertToJson(clientAccount);
        rabbitTemplate.convertAndSend(queue.getName(), payload);
    }

    public String convertToJson(ClientAccount clientAccount) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(clientAccount);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
