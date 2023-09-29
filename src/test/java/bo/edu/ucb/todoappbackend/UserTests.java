package bo.edu.ucb.todoappbackend;

import bo.edu.ucb.todoappbackend.dao.UserDao;
import bo.edu.ucb.todoappbackend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTests {

	@Autowired // para que Spring inyecte el objeto
	UserDao userDao;

	@Test
	void testUserJPA() {
		List<User> usersList = userDao.findAll();
		for (User u : usersList) {
			System.out.println("Usuario: " + u.getUsername());
		}
		assertNotEquals(0, usersList.size(), "La base de datos debería tener al menos un usuario");
	}
}
