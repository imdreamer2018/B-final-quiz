package com.example.demo.repositoryTests;

import com.example.demo.entity.TraineeEntity;
import com.example.demo.repository.TraineeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TraineeRepositoryTest {

    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TestEntityManager entityManager;

    private TraineeEntity traineeEntity;


    @BeforeEach
    void setUp() {
        traineeEntity = TraineeEntity.builder()
                .name("mock name")
                .grouped(false)
                .build();
    }

    @Test
    void should_return_trainees_info_when_find_ungrouped_trainees() {

        entityManager.persistAndFlush(traineeEntity);

        List<TraineeEntity> trainees = traineeRepository.findAllAndGroupedIsFalse(0);

        assertEquals("mock name", trainees.get(0).getName());
    }

}

