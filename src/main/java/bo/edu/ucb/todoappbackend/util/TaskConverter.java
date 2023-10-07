package bo.edu.ucb.todoappbackend.util;

import bo.edu.ucb.todoappbackend.dto.TaskDto;
import bo.edu.ucb.todoappbackend.entity.Task;
import bo.edu.ucb.todoappbackend.entity.Label;
import bo.edu.ucb.todoappbackend.entity.User;

public class TaskConverter {

    public static TaskDto entityToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(task.getTaskId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setCompletedDate(task.getCompletedDate());
        taskDto.setStatus(task.getStatus());
        taskDto.setArchived(task.getArchived());
        if (task.getLabel() != null) {
            taskDto.setLabelId(task.getLabel().getLabelId());
        }
        taskDto.setUserId(task.getUser().getUserId());
        return taskDto;
    }

    public static Task dtoToEntity(TaskDto taskDto, Label label, User user) {
        Task task = new Task();
        task.setTaskId(taskDto.getTaskId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setCompletedDate(taskDto.getCompletedDate());
        task.setStatus(taskDto.getStatus());
        task.setArchived(taskDto.getArchived());
        task.setLabel(label);
        task.setUser(user);
        return task;
    }
}
