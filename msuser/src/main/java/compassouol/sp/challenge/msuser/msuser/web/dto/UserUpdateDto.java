package compassouol.sp.challenge.msuser.msuser.web.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateDto {
    @Size(min = 3, message = "First name must have at least 3 characters")
    private String firstName;
    @Size(min = 3, message = "Last name must have at least 3 characters")
    private String lastName;
    private String email;
    private String cpf;
    private String cep;
    private Date birthDate;
    private boolean status;
}
