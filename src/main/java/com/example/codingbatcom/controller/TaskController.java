package com.example.codingbatcom.controller;

import com.example.codingbatcom.payLoad.ApiResponse;
import com.example.codingbatcom.payLoad.TaskDTO;
import com.example.codingbatcom.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public HttpEntity<?> getAAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        ApiResponse response = taskService.getById(id);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid  @RequestBody TaskDTO taskDTO){
        ApiResponse response = taskService.save(taskDTO);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid  @RequestBody TaskDTO taskDTO){
        ApiResponse response = taskService.update(id,taskDTO);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = taskService.delete(id);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }
}
