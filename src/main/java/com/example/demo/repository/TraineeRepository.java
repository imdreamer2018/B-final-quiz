package com.example.demo.repository;

import com.example.demo.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeRepository extends JpaRepository<TraineeEntity, Long> {
}
