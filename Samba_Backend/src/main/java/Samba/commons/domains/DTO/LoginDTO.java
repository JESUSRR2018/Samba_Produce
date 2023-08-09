package Samba.commons.domains.DTO;


import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LoginDTO implements Serializable {

    private Integer idUsuario;
    private String usuarioEmail;
    private String usuarioPassword;
}
