package compassouol.sp.challenge.msuser.msuser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "msaddress", url = "http://localhost:8080")
public interface MsAddressClient {
    @GetMapping("{cep}")
    void buscarEndereco(@PathVariable String cep);

}
