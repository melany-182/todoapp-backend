package bo.edu.ucb.todoappbackend.dao;

import bo.edu.ucb.todoappbackend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TagDao extends JpaRepository<Tag, Long> {

    public List<Tag> findByUserUserId(Long userId); // TODO: implementar este método
}
