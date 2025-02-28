package com.leo.task_api.dto;

import com.leo.task_api.model.TaskStatus;

public record TaskDTO(Long id, String title, String description, TaskStatus status) {}
