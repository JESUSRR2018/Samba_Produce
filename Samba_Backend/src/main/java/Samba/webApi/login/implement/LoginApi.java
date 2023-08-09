package Samba.webApi.login.implement;


import Samba.commons.constans.endpoints.ILoginEndPoint;
import Samba.commons.constans.response.ILoginResponse;
import Samba.commons.domains.DTO.LoginDTO;
import Samba.commons.domains.DTO.responseDTO.GenericResponseDTO;
import Samba.service.login.ILoginService;
import Samba.webApi.login.ILoginApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ILoginEndPoint.BASE_URL_LOGIN)
@Tag(name = "Sistema de Gestión de Usuarios", description = "Ops de autenticar, crear, eliminar y actualizar usuarios")
@Log4j2
public class LoginApi implements ILoginApi {

    private final ILoginService iLoginService;

    public LoginApi(ILoginService iLoginService) {
        this.iLoginService = iLoginService;
    }

    @Override
    @Operation(summary = "controlar la autenticación de los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = ILoginResponse.AUTENTIFICATION_SUCESS,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDTO.class))}),
            @ApiResponse(responseCode  = "400", description = ILoginResponse.AUTENTIFICACION_FAIL,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode  = "404", description = ILoginResponse.NOT_FOUND,
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode  = "500", description = ILoginResponse.INTERNAL_SERVER,
                    content = {@Content(mediaType = "application/json")})})
    @GetMapping(ILoginEndPoint.LOGIN_SERVICE)
    public ResponseEntity<GenericResponseDTO> loginService(LoginDTO loginDTO) {
        return this.iLoginService.loginService(loginDTO);
    }

    @Override
    @Operation(summary = "crear una nueva cuenta de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = ILoginResponse.CREATE_SUCCESS,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponseDTO.class))}),
            @ApiResponse(responseCode  = "400", description = ILoginResponse.CREATE_FAIL,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode  = "500", description = ILoginResponse.INTERNAL_SERVER,
                    content = {@Content(mediaType = "application/json")})})
    @PostMapping(ILoginEndPoint.CREATE_ACOUNT)
    public ResponseEntity<GenericResponseDTO> saveLogin(LoginDTO loginDTO) {
        System.out.println("Prueba Entrada " + loginDTO.getUsuarioPassword());
        return iLoginService.saveLogin(loginDTO);
    }
}