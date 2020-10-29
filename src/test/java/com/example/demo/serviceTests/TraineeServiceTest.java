package com.example.demo.serviceTests;

import com.example.demo.dto.Trainee;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.service.TraineeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {

    private TraineeService traineeService;

    @Mock
    TraineeRepository traineeRepository;

    private Trainee trainee;

    private TraineeEntity traineeEntity;

    @BeforeEach
    void setUp() {
        initMocks(this);
        traineeService = new TraineeService(traineeRepository);
        trainee = Trainee.builder()
                .name("mock name")
                .build();
        traineeEntity = Trainee.toTraineeEntity(trainee);
    }

    @Nested
    class CreateTrainee {

            @Test
            void shouldReturnTraineeInfo() {
                Trainee traineeResponse = traineeService.createTrainee(trainee);
                assertEquals("mock name", traineeResponse.getName());
            }

    }

    @Nested
    class FindTrainee {

        @Nested
        class WhenGroupedIsFalse {

            @Test
            void should_return_trainees_info() {
                List<TraineeEntity> trainees = new ArrayList<>();
                trainees.add(traineeEntity);
                when(traineeRepository.findAllAndGroupedIsFalse(0)).thenReturn(trainees);
                List<Trainee> traineesResponse = traineeService.getTrainees(false);
                assertEquals("mock name", traineesResponse.get(0).getName());
            }


        }
    }


}
