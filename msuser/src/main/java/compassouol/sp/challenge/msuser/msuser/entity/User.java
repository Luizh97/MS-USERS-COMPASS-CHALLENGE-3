package compassouol.sp.challenge.msuser.msuser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cpf;
    private String cep;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    public enum Role {
        ROLE_CLIENTE
    }
    private boolean status = false;

}
