package compassouol.sp.challenge.msuser.msuser.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import compassouol.sp.challenge.msuser.msuser.entity.DadosUserSistema;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovimentacaoUserPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueMovimentacoes;
    public void movimentacaoUsuario(DadosUserSistema dadosUserSistema) throws JsonProcessingException {
        var json = converteIntoJson(dadosUserSistema);
        rabbitTemplate.convertAndSend(queueMovimentacoes.getName(), json);
    }
    private String converteIntoJson(DadosUserSistema dadosUserSistema) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dadosUserSistema);
        return json;
    }
}
