package com.example.codingbatcom.service;

import com.example.codingbatcom.entity.Application;
import com.example.codingbatcom.entity.Category;
import com.example.codingbatcom.payLoad.ApiResponse;
import com.example.codingbatcom.payLoad.CategoryDto;
import com.example.codingbatcom.repository.ApplicationRepository;
import com.example.codingbatcom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ApplicationRepository applicationRepository;

    public ResponseEntity<ApiResponse> getAll(){
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.status(categories.isEmpty()?404:200).body(categories.isEmpty()?
                new ApiResponse("Category not found",false):
                new ApiResponse("Success",true,categories));
    }

    public ResponseEntity<ApiResponse> add(CategoryDto categoryDto){
        Category category=new Category();
            category.setName(categoryDto.getName());
            category.setReference(categoryDto.getReference());

        Set<Integer> applicationId = categoryDto.getApplicationId();
        Set<Application> applications=new HashSet<>();
        for (Integer id : applicationId) {
            if(!applicationRepository.existsById(id))
                return ResponseEntity.status(404).body(new ApiResponse("Application not found",false));
            applications.add(applicationRepository.findById(id).get());
        }
        category.setApplications(applications);
        categoryRepository.save(category);
        return ResponseEntity.status(201).body(new ApiResponse("Success",true,"ID: "+category.getId()));
    }

    public ResponseEntity<ApiResponse> edit(CategoryDto categoryDto,Integer id){
        if(!categoryRepository.existsById(id))
            return ResponseEntity.status(404).body(new ApiResponse("Category not found",false));

        Category category = categoryRepository.findById(id).get();
        if(categoryDto.getName()!=null)
            category.setName(categoryDto.getName());
        if(categoryDto.getReference()!=null)
            category.setReference(categoryDto.getReference());
        if(categoryDto.getNumberDoneExtraTasks()!=null)
            category.setNumberDoneExtraTasks(categoryDto.getNumberDoneExtraTasks());

        Set<Integer> applicationId = categoryDto.getApplicationId();
        Set<Application> applications =new HashSet<>();
        if(applicationId!=null){
            for (Integer code : applicationId) {
                if(!applicationRepository.existsById(code))
                    return ResponseEntity.status(404).body(new ApiResponse("Application not found",false));
                applications.add(applicationRepository.findById(code).get());
            }
            category.setApplications(applications);
        }
        categoryRepository.save(category);
        return ResponseEntity.status(202).body(new ApiResponse("Success",true));
    }

    public ResponseEntity<ApiResponse> delete(Integer id){
        if(!categoryRepository.existsById(id))
            return ResponseEntity.status(404).body(new ApiResponse("Category not found",false));

        categoryRepository.deleteById(id);
        return ResponseEntity.status(204).body(new ApiResponse("Success",true));
    }
}
