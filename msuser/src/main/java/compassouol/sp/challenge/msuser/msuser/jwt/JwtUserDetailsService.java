package compassouol.sp.challenge.msuser.msuser.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import compassouol.sp.challenge.msuser.msuser.entity.DadosUserSistema;
import compassouol.sp.challenge.msuser.msuser.entity.Usuario;
import compassouol.sp.challenge.msuser.msuser.infra.mqueue.MovimentacaoUserPublisher;
import compassouol.sp.challenge.msuser.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final MovimentacaoUserPublisher movimentacaoUserPublisher;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userService.findByEmail(username);
        return new JwtUserDetails(user);
    }


    public JwtToken getTokenAutheticated(String username) throws JsonProcessingException {
        Usuario.Role role = userService.buscarRolerPorEmail(username);
        movimentacaoUserPublisher.movimentacaoUsuario(new DadosUserSistema(username, "LOGIN", new Date()));
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
