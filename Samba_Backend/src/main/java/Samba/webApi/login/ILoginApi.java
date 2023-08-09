package Samba.webApi.login;

import Samba.commons.constans.endpoints.ILoginEndPoint;
import Samba.commons.domains.DTO.LoginDTO;
import Samba.commons.domains.DTO.responseDTO.GenericResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ILoginApi {
    @PostMapping(ILoginEndPoint.LOGIN_SERVICE)
    ResponseEntity<GenericResponseDTO> loginService(@RequestBody LoginDTO loginDTO);
    @PostMapping(ILoginEndPoint.CREATE_ACOUNT)
    ResponseEntity<GenericResponseDTO> saveLogin(@RequestBody LoginDTO loginDTO);
}