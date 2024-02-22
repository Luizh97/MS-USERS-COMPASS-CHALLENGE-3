package compassouol.sp.challenge.msuser.msuser.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "CPF is required")
    private String cpf;
    @NotBlank(message = "CEP is required")
    private String cep;
    @NotNull
    private Date birthDate;
    private boolean status = false;

}
