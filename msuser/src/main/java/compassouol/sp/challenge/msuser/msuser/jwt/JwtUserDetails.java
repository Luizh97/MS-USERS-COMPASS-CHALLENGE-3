package compassouol.sp.challenge.msuser.msuser.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.Collection;
import java.util.Collections;

public class JwtUserDetails extends User {


    compassouol.sp.challenge.msuser.msuser.entity.User user;
    public JwtUserDetails(compassouol.sp.challenge.msuser.msuser.entity.User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }
    public Long getId() {
        return this.user.getId();
    }

    public String getRole() {
        return "ROLE_USER";
    }

}
