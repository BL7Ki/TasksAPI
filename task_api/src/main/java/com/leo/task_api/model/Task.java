package com.leo.task_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Lombok vai gerar getters, setters, toString, equals, hashCode e construtores
@Entity // A entidade que se comunica com o banco de dados
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Garantindo a geração automática do ID
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)  // Armazena o status como String no banco de dados
    private TaskStatus status;

    public Task(String title, String description, TaskStatus status) {
    }
}
