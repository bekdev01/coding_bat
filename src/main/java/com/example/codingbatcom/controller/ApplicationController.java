package com.example.codingbatcom.controller;


import com.example.codingbatcom.entity.Application;
import com.example.codingbatcom.payLoad.ApiResponse;
import com.example.codingbatcom.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getAll(){
        return applicationService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody Application application){
        return applicationService.add(application);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> edit(@Valid@RequestBody Application application,@PathVariable Integer id){
        return applicationService.edit(application, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return applicationService.delete(id);
    }
}
