package Samba.commons.domains.DTO.responseDTO;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GenericResponseDTO implements Serializable {

    public String message;
    public Object objectResponse;
    public int statusCode;
}