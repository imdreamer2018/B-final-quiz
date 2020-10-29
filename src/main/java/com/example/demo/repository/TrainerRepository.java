package com.example.demo.repository;

import com.example.demo.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
    @Query(nativeQuery = true, value = "select * from trainer t where t.grouped= :grouped")
    List<TrainerEntity> findAllAndGroupedIsFalse(@Param("grouped") int grouped);
}
