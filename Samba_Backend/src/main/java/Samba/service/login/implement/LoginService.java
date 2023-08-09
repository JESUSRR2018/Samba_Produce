package Samba.service.login.implement;


import Samba.commons.constans.response.ILoginResponse;
import Samba.commons.converter.login.LoginConverter;
import Samba.commons.domains.DTO.LoginDTO;
import Samba.commons.domains.DTO.responseDTO.GenericResponseDTO;
import Samba.commons.domains.entity.login.LoginEntity;
import Samba.repository.login.ILoginRepository;
import Samba.service.login.ILoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class LoginService implements ILoginService {

    @Autowired
    private ILoginRepository iLoginRepository;
    @Autowired
    private LoginConverter loginConverter;


    @Override
    public ResponseEntity<GenericResponseDTO> loginService(LoginDTO loginDTO) {
        try {
            List<LoginEntity> usuarios = iLoginRepository.findByUsuarioEmail(loginDTO.getUsuarioEmail());
            if (!usuarios.isEmpty()) {
                for (LoginEntity usuario : usuarios) {
                    LoginDTO usuarioDecode = loginConverter.convertLoginEntityToLoginDTO(usuario);
                    if (usuarioDecode.getUsuarioPassword().equals(loginDTO.getUsuarioPassword())) {
                        return ResponseEntity.ok(GenericResponseDTO.builder()
                                .message("Operacion exitosa")
                                .objectResponse("Usuario autenticado correctamente")
                                .statusCode(HttpStatus.OK.value())
                                .build());
                    }
                }
                return ResponseEntity.badRequest().body(GenericResponseDTO.builder()
                        .message("Contraseña incorrecta")
                        .objectResponse(null)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build());
            } else {
                return ResponseEntity.badRequest().body(GenericResponseDTO.builder()
                        .message("Error usuario no encontrado")
                        .objectResponse(null)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build());
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error interno", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericResponseDTO.builder()
                            .message("Error interno del servidor")
                            .objectResponse(null)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .build());
        }
    }

    @Override
    public ResponseEntity<GenericResponseDTO> saveLogin(LoginDTO loginDTO) {
        try {
            Optional<LoginEntity> existeLogin;
            existeLogin = iLoginRepository.findById(loginDTO.getIdUsuario());
            if(!existeLogin.isPresent()){
                LoginEntity loginEntity = loginConverter.convertLoginDTOToLoginEntity(loginDTO);
                iLoginRepository.save(loginEntity);
                return new ResponseEntity<>(GenericResponseDTO.builder()
                        .message(ILoginResponse.CREATE_SUCCESS)
                        .objectResponse(ILoginResponse.CREATE_LOGIN_SUCCESS)
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(GenericResponseDTO.builder()
                        .message(ILoginResponse.CREATE_FAIL)
                        .objectResponse(ILoginResponse.LOGIN_SUCCESS)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error(ILoginResponse.INTERNAL_SERVER + e);
            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .message(ILoginResponse.CREATE_FAIL)
                    .objectResponse(null)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}