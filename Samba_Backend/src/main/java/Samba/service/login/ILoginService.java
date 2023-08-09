package Samba.service.login;

import Samba.commons.domains.DTO.LoginDTO;
import Samba.commons.domains.DTO.responseDTO.GenericResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ILoginService {
    ResponseEntity<GenericResponseDTO> loginService(LoginDTO loginDTO);
    ResponseEntity<GenericResponseDTO> saveLogin(LoginDTO loginDTO);
}