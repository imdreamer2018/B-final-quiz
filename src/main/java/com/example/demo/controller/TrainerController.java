package com.example.demo.controller;

import com.example.demo.dto.Trainer;
import com.example.demo.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/hello")
    public String helloK8s() {
        return "hello k8s!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainer createTrainer(@RequestBody @Valid Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }

    @GetMapping
    public List<Trainer> getTrainers(@RequestParam(required = false) boolean grouped) {
        return trainerService.getTrainers(grouped);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(
            @NotNull(message = "trainer id can not be null")
            @DecimalMin(value = "1", message = "trainer id muse be number and greater than 1")
            @PathVariable(name = "id") Long trainerId) {
        trainerService.deleteTrainer(trainerId);
    }
}
