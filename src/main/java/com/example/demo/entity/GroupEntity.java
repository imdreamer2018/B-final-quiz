package com.example.demo.entity;

import com.example.demo.dto.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "group")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private List<TrainerEntity> trainerEntities;
    private List<TraineeEntity> traineeEntities;

    public static Group toGroup(GroupEntity groupEntity) {

        return Group.builder()
                .id(groupEntity.getId())
                .name(groupEntity.getName())
                .trainers(groupEntity.getTrainerEntities().stream().map(TrainerEntity::toTrainer).collect(Collectors.toList()))
                .trainees(groupEntity.getTraineeEntities().stream().map(TraineeEntity::toTrainee).collect(Collectors.toList()))
                .build();
    }
}
