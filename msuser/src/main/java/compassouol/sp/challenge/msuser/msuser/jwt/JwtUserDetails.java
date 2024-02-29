package compassouol.sp.challenge.msuser.msuser.jwt;

import compassouol.sp.challenge.msuser.msuser.entity.Usuario;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {


    Usuario user;
    public JwtUserDetails(Usuario user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }
    public Long getId() {
        return this.user.getId();
    }

    public String getRole() {
        return user.getRole().name();
    }

}
