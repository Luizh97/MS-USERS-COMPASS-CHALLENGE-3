package compassouol.sp.challenge.msuser.msuser.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {
    @Size(min = 6, message = "Password must have at least 6 characters")
    @NotBlank(message = "Password is required")
    private String password;
}
