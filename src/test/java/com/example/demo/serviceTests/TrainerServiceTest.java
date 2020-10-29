package com.example.demo.serviceTests;

import com.example.demo.dto.Trainer;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @Nested
    class FindTrainers {

        @Nested
        class WhenGroupedIsFalse {

            @Test
            void should_return_trainees_info() {
                List<TrainerEntity> trainers = new ArrayList<>();
                trainers.add(trainerEntity);
                when(trainerRepository.findAllAndGroupedIsFalse(0)).thenReturn(trainers);
                List<Trainer> trainersResponse = trainerService.getTrainers(false);
                assertEquals("mock trainer name", trainersResponse.get(0).getName());
            }
        }
    }

    @Nested
    class DeleteTrainer {

        @Nested
        class WhenTrainerIdIsExisted {

            @Test
            void should_delete_success() {
                when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainerEntity));
                trainerService.deleteTrainer(1L);
                verify(trainerRepository).deleteById(1L);
            }
        }

        @Nested
        class WhenTraineeIdIsUnExisted {

            @Test
            void should_throw_exception() {
                when(trainerRepository.findById(1L)).thenReturn(Optional.empty());
                ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> trainerService.deleteTrainer(1L));
                assertEquals("can not find basic info of trainer with id is " + 1L, resourceNotFoundException.getMessage());
            }
        }
    }

}
