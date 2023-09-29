package bo.edu.ucb.todoappbackend.bl;

import bo.edu.ucb.todoappbackend.dao.TaskDao;
import bo.edu.ucb.todoappbackend.entity.Task;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskBl {

    private final TaskDao taskDao;

    public TaskBl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // b. Listado de tareas de un usuario
    public List<Task> getAllTasksByUserId(Long userId) {
        List<Task> tasks = taskDao.findByUserUserId(userId);
        // FIXME: arreglar en un futuro, esto no es lo correcto
        for (Task t : tasks) {
            t.getUser().setUsername(null);
            t.getUser().setPassword(null);
        }
        return tasks;
    }

    // c. Creación de tarea
    public void createTask(Task task) {
        try {
            taskDao.save(task);
        } catch (Exception e) {
            System.err.println(e.getMessage()); // FIXME: logs
        }
    }

    // TODO: agregar función que permita editar todos los campos de una tarea
    // d. Cambio de estado de tarea
    public Task updateTaskStatusById(Long taskId) {
        Task task = taskDao.findById(taskId).orElse(null);
        if (task != null) {
            String actualStatus = task.getStatus();
            String newStatus = actualStatus.equals("Pendiente") ? "Completada" : "Pendiente";
            task.setStatus(newStatus);
            taskDao.save(task);
        }
        // FIXME: arreglar en un futuro, esto no es lo correcto
        assert task != null;
        task.getUser().setUsername(null);
        task.getUser().setPassword(null);
        return task;
    }
}
