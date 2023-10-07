package bo.edu.ucb.todoappbackend.bl;

import bo.edu.ucb.todoappbackend.dao.LabelDao;
import bo.edu.ucb.todoappbackend.dao.UserDao;
import bo.edu.ucb.todoappbackend.dto.LabelDto;
import bo.edu.ucb.todoappbackend.entity.Label;
import bo.edu.ucb.todoappbackend.entity.User;
import bo.edu.ucb.todoappbackend.util.LabelConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LabelBl {
    private static final Logger LOG = LoggerFactory.getLogger(LabelBl.class); // LOGGER
    private final LabelDao labelDao;
    private final UserDao UserDao;

    public LabelBl(LabelDao labelDao, UserDao UserDao) {
        this.labelDao = labelDao;
        this.UserDao = UserDao;
    }

    // e. Listado de etiquetas de un usuario
    public List<LabelDto> getAllLabelsByUserId(Long userId) {
        try {
            List<Label> labels = labelDao.findByUserUserId(userId);
            List<LabelDto> labelDtos = new ArrayList<>();
            for (Label l : labels) {
                labelDtos.add(LabelConverter.entityToDto(l));
            }
            LOG.info("Etiquetas obtenidas correctamente");
            return labelDtos;
        } catch (Exception ex) {
            LOG.info("Error al obtener las etiquetas: " + ex.getMessage());
            return Collections.emptyList();
        }
    }

    // f. Creación de etiqueta
    public LabelDto createLabel(LabelDto labelDto) {
        try {
            User user = UserDao.findByUserId(labelDto.getUserId());
            Label label = LabelConverter.dtoToEntity(labelDto, user);
            label = labelDao.save(label);
            LOG.info("Etiqueta creada correctamente");
            return LabelConverter.entityToDto(label);
        } catch (Exception ex) {
            LOG.info("Error al crear la etiqueta: " + ex.getMessage());
            return null;
        }
    }

    // g. Modificación de etiqueta
    public LabelDto modifyLabelById(Long labelId, LabelDto newLabelDto) {
        try {
            Label label = labelDao.findById(labelId).orElse(null);
            if (label != null) {
                label.setLabelName(newLabelDto.getLabelName());
                labelDao.save(label);
                LOG.info("Etiqueta modificada correctamente");
                return LabelConverter.entityToDto(label);
            }
            LOG.info("Etiqueta con ID " + labelId + " no encontrada");
            return null;
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
