package bo.edu.ucb.todoappbackend.bl;

import bo.edu.ucb.todoappbackend.dao.TaskDao;
import bo.edu.ucb.todoappbackend.dto.TaskDto;
import bo.edu.ucb.todoappbackend.dao.UserDao;
import bo.edu.ucb.todoappbackend.dao.LabelDao;
import bo.edu.ucb.todoappbackend.entity.Label;
import bo.edu.ucb.todoappbackend.entity.Task;
import bo.edu.ucb.todoappbackend.entity.User;
import bo.edu.ucb.todoappbackend.util.TaskConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class TaskBl {
    private static final Logger LOG = LoggerFactory.getLogger(TaskBl.class); // LOGGER
    private final TaskDao taskDao;
    private final UserDao UserDao;
    private final LabelDao LabelDao;

    public TaskBl(TaskDao taskDao, UserDao UserDao, LabelDao LabelDao) {
        this.taskDao = taskDao;
        this.UserDao = UserDao;
        this.LabelDao = LabelDao;
    }

    // Obtención de tarea por id // FIXME: el usuario debería poder ver únicamente sus tareas
    public TaskDto getTaskById(Long taskId) {
        try {
            Task task = taskDao.findById(taskId).orElse(null);
            if (task != null) {
                LOG.info("Tarea obtenida correctamente");
                return TaskConverter.entityToDto(task);
            }
            LOG.info("Tarea con ID " + taskId + " no encontrada");
            return null;
        } catch (Exception ex) {
            LOG.info("Error al obtener la tarea: " + ex.getMessage());
            return null;
        }
    }

    // b. Listado de tareas de un usuario
    public List<TaskDto> getAllTasksByUserId(Long userId) {
        try {
            List<Task> tasks = taskDao.findByUserUserId(userId);
            List<TaskDto> taskDtos = new ArrayList<>();
            for (Task t : tasks) {
                taskDtos.add(TaskConverter.entityToDto(t));
            }
            LOG.info("Tareas obtenidas correctamente");
            return taskDtos;
        } catch (Exception ex) {
            LOG.info("Error al obtener las tareas: " + ex.getMessage());
            return Collections.emptyList();
        }
    }

    // c. Creación de tarea
    public TaskDto createTask(TaskDto taskDto) {
        try {
            Label label = LabelDao.findByLabelId(taskDto.getLabelId());
            User user = UserDao.findByUserId(taskDto.getUserId());
            Task task = TaskConverter.dtoToEntity(taskDto, label, user);
            task.setStatus("Pendiente"); // dado que Hibernate no siempre respeta el valor por defecto de la BD
            task = taskDao.save(task);
            LOG.info("Tarea creada correctamente");
            return TaskConverter.entityToDto(task);
        } catch (Exception ex) {
            LOG.info("Error al crear la tarea: " + ex.getMessage());
            return null;
        }
    }

    // d. Cambio de estado de tarea
    public TaskDto updateTaskStatusById(Long taskId) {
        try {
            Task task = taskDao.findById(taskId).orElse(null);
            if (task != null) {
                String actualStatus = task.getStatus();
                String newStatus = actualStatus.equals("Pendiente") ? "Completada" : "Pendiente";
                task.setStatus(newStatus);
                if (newStatus.equals("Completada")) {
                    task.setCompletedDate(LocalDateTime.now());
                } else {
                    task.setCompletedDate(null);
                }
                taskDao.save(task);
                LOG.info("Estado de la tarea cambiado correctamente");
                return TaskConverter.entityToDto(task);
            }
            LOG.info("Tarea con ID " + taskId + " no encontrada");
            return null;
        } catch (Exception ex) {
            LOG.info("Error al cambiar el estado de la tarea: " + ex.getMessage());
            return null;
        }
    }
}
