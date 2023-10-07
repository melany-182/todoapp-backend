package bo.edu.ucb.todoappbackend.dao;

import bo.edu.ucb.todoappbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    public User findByUsernameAndPassword(String username, String password);

    public User findByUserId(Long userId);
}
