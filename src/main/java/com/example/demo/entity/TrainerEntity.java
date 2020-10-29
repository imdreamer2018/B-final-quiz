package com.example.demo.entity;

import com.example.demo.dto.Trainer;
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
@Table(name = "trainer")
public class TrainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean grouped;

    public static Trainer toTrainer(TrainerEntity trainerEntity) {
        return Trainer.builder()
                .id(trainerEntity.getId())
                .name(trainerEntity.getName())
                .grouped(trainerEntity.getGrouped())
                .build();
    }
}
