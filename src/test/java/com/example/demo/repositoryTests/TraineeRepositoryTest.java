package com.example.demo.repositoryTests;

import com.example.demo.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class TraineeRepositoryTest {

    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TestEntityManager entityManager;
}
