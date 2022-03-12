package com.example.codingbatcom.repository;

import com.example.codingbatcom.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
