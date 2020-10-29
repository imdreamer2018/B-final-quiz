package com.example.demo.service;

import com.example.demo.dto.Trainee;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Trainee> getTrainees(boolean grouped) {
        List<TraineeEntity> trainees = new ArrayList<>();
        if (!grouped) {
            trainees = traineeRepository.findAllAndGroupedIsFalse(0);
        }
        return trainees.stream()
                .map(TraineeEntity::toTrainee).collect(Collectors.toList());
    }
}
