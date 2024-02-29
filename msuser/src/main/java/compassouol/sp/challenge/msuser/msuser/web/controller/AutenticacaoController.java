package compassouol.sp.challenge.msuser.msuser.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import compassouol.sp.challenge.msuser.msuser.jwt.JwtToken;
import compassouol.sp.challenge.msuser.msuser.jwt.JwtUserDetailsService;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserLoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class AutenticacaoController {

    private final JwtUserDetailsService detailsService;

    private final AuthenticationManager authenticationManager;


    @PostMapping("/auth")
    public ResponseEntity<?>autenticar(@RequestBody @Valid UserLoginDto userLoginDto, HttpServletRequest request){
        log.info("Autenticando usuario: {}", userLoginDto.getEmail());
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailsService.getTokenAutheticated(userLoginDto.getEmail());
            return ResponseEntity.ok(token);
        }catch(AuthenticationException e){
            log.warn("Falha na autenticação do usuario: {}", userLoginDto.getEmail());

        }catch (JsonProcessingException e){
            log.error("Erro ao enviar mensagem: {}");
        }
        return ResponseEntity.badRequest()
                .body("Falha na autenticação do usuario: " + userLoginDto.getEmail());

    }
}
