package bo.edu.ucb.todoappbackend.api;

import bo.edu.ucb.todoappbackend.bl.AuthBl;
import bo.edu.ucb.todoappbackend.bl.TaskBl;
import bo.edu.ucb.todoappbackend.dto.ResponseDto;
import bo.edu.ucb.todoappbackend.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TaskApi {

    private final TaskBl taskBl;
    private final AuthBl authBl;

    @Autowired
    public TaskApi(TaskBl taskBl, AuthBl authBl) {
        this.taskBl = taskBl;
        this.authBl = authBl;
    }

    /** Endpoint que retorna todas las tareas de un usuario.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     */
    @GetMapping("/api/v1/tasks")
    public ResponseDto<List<Task>> getAllTasks(@RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.taskBl.getAllTasksByUserId(authBl.getUserIdFromToken(token)));
        }
    }

    /** Endpoint que permite crear una tarea.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param task: La tarea a crear.
     */
    @PostMapping("/api/v1/tasks")
    public ResponseDto<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            this.taskBl.createTask(task);
            return new ResponseDto<>(task);
        }
    }

    /** Endpoint que permite cambiar el estado de una tarea.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param taskId: El ID de la tarea a actualizar.
     */
    @PutMapping("/api/v1/tasks/{taskId}")
    public ResponseDto<Task> updateTaskStatus(@PathVariable Long taskId, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.taskBl.updateTaskStatusById(taskId));
        }
    }
}
