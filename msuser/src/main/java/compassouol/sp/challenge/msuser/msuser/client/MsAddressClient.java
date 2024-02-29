package compassouol.sp.challenge.msuser.msuser.client;

import compassouol.sp.challenge.msuser.msuser.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "msaddress", url = "http://localhost:8080")
public interface MsAddressClient {
    @PostMapping("{cep}/usuario")
    void buscarEnderecoUsuario(@PathVariable String cep, @RequestBody Long id);

//    @PostMapping ("{cep}/usuario")
//    void buscarUsuario(@RequestBody Usuario user);

}
