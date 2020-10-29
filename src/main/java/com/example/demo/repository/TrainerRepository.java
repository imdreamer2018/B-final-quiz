package com.example.demo.repository;

import com.example.demo.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
    @Query(nativeQuery = true, value = "select * from trainer t where t.grouped= :grouped")
    List<TrainerEntity> findAllAndGroupedIsFalse(@Param("grouped") int grouped);

    @Query(nativeQuery = true, value = "delete from train_group_trainers where train_group_trainers.trainers_id= :trainer_id")
    @Modifying
    @Transactional
    void deleteForeignKey(@Param("trainer_id") Long trainer_id);
}
