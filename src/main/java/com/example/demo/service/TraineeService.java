package com.example.demo.service;

import com.example.demo.dto.Trainee;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public Trainee createTrainee(Trainee trainee) {
        TraineeEntity traineeEntity = Trainee.toTraineeEntity(trainee);
        traineeRepository.save(traineeEntity);
        trainee.setId(traineeEntity.getId());
        return trainee;
    }
}
