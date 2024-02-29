package compassouol.sp.challenge.msuser.msuser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${spring.rabbitmq.queue}")
    private String filaMovimentacoes;

    @Bean
    public Queue queueMovimentacoes() {
        return new Queue(filaMovimentacoes, true);
    }
}
