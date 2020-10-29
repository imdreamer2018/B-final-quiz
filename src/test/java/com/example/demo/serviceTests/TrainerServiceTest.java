package com.example.demo.serviceTests;

import com.example.demo.dto.Trainer;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {

    private TrainerService trainerService;

    @Mock
    TrainerRepository trainerRepository;

    private Trainer trainer;

    private TrainerEntity trainerEntity;

    @BeforeEach
    void setUp() {
        initMocks(this);
        trainerService = new TrainerService(trainerRepository);
        trainer = Trainer.builder()
                .name("mock trainer name")
                .build();
        trainerEntity = Trainer.toTrainerEntity(trainer);
    }

}
