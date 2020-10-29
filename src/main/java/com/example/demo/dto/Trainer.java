package com.example.demo.dto;

import com.example.demo.entity.TrainerEntity;
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
public class Trainer {

    private Long id;
    @NotNull(message = "trainer name can not empty")
    private String name;
    @JsonIgnore
    private boolean grouped = false;

    public static TrainerEntity toTrainerEntity(Trainer trainer) {
        return TrainerEntity.builder()
                .name(trainer.getName())
                .grouped(trainer.grouped)
                .build();

    }
}
