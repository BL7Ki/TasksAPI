package com.leo.task_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leo.task_api.model.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {}

/* Principais metodos do JPA
 * 
 * save
 * delete
 * findAll
 * findById
 * count
 * exists
 */