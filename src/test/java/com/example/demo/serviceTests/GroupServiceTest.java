package com.example.demo.serviceTests;

import com.example.demo.dto.Group;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    private GroupService groupService;

    @Mock
    GroupRepository groupRepository;

    @Mock
    TrainerRepository trainerRepository;

    @Mock
    TraineeRepository traineeRepository;

    private List<TraineeEntity> traineeEntities = new ArrayList<>();

    private List<TrainerEntity> trainerEntities = new ArrayList<>();

    @BeforeEach
    void setUp() {
        initMocks(this);
        groupService = new GroupService(groupRepository, traineeRepository, trainerRepository);

        for (int i = 0; i < 8; i++) {
            traineeEntities.add(TraineeEntity.builder()
                    .name("mock name"+ i)
                    .grouped(false)
                    .build());
        }
        for (int i = 0; i < 8; i++) {
            trainerEntities.add(TrainerEntity.builder()
                    .name("mock trainer name"+ i)
                    .grouped(false)
                    .build());
        }
    }

    @Nested
    class CreateGroups {

        @Test
        void should_return_groups_info() {
            when(traineeRepository.findAllAndGroupedIsFalse(0)).thenReturn(traineeEntities);
            when(trainerRepository.findAllAndGroupedIsFalse(0)).thenReturn(trainerEntities);
            List<Group> groups = groupService.autoGrouping();
            assertEquals(4, groups.size());
        }
    }
}
