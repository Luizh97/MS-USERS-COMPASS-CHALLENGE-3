package compassouol.sp.challenge.msuser.msuser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(name = "msaddress", url = "http://localhost:8081")
public interface MsAddressClient {
    @PostMapping("{cep}/usuario")
    void buscarEnderecoUsuario(@PathVariable String cep, @RequestBody Long id);


}
