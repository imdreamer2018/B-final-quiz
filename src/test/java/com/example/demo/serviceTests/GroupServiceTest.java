package com.example.demo.serviceTests;

import com.example.demo.repository.GroupRepository;
import com.example.demo.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    private GroupService groupService;

    @Mock
    GroupRepository groupRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        groupService = new GroupService(groupRepository);
    }
}
