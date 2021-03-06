package com.example.demo.service;

import com.example.demo.dto.Group;
import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private static final String GROUP_NAME = "组";
    private static final int GROUP_TRAINER_NUM = 2;

    private final GroupRepository groupRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    // TODO GTB-工程实践: - 过长的代码，应该及时换行
    public GroupService(GroupRepository groupRepository, TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.groupRepository = groupRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    // TODO GTB-工程实践: - 长方法，可以抽成多个方法提高可读性
    public List<Group> autoGrouping() {
        groupRepository.deleteAll();
        List<TrainerEntity> trainerEntities = trainerRepository.findAll();
        List<TraineeEntity> traineeEntities = traineeRepository.findAll();
        if (trainerEntities.size() < GROUP_TRAINER_NUM)
            throw new BadRequestException("trainer num less than 2!");
        Collections.shuffle(traineeEntities);
        Collections.shuffle(trainerEntities);

        List<GroupEntity> groupEntities = new ArrayList<>();
        AtomicLong groupIndex = new AtomicLong(0L);
        int groupNum = trainerEntities.size() / GROUP_TRAINER_NUM;

        for (TraineeEntity traineeEntity: traineeEntities) {
            List<TrainerEntity> trainers = new ArrayList<>();
            List<TraineeEntity> trainees = new ArrayList<>();
            GroupEntity groupEntity = new GroupEntity(groupIndex.get(), groupIndex.get() + GROUP_NAME, trainers, trainees);
            if (groupEntities.size() < groupNum) {
                groupEntities.add(groupEntity);
            }
            traineeEntity.setGrouped(true);
            traineeRepository.save(traineeEntity);
            groupEntities.get(Math.toIntExact(groupIndex.get())).setId(groupIndex.get() + 1);
            groupEntities.get(Math.toIntExact(groupIndex.get())).setName(groupIndex.get() + 1 + GROUP_NAME);
            groupEntities.get(Math.toIntExact(groupIndex.get())).getTrainees().add(traineeEntity);
            groupIndex.incrementAndGet();
            if (groupIndex.get() == groupNum)
                groupIndex.set(0L);
        }

        for (int i = 0; i < trainerEntities.size(); i += GROUP_TRAINER_NUM) {
            if (i + 1 < trainerEntities.size()) {
                trainerEntities.get(i).setGrouped(true);
                trainerRepository.save(trainerEntities.get(i));
                trainerEntities.get(i + 1).setGrouped(true);
                trainerRepository.save(trainerEntities.get(i + 1));
                groupEntities.get(i / GROUP_TRAINER_NUM).getTrainers().add(trainerEntities.get(i));
                groupEntities.get(i / GROUP_TRAINER_NUM).getTrainers().add(trainerEntities.get(i + 1));
            }
        }

        groupRepository.saveAll(groupEntities);
        return groupEntities.stream()
                .map(GroupEntity::toGroup).collect(Collectors.toList());
    }

    public List<Group> getGroups() {
        return groupRepository.findAll().stream().map(GroupEntity::toGroup).collect(Collectors.toList());
    }
}
