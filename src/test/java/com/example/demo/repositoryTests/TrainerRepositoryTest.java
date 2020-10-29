package com.example.demo.repositoryTests;

import com.example.demo.entity.TrainerEntity;
import com.example.demo.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TrainerRepositoryTest {

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TestEntityManager entityManager;

    private TrainerEntity trainerEntity;


    @BeforeEach
    void setUp() {
        trainerEntity = TrainerEntity.builder()
                .name("mock trainer name")
                .grouped(false)
                .build();
    }

    @Test
    void should_return_trainees_info_when_find_ungrouped_trainees() {

        entityManager.persistAndFlush(trainerEntity);

        List<TrainerEntity> trainers = trainerRepository.findAllAndGroupedIsFalse(0);

        assertEquals("mock trainer name", trainers.get(0).getName());
    }
}
