package compassouol.sp.challenge.msuser.msuser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DadosUserSistema {
    private String email;
    private String event;
    private Date data;
}
