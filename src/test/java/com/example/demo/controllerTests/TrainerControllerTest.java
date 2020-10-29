package com.example.demo.controllerTests;

import com.example.demo.controller.TrainerController;
import com.example.demo.dto.Trainer;
import com.example.demo.service.TrainerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Nested
    class CreateTrainee {

        @Test
        void should_return_trainee_info() throws Exception {
            when(trainerService.createTrainer(trainer)).thenReturn(trainer);
            mockMvc.perform(post("/trainers")
                    .content(trainerJson.write(trainer).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is("mock trainer name")));
            verify(trainerService).createTrainer(trainer);
        }
    }
}
