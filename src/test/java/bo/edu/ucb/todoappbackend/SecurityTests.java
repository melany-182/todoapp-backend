package bo.edu.ucb.todoappbackend;

import bo.edu.ucb.todoappbackend.bl.SecurityBl;
import bo.edu.ucb.todoappbackend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityTests {

	@Autowired // para que Spring inyecte el objeto
	SecurityBl securityBl;

	@Test
	void testLoginSuccess() {
		User user = securityBl.login("john_doe", "password123");
		assertNotNull(user, "El usuario debería existir");
	}

	@Test
	void testLoginError() {
		try {
			securityBl.login("john_doe", "password456");
		} catch (RuntimeException ex) {
			assertEquals("Credenciales inválidas", ex.getMessage());
		}
	}
}
