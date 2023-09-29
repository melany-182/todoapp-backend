package bo.edu.ucb.todoappbackend;

import bo.edu.ucb.todoappbackend.dao.TagDao;
import bo.edu.ucb.todoappbackend.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TagTests {

	@Autowired // para que Spring inyecte el objeto
	TagDao tagDao;

	@Test
	void testTagJPA() {
		List<Tag> tagsList = tagDao.findAll();
		for (Tag t : tagsList) {
			System.out.println("Etiqueta: " + t.getTagName());
		}
		assertNotEquals(0, tagsList.size(), "La base de datos debería tener al menos una etiqueta");
	}
}
