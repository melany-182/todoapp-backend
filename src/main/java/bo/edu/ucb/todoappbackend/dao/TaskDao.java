package bo.edu.ucb.todoappbackend.dao;

import bo.edu.ucb.todoappbackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskDao extends JpaRepository<Task, Long> {

    public List<Task> findByUserUserId(Long userId);
}
