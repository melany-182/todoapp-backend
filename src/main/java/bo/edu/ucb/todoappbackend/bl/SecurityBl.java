package bo.edu.ucb.todoappbackend.bl;

import bo.edu.ucb.todoappbackend.dao.UserDao;
import bo.edu.ucb.todoappbackend.entity.User;
import org.springframework.stereotype.Service;

@Service
public class SecurityBl {

    private UserDao userDao;

    public SecurityBl(UserDao userDao) { // la inyección siempre se hace por constructor
        this.userDao = userDao;
    }

    public User login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);

        if (user == null) {
            throw new RuntimeException("Credenciales inválidas");
        }

        user.setPassword(null); // para no devolver la contraseña en la respuesta
        return user;
    }
}
