package com.example.demo.entity;

import com.example.demo.dto.Trainee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trainee")
public class TraineeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean grouped;

    public static Trainee toTrainee(TraineeEntity traineeEntity) {
        return Trainee.builder()
                .id(traineeEntity.getId())
                .name(traineeEntity.getName())
                .grouped(traineeEntity.getGrouped())
                .build();
    }
}
