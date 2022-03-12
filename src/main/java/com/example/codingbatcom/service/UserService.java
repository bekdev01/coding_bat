package com.example.codingbatcom.service;

import com.example.codingbatcom.entity.Application;
import com.example.codingbatcom.entity.User;
import com.example.codingbatcom.payLoad.ApiResponse;
import com.example.codingbatcom.payLoad.UserDto;
import com.example.codingbatcom.repository.ApplicationRepository;
import com.example.codingbatcom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationRepository applicationRepository;

    public ResponseEntity<ApiResponse> getAll(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(users.isEmpty()?404:200).body(users.isEmpty()?
                new ApiResponse("Users not found",false):
                new ApiResponse("Success",true,users));
    }

    public ResponseEntity<ApiResponse> add(UserDto userDto){
        if(userRepository.existsByUsername(userDto.getUsername()))
            return ResponseEntity.status(409).body(new ApiResponse("This username already exist",false));

        User user=new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setApplications(applicationRepository.findAll());
        userRepository.save(user);

        return ResponseEntity.status(201).body(new ApiResponse("Success",true,"ID: "+user.getId()));
    }

    public ResponseEntity<ApiResponse> edit(UserDto userDto,Integer id){
        if(!userRepository.existsById(id))
            return ResponseEntity.status(404).body(new ApiResponse("User not found",false));
        User user = userRepository.findById(id).get();
        if(userDto.getUsername()!=null){
            if(userRepository.existsByUsername(userDto.getUsername()))
                return ResponseEntity.status(409).body(new ApiResponse("This username already exist",false));
            user.setUsername(userDto.getUsername());
        }
        if(userDto.getPassword()!=null){
            user.setPassword(userDto.getPassword());
        }

        userRepository.save(user);
        return ResponseEntity.status(202).body(new ApiResponse("Success",true));
    }

    public ResponseEntity<ApiResponse> delete(Integer id){
        if(!userRepository.existsById(id))
            return ResponseEntity.status(404).body(new ApiResponse("User not found",false));

        userRepository.deleteById(id);
        return ResponseEntity.status(204).body(new ApiResponse("Success",true));
    }


}
