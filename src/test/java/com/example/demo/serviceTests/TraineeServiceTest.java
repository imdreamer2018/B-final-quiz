package com.example.demo.serviceTests;

import com.example.demo.repository.TraineeRepository;
import com.example.demo.service.TraineeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {

    private TraineeService traineeService;

    @Mock
    TraineeRepository traineeRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        traineeService = new TraineeService(traineeRepository);
    }

}
