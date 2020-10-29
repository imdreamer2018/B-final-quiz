package com.example.demo.repositoryTests;

import com.example.demo.entity.TrainerEntity;
import com.example.demo.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TrainerRepositoryTest {

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
}
