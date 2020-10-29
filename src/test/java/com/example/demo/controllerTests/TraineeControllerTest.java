package com.example.demo.controllerTests;

import com.example.demo.controller.TraineeController;
import com.example.demo.dto.Trainee;
import com.example.demo.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TraineeController.class)
@AutoConfigureJsonTesters
public class TraineeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TraineeService traineeService;

    @Autowired
    private JacksonTester<Trainee> traineeJacksonTester;
}
