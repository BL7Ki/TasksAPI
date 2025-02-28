package com.leo.task_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.task_api.dto.TaskDTO;
import com.leo.task_api.repository.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Método para pegar todas as tarefas
    @GetMapping
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream()
                .map(task -> new TaskDTO(task))  // Convertendo Task para TaskDTO
                .collect(Collectors.toList());
    }

    // Método para pegar uma tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok(new TaskDTO(task)))  // Convertendo Task para TaskDTO
                .orElse(ResponseEntity.notFound().build());
    }
}
