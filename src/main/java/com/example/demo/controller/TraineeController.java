package com.example.demo.controller;

import com.example.demo.dto.Trainee;
import com.example.demo.service.TraineeService;
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
@RequestMapping(value = "/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee createTrainee(@RequestBody @Valid Trainee trainee) {
        return traineeService.createTrainee(trainee);
    }

    @GetMapping
    public List<Trainee> getTrainees(@RequestParam(required = false) boolean grouped) {
        return traineeService.getTrainees(grouped);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainee(
            @NotNull(message = "trainee id can not be null")
            @DecimalMin(value = "1", message = "trainee id muse be number and greater than 1")
            @PathVariable(name = "id") Long traineeId) {
        traineeService.deleteTrainee(traineeId);
    }

}
