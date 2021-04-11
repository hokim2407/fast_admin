package com.example.study.model.repository;

import com.example.study.model.entity.Category;
import com.example.study.model.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement,Long> {

    Optional<Settlement> findByUserId(Long id);
}
