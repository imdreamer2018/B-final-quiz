package com.example.demo.controllerTests;

import com.example.demo.controller.GroupController;
import com.example.demo.dto.Group;
import com.example.demo.dto.Trainee;
import com.example.demo.dto.Trainer;
import com.example.demo.service.GroupService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupController.class)
@AutoConfigureJsonTesters
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @Autowired
    private JacksonTester<Group> groupJson;

    private List<Trainee> trainees = new ArrayList<>();

    private List<Trainer> trainers = new ArrayList<>();

    private List<Group> groups = new ArrayList<>();


    @BeforeEach
    void setUp() {
        for (int i = 0; i < 2; i++) {
            trainees.add(Trainee.builder()
                    .name("mock name"+ i)
                    .grouped(false)
                    .build());
        }
        for (int i = 0; i < 2; i++) {
            trainers.add(Trainer.builder()
                    .name("mock trainer name"+ i)
                    .grouped(false)
                    .build());
        }
        Group group = Group.builder()
                .name("mock group")
                .trainers(trainers)
                .trainees(trainees)
                .build();
        groups.add(group);
    }

    @AfterEach
    void afterEach() {
        Mockito.reset(groupService);
    }

    @Nested
    class CreateGroups {

        @Test
        void should_return_groups_info() throws Exception {
            when(groupService.autoGrouping()).thenReturn(groups);
            mockMvc.perform(post("/groups/auto-grouping")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$", hasSize(1)));
            verify(groupService).autoGrouping();
        }
    }

    @Nested
    class GetGroups {

        @Test
        void should_return_groups_info() throws Exception {
            when(groupService.getGroups()).thenReturn(groups);
            mockMvc.perform(get("/groups")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)));
            verify(groupService).getGroups();
        }
    }
}
