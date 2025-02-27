package com.leo.task_api.dto;

import com.leo.task_api.model.TaskStatus;

public record TaskDTO(String title, String description, TaskStatus status) {}
