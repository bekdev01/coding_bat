package com.example.codingbatcom.controller;

import com.example.codingbatcom.payLoad.ApiResponse;
import com.example.codingbatcom.payLoad.UserDto;
import com.example.codingbatcom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getAll(){
        return userService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@Valid  @RequestBody UserDto userDto){
        return userService.add(userDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> edit(@Valid @RequestBody UserDto userDto,@PathVariable Integer id){
        return userService.edit(userDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return userService.delete(id);
    }
}
