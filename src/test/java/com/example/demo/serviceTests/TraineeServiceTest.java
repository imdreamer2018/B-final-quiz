package com.example.demo.serviceTests;

import com.example.demo.dto.Trainee;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.service.TraineeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {

    private TraineeService traineeService;

    @Mock
    TraineeRepository traineeRepository;

    private Trainee trainee;

    @BeforeEach
    void setUp() {
        initMocks(this);
        traineeService = new TraineeService(traineeRepository);
        trainee = Trainee.builder()
                .name("mock name")
                .build();
    }

    @Nested
    class CreateTrainee {

            @Test
            void shouldReturnTraineeInfo() {
                Trainee traineeResponse = traineeService.createTrainee(trainee);
                assertEquals("mock name", traineeResponse.getName());
            }

    }


}
