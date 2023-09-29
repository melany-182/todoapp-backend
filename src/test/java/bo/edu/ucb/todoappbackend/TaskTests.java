package bo.edu.ucb.todoappbackend;

import bo.edu.ucb.todoappbackend.dao.TaskDao;
import bo.edu.ucb.todoappbackend.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskTests {

	@Autowired // para que Spring inyecte el objeto
	TaskDao taskDao;

	@Test
	void testTaskJPA() {
		List<Task> tasksList = taskDao.findAll();
		for (Task t : tasksList) {
			System.out.println("Tarea: " + t.getTitle());
		}
		assertNotEquals(0, tasksList.size(), "La base de datos debería tener al menos una tarea");
	}
}
