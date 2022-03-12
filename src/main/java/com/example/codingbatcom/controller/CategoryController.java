package com.example.codingbatcom.controller;

import com.example.codingbatcom.payLoad.ApiResponse;
import com.example.codingbatcom.payLoad.CategoryDto;
import com.example.codingbatcom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getAll(){
        return categoryService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.add(categoryDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> edit(@Valid@RequestBody CategoryDto categoryDto,@PathVariable Integer id){
        return categoryService.edit(categoryDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }
}
