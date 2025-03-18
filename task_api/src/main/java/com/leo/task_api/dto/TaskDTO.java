package com.leo.task_api.dto;

import com.leo.task_api.model.Task;
import com.leo.task_api.model.TaskStatus;

public record TaskDTO(String title, String description, TaskStatus status) {

    // Construtor explícito para converter a entidade Task em TaskDTO
    public TaskDTO(Task task) {
        this(task.getTitle(), task.getDescription(), task.getStatus());
    }
}
