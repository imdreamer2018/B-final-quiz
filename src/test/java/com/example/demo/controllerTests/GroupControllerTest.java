package com.example.demo.controllerTests;

import com.example.demo.controller.GroupController;
import com.example.demo.dto.Group;
import com.example.demo.service.GroupService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GroupController.class)
@AutoConfigureJsonTesters
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @Autowired
    private JacksonTester<Group> groupJson;

    private Group group;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void afterEach() {
        Mockito.reset(groupService);
    }
}
