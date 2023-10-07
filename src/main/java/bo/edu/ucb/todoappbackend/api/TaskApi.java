package bo.edu.ucb.todoappbackend.api;

import bo.edu.ucb.todoappbackend.bl.AuthBl;
import bo.edu.ucb.todoappbackend.bl.TaskBl;
import bo.edu.ucb.todoappbackend.dto.TaskDto;
import bo.edu.ucb.todoappbackend.dto.ResponseDto;
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

    /** Endpoint que retorna el detalle de una tarea por ID.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param taskId: El ID de la tarea a obtener.
     */
    @GetMapping("/api/v1/tasks/{taskId}")
    public ResponseDto<TaskDto> getTaskById(@PathVariable Long taskId, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.taskBl.getTaskById(taskId));
        }
    }

    /** Endpoint que retorna todas las tareas de un usuario.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     */
    @GetMapping("/api/v1/tasks")
    public ResponseDto<List<TaskDto>> getAllTasks(@RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.taskBl.getAllTasksByUserId(authBl.getUserIdFromToken(token)));
        }
    }

    /** Endpoint que permite crear una tarea.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param taskDto: La tarea a crear.
     */
    @PostMapping("/api/v1/tasks")
    public ResponseDto<TaskDto> createTask(@RequestBody TaskDto taskDto, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.taskBl.createTask(taskDto));
        }
    }

    /** Endpoint que permite cambiar el estado de una tarea.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param taskId: El ID de la tarea a actualizar.
     */
    @PutMapping("/api/v1/tasks/{taskId}")
    public ResponseDto<TaskDto> updateTaskStatus(@PathVariable Long taskId, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.taskBl.updateTaskStatusById(taskId));
        }
    }
}
