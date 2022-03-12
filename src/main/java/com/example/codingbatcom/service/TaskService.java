package com.example.codingbatcom.service;

import com.example.codingbatcom.entity.Category;
import com.example.codingbatcom.entity.Task;
import com.example.codingbatcom.payLoad.ApiResponse;
import com.example.codingbatcom.payLoad.TaskDTO;
import com.example.codingbatcom.repository.CategoryRepository;
import com.example.codingbatcom.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public ApiResponse save(TaskDTO taskDTO) {
        if (!categoryRepository.existsById(taskDTO.getCategoryId()))
            return new ApiResponse("CATEGORY NOT FOUND", false);
        return new ApiResponse("Success", true, taskRepository.save(
                new Task(
                        taskDTO.getName(),
                        taskDTO.getQuestion(),
                        taskDTO.getSolution(),
                        categoryRepository.findById(taskDTO.getCategoryId()).get()
                )
        ));
    }

    public ApiResponse getById(Integer id) {
        return taskRepository.findById(id)
                .map(task -> new ApiResponse("Success", true, task))
                .orElseGet(() -> new ApiResponse("NOT FOUND", false));
    }

    public ApiResponse getAll() {
        return new ApiResponse("Success", true, taskRepository.findAll());
    }

    public ApiResponse update(Integer id, TaskDTO taskDTO) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) return new ApiResponse("TASK NOT FOUND", false);
        if (!categoryRepository.existsById(taskDTO.getCategoryId()))
            return new ApiResponse("CATEGORY NOT FOUND", false);
        Task task = optionalTask.get();
        task.setName(taskDTO.getName());
        task.setQuestion(taskDTO.getQuestion());
        task.setSolution(taskDTO.getSolution());
        task.setCategory(categoryRepository.findById(taskDTO.getCategoryId()).get());
        task.setDone(taskDTO.isDone());
        return new ApiResponse("Success", true, taskRepository.save(task));
    }

    public ApiResponse delete(Integer id) {
        return taskRepository
                .findById(id).map(task -> new ApiResponse("Success", true, task))
                .orElse(new ApiResponse("TASK NOT FOUND", false));
    }

}
