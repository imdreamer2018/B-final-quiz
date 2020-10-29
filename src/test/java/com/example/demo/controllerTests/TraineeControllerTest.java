package com.example.demo.controllerTests;

import com.example.demo.controller.TraineeController;
import com.example.demo.dto.Trainee;
import com.example.demo.service.TraineeService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TraineeController.class)
@AutoConfigureJsonTesters
public class TraineeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TraineeService traineeService;

    @Autowired
    private JacksonTester<Trainee> traineeJson;

    private Trainee trainee;


    @BeforeEach
    void setUp() {
        trainee = Trainee.builder()
                .name("mock name")
                .build();
    }

    @AfterEach
    void afterEach() {
        Mockito.reset(traineeService);
    }

    @Nested
    class CreateTrainee {

        @Test
        void should_return_trainee_info() throws Exception {
            when(traineeService.createTrainee(trainee)).thenReturn(trainee);
            mockMvc.perform(post("/trainees")
                    .content(traineeJson.write(trainee).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is("mock name")));
            verify(traineeService).createTrainee(trainee);
        }
    }

    @Nested
    class FindTrainees {

        @Nested
        class WhenGroupedIsFalse {

            @Test
            void should_return_trainees_info() throws Exception {
                List<Trainee> trainees = new ArrayList<>();
                trainees.add(trainee);
                when(traineeService.getTrainees(false)).thenReturn(trainees);
                mockMvc.perform(get("/trainees?grouped=false")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].name", is("mock name")));
                verify(traineeService).getTrainees(false);
            }
        }
    }
}
