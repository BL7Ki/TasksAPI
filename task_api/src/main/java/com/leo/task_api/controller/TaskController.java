package com.leo.task_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.leo.task_api.model.Task;
import com.leo.task_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.leo.task_api.dto.TaskDTO;
import com.leo.task_api.repository.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream()
                .map(TaskDTO::new)  // Convertendo Task para TaskDTO
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok(new TaskDTO(task)))  // Convertendo Task para TaskDTO
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task(
                taskDTO.title(),
                taskDTO.description(),
                taskDTO.status()
        );

        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(new TaskDTO(savedTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build(); // Se a tarefa existir, retorna 204 No Content
        }
        return ResponseEntity.notFound().build(); // Se não existir, retorna 404 Not Found
    }
}
