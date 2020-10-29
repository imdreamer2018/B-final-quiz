package com.example.demo.serviceTests;

import com.example.demo.dto.Trainer;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Nested
    class CreateTrainer {

        @Test
        void should_return_trainer_info() {
            Trainer trainerResponse = trainerService.createTrainer(trainer);
            assertEquals("mock trainer name", trainerResponse.getName());
        }
    }

}
