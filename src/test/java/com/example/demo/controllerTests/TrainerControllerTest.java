package com.example.demo.controllerTests;

import com.example.demo.controller.TrainerController;
import com.example.demo.dto.Trainer;
import com.example.demo.service.TrainerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TrainerController.class)
@AutoConfigureJsonTesters
public class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainerService trainerService;

    @Autowired
    private JacksonTester<Trainer> trainerJson;

    private Trainer trainer;


    @BeforeEach
    void setUp() {
        trainer = Trainer.builder()
                .name("mock trainer name")
                .build();
    }

    @AfterEach
    void afterEach() {
        Mockito.reset(trainerService);
    }
}
