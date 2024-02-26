package compassouol.sp.challenge.msuser.msuser.jwt;

import compassouol.sp.challenge.msuser.msuser.entity.User;
import compassouol.sp.challenge.msuser.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAutheticated(String username){
        String role = "ROLE_USER";
        return JwtUtils.createToken(username, role.substring("ROLE_".length()));
    }
}
