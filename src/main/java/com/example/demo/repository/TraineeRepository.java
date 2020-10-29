package com.example.demo.repository;

import com.example.demo.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraineeRepository extends JpaRepository<TraineeEntity, Long> {

    @Query(nativeQuery = true, value = "select * from trainee t where t.grouped= :grouped")
    List<TraineeEntity> findAllAndGroupedIsFalse(@Param("grouped") int grouped);
}
