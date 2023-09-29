package bo.edu.ucb.todoappbackend.api;

import bo.edu.ucb.todoappbackend.bl.AuthBl;
import bo.edu.ucb.todoappbackend.dto.LoginRequestDto;
import bo.edu.ucb.todoappbackend.dto.ResponseDto;
import bo.edu.ucb.todoappbackend.dto.TokenDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LoginApi {

    AuthBl authBl;

    public LoginApi(AuthBl authBl) {
        this.authBl = authBl;
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseDto<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        TokenDto tokenDto;
        try {
            tokenDto = authBl.login(loginRequestDto);
        } catch (RuntimeException ex) {
            return new ResponseDto<>("TODO-0001", ex.getMessage());
        }
        return new ResponseDto<>(tokenDto);
    }
}
