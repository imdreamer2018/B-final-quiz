package com.example.demo.service;

import com.example.demo.dto.Trainer;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer createTrainer(Trainer trainer) {
        TrainerEntity trainerEntity = Trainer.toTrainerEntity(trainer);
        trainerRepository.save(trainerEntity);
        return TrainerEntity.toTrainer(trainerEntity);
    }

    public List<Trainer> getTrainers(boolean grouped) {
        List<TrainerEntity> trainers = new ArrayList<>();
        if (!grouped) {
            trainers = trainerRepository.findAllAndGroupedIsFalse(0);
        }
        return trainers.stream()
                .map(TrainerEntity::toTrainer).collect(Collectors.toList());
    }
}
