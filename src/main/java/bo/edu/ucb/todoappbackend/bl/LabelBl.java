package bo.edu.ucb.todoappbackend.bl;

import bo.edu.ucb.todoappbackend.dao.LabelDao;
import bo.edu.ucb.todoappbackend.entity.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LabelBl {
    private static final Logger LOG = LoggerFactory.getLogger(AuthBl.class); // LOGGER
    private final LabelDao labelDao;

    public LabelBl(LabelDao labelDao) {
        this.labelDao = labelDao;
    }

    // e. Listado de etiquetas de un usuario
    public List<Label> getAllLabelsByUserId(Long userId) {
        try {
            List<Label> labels = labelDao.findByUserUserId(userId);
            // FIXME: arreglar en un futuro, esto no es lo correcto
            for (Label l : labels) {
                l.getUser().setUsername(null);
                l.getUser().setPassword(null);
            }
            LOG.info("Etiquetas obtenidas correctamente");
            return labels;
        } catch (Exception ex) {
            LOG.info("Error al obtener las etiquetas: " + ex.getMessage());
            return null;
        }
    }

    // f. Creación de etiqueta
    public void createLabel(Label label) {
        try {
            labelDao.save(label);
            LOG.info("Etiqueta creada correctamente");
        } catch (Exception ex) {
            LOG.info("Error al crear la etiqueta: " + ex.getMessage());
        }
    }

    // g. Modificación de etiqueta
    public Label modifyLabelById(Long labelId, Label newLabel) {
        try {
            Label label = labelDao.findById(labelId).orElse(null);
            if (label != null) {
                label.setLabelName(newLabel.getLabelName());
                labelDao.save(label);
            }
            // FIXME: arreglar en un futuro, esto no es lo correcto
            assert label != null;
            label.getUser().setUsername(null);
            label.getUser().setPassword(null);
            LOG.info("Etiqueta modificada correctamente");
            return label;
        } catch (Exception ex) {
            LOG.info("Error al modificar la etiqueta: " + ex.getMessage());
            return null;
        }
    }

    // h. Eliminación de etiqueta
    public void deleteLabelById(Long labelId) {
        try {
            labelDao.deleteById(labelId);
            LOG.info("Etiqueta eliminada correctamente");
        } catch (Exception ex) {
            LOG.info("Error al eliminar la etiqueta: " + ex.getMessage());
        }
    }
}
