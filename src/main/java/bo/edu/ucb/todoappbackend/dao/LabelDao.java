package bo.edu.ucb.todoappbackend.dao;

import bo.edu.ucb.todoappbackend.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LabelDao extends JpaRepository<Label, Long> {

    public List<Label> findByUserUserId(Long userId); // TODO: implementar este método
}
