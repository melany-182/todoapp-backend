package bo.edu.ucb.todoappbackend;

import bo.edu.ucb.todoappbackend.bl.AuthBl;
import bo.edu.ucb.todoappbackend.dto.LoginRequestDto;
import bo.edu.ucb.todoappbackend.dto.TokenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityTests {

	@Autowired // para que Spring inyecte el objeto
	AuthBl authBl;

	LoginRequestDto loginRequestDtoSuccess = new LoginRequestDto("john_doe", "password123");
	LoginRequestDto loginRequestDtoError = new LoginRequestDto("john_doe", "password456");

	@Test
	void testLoginSuccess() {
		TokenDto tokenDto = authBl.login(loginRequestDtoSuccess);
		assertNotNull(tokenDto, "El usuario debería existir");
	}

	@Test
	void testLoginError() {
		try {
			authBl.login(loginRequestDtoError);
		} catch (RuntimeException ex) {
			assertEquals("Credenciales inválidas", ex.getMessage());
		}
	}
}
