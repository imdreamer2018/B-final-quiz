package com.example.demo.repositoryTests;

import com.example.demo.entity.GroupEntity;
import com.example.demo.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private TestEntityManager entityManager;

    private GroupEntity groupEntity;


    @BeforeEach
    void setUp() {
    }
}
