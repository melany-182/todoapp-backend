package bo.edu.ucb.todoappbackend;

import bo.edu.ucb.todoappbackend.dao.LabelDao;
import bo.edu.ucb.todoappbackend.entity.Label;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LabelTests {

	@Autowired // para que Spring inyecte el objeto
	LabelDao labelDao;

	@Test
	void testLabelJPA() {
		List<Label> labelsList = labelDao.findAll();
		for (Label l : labelsList) {
			System.out.println("Etiqueta: " + l.getLabelName());
		}
		assertNotEquals(0, labelsList.size(), "La base de datos debería tener al menos una etiqueta");
	}
}
