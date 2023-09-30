package bo.edu.ucb.todoappbackend.bl;

import bo.edu.ucb.todoappbackend.dao.TaskDao;
import bo.edu.ucb.todoappbackend.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class TaskBl {
    private static final Logger LOG = LoggerFactory.getLogger(AuthBl.class); // LOGGER
    private final TaskDao taskDao;

    public TaskBl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // b. Listado de tareas de un usuario
    public List<Task> getAllTasksByUserId(Long userId) {
        try {
            List<Task> tasks = taskDao.findByUserUserId(userId);
            // FIXME: arreglar en un futuro, esto no es lo correcto
            for (Task t : tasks) {
                t.getUser().setUsername(null);
                t.getUser().setPassword(null);
            }
            LOG.info("Tareas obtenidas correctamente");
            return tasks;
        } catch (Exception ex) {
            LOG.info("Error al obtener las tareas: " + ex.getMessage());
            return null;
        }
    }

    // c. Creación de tarea
    public void createTask(Task task) {
        try {
            taskDao.save(task);
            LOG.info("Tarea creada correctamente");
        } catch (Exception ex) {
            LOG.info("Error al crear la tarea: " + ex.getMessage());
        }
    }

    // TODO: agregar función que permita editar todos los campos de una tarea
    // d. Cambio de estado de tarea
    public Task updateTaskStatusById(Long taskId) {
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
            }
            // FIXME: arreglar en un futuro, esto no es lo correcto
            assert task != null;
            task.getUser().setUsername(null);
            task.getUser().setPassword(null);
            LOG.info("Estado de la tarea cambiado correctamente");
            return task;
        } catch (Exception ex) {
            LOG.info("Error al cambiar el estado de la tarea: " + ex.getMessage());
            return null;
        }
    }
}
