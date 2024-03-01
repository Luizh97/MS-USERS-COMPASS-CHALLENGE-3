package compassouol.sp.challenge.msuser.msuser.web.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {
    @NotBlank(message = "First name is required")
    @Size(min = 3, message = "First name must have at least 3 characters")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(min = 3, message = "Last name must have at least 3 characters")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;
    @NotBlank(message = "CPF is required")
    @CPF(message = "Invalid CPF")
    private String cpf;
    @NotBlank(message = "CEP is required")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
    private String cep;
    @NotNull
    private Date birthDate;
    private boolean status = false;

}
