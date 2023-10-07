package bo.edu.ucb.todoappbackend.dto;

import java.time.LocalDateTime;

public class TaskDto {
    private Long taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime completedDate;
    private String status;
    private boolean archived;
    private Long labelId;
    private Long userId;

    public TaskDto() {} // importante: constructor por defecto / sin argumentos

    public TaskDto(Long taskId, String title, String description, LocalDateTime dueDate, LocalDateTime completedDate, String status, boolean archived, Long labelId, Long userId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.status = status;
        this.archived = archived;
        this.labelId = labelId;
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", completedDate=" + completedDate +
                ", status='" + status + '\'' +
                ", archived=" + archived +
                ", labelId=" + labelId +
                ", userId=" + userId +
                '}';
    }
}
