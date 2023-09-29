package bo.edu.ucb.todoappbackend.api;

import bo.edu.ucb.todoappbackend.bl.SecurityBl;
import bo.edu.ucb.todoappbackend.dto.LoginRequestDto;
import bo.edu.ucb.todoappbackend.dto.ResponseDto;
import bo.edu.ucb.todoappbackend.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApi {

    SecurityBl securityBl;

    public LoginApi(SecurityBl securityBl) {
        this.securityBl = securityBl;
    }

    @PostMapping("/api/v1/login")
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        User user;
        try {
            user = securityBl.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        } catch (RuntimeException ex) {
            return new ResponseDto("TODO-1000", ex.getMessage());
        }
        return new ResponseDto(user);
    }
}
