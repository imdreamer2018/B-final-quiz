package com.example.demo.dto;

import com.example.demo.entity.TraineeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trainee {

    private Long id;
    @NotNull
    private String name;
    @JsonIgnore
    private boolean grouped = false;

    public static TraineeEntity toTraineeEntity (Trainee trainee) {
        return TraineeEntity.builder()
                .name(trainee.getName())
                .grouped(trainee.grouped)
                .build();
    }
}
