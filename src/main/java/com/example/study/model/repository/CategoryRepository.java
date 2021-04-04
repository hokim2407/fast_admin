package com.example.study.model.repository;

import com.example.study.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository     extends JpaRepository<Category,Long> {

}
