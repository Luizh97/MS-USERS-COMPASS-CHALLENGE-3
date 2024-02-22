package compassouol.sp.challenge.msuser.msuser.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cpf;
    private String cep;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private boolean status;
}
