package com.example.codingbatcom.service;

import com.example.codingbatcom.entity.Application;
import com.example.codingbatcom.payLoad.ApiResponse;
import com.example.codingbatcom.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    public ResponseEntity<ApiResponse> getAll(){
        List<Application> applications = applicationRepository.findAll();
        return ResponseEntity.status(applications.isEmpty()?404:200).body(
                applications.isEmpty()?new ApiResponse("Applications not found",false):
                        new ApiResponse("Success",true,applications));

    }

    public ResponseEntity<ApiResponse> add(Application application){
        if(applicationRepository.existsByName(application.getName()))
            return ResponseEntity.status(409).body(new ApiResponse("This application already exist",false));

        applicationRepository.save(application);
        return ResponseEntity.status(201).body(new ApiResponse("Success",true,"ID: "+application.getId()));
    }

    public ResponseEntity<ApiResponse> edit(Application application,Integer id){
        if(!applicationRepository.existsById(id))
            return ResponseEntity.status(404).body(new ApiResponse("Application not found",false));
        Application application1 = applicationRepository.findById(id).get();
        if(application.getName()!=null){
            if(applicationRepository.existsByName(application.getName()))
                return ResponseEntity.status(409).body(new ApiResponse("This application already exist",false));
            application1.setName(application.getName());
        }
        applicationRepository.save(application1);
        return ResponseEntity.status(202).body(new ApiResponse("Success",true));
    }

    public ResponseEntity<ApiResponse> delete(Integer id){
        if(!applicationRepository.existsById(id))
            return ResponseEntity.status(404).body(new ApiResponse("Application not found",false));
        applicationRepository.deleteById(id);
        return ResponseEntity.status(202).body(new ApiResponse("Success",true));
    }
}
