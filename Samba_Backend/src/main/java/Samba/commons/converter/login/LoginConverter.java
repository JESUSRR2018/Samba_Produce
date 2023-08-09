package Samba.commons.converter.login;


import Samba.commons.constans.response.ILoginResponse;
import Samba.commons.domains.DTO.LoginDTO;
import Samba.commons.domains.entity.login.LoginEntity;
import Samba.commons.helpers.HelperMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@Log4j2
public class LoginConverter {
    public LoginDTO convertLoginEntityToLoginDTO(LoginEntity loginEntity) {
        LoginDTO loginDTO = new LoginDTO();
        try {
            loginDTO = HelperMapper.modelMapper().map(loginEntity, LoginDTO.class);
            byte[] cadenaDecodificadaByte = Base64.getDecoder().decode(loginEntity.getUsuarioPassword());
            String cadenaDecodificada = new String(cadenaDecodificadaByte);
            loginDTO.setUsuarioPassword(cadenaDecodificada);
        } catch (Exception e) {
            log.error(ILoginResponse.DOCUMENT_FAIL + e);
        }
        return loginDTO;
    }

    public LoginEntity convertLoginDTOToLoginEntity(LoginDTO loginDTO) {
        LoginEntity loginEntity = new LoginEntity();
        try {
            loginEntity = HelperMapper.modelMapper().map(loginDTO, LoginEntity.class);
            loginEntity.setUsuarioPassword(
                    Base64.getEncoder().encodeToString(loginDTO.getUsuarioPassword().getBytes()));
        } catch (Exception e) {
            log.error(ILoginResponse.DOCUMENT_FAIL + e);
        }
        return loginEntity;
    }
}