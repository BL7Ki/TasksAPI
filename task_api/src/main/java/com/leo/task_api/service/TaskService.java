package com.leo.task_api.service;

import com.leo.task_api.model.Task;
import com.leo.task_api.dto.TaskDTO;
import com.leo.task_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task(taskDTO.title(), taskDTO.description(), taskDTO.status());
        Task savedTask = taskRepository.save(task);
        return new TaskDTO(savedTask);
    }


    public Optional<TaskDTO> updateTask(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDTO.title());
            task.setDescription(taskDTO.description());
            task.setStatus(taskDTO.status());

            Task updatedTask = taskRepository.save(task);
            return new TaskDTO(updatedTask);
        });
    }

    public boolean deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }
}
