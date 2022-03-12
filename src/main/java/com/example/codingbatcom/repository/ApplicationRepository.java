package com.example.codingbatcom.repository;

import com.example.codingbatcom.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    boolean existsByName(String name);
}
