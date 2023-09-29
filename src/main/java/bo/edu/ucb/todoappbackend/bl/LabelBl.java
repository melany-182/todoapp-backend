package bo.edu.ucb.todoappbackend.bl;

import bo.edu.ucb.todoappbackend.dao.LabelDao;
import bo.edu.ucb.todoappbackend.entity.Label;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LabelBl {
    private final LabelDao labelDao;

    public LabelBl(LabelDao labelDao) {
        this.labelDao = labelDao;
    }

    // e. Listado de etiquetas de un usuario
    public List<Label> getAllLabelsByUserId(Long userId) {
        List<Label> labels = labelDao.findByUserUserId(userId);
        // FIXME: arreglar en un futuro, esto no es lo correcto
        for (Label l : labels) {
            l.getUser().setUsername(null);
            l.getUser().setPassword(null);
        }
        return labels;
    }

    // f. Creación de etiqueta
    public void createLabel(Label label) {
        try {
            labelDao.save(label);
        } catch (Exception e) {
            System.err.println(e.getMessage()); // FIXME: logs
        }
    }

    // g. Modificación de etiqueta
    public Label updateLabelById(Long labelId, Label newLabel) {
        Label label = labelDao.findById(labelId).orElse(null);
        if (label != null) {
            label.setLabelName(newLabel.getLabelName());
            labelDao.save(label);
        }
        // FIXME: arreglar en un futuro, esto no es lo correcto
        assert label != null;
        label.getUser().setUsername(null);
        label.getUser().setPassword(null);
        return label;
    }

    // h. Eliminación de etiqueta
    public void deleteLabelById(Long labelId) {
        labelDao.deleteById(labelId);
    }
}
