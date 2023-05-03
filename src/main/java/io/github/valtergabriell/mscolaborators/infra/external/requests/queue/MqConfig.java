package io.github.valtergabriell.mscolaborators.infra.external.requests.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    @Bean
    public Queue queue(){
        return new Queue("create-client-queue", true);
    }

}
