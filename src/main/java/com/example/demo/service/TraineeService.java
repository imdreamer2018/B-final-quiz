package com.example.demo.service;

import com.example.demo.dto.Trainee;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TraineeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public Trainee createTrainee(Trainee trainee) {
        TraineeEntity traineeEntity = Trainee.toTraineeEntity(trainee);
        // TODO GTB-知识点: - save方法可以直接返回有id的trainee对象
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

    public List<Trainee> getTraineesPage(Pageable pageable) {
        Page<TraineeEntity> trainees = traineeRepository.findAll(pageable);
        return trainees.stream()
                .map(TraineeEntity::toTrainee).collect(Collectors.toList());
    }


    public void deleteTrainee(long traineeId) {
        Optional<TraineeEntity> trainee = traineeRepository.findById(traineeId);
        // TODO GTB-知识点: - 可以使用orElseThrow方法
        if (!trainee.isPresent())
            throw new ResourceNotFoundException("can not find basic info of trainee with id is " + traineeId);
        // TODO GTB-工程实践: - deleteForeignKey方法不需要
        traineeRepository.deleteForeignKey(traineeId);
        traineeRepository.deleteById(traineeId);

    }
}
