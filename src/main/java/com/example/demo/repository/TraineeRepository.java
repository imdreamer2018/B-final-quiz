package com.example.demo.repository;

import com.example.demo.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TraineeRepository extends JpaRepository<TraineeEntity, Long> {

    @Query(nativeQuery = true, value = "select * from trainee t where t.grouped= :grouped")
    List<TraineeEntity> findAllAndGroupedIsFalse(@Param("grouped") int grouped);

    @Query(nativeQuery = true, value = "delete from train_group_trainees where train_group_trainees.trainees_id= :trainee_id")
    @Modifying
    @Transactional
    void deleteForeignKey(@Param("trainee_id") Long trainee_id);
}
