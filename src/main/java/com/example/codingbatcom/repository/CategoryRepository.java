package com.example.codingbatcom.repository;

import com.example.codingbatcom.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
