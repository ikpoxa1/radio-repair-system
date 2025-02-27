package com.kirillov.kirillov.repository;

import com.kirillov.kirillov.model.Role;
import com.kirillov.kirillov.model.Worker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class WorkerRepositoryTest {

    @Autowired
    private WorkerRepository workerRepository;

    @Test
    void saveAndRetrieveWorker() {
        Worker worker = new Worker("Ivanov", "Engineer", Role.USER, "ivanov_login", "{noop}password");
        Worker savedWorker = workerRepository.save(worker);

        Optional<Worker> retrievedWorker = workerRepository.findById(savedWorker.getId());
        assertTrue(retrievedWorker.isPresent());
        assertEquals("Ivanov", retrievedWorker.get().getLastName());
    }

    @Test
    void findByLogin_ShouldReturnWorker() {
        Worker worker = new Worker("Petrov", "Technician", Role.USER, "petrov_login", "{noop}pass123");
        workerRepository.save(worker);

        Optional<Worker> foundWorker = workerRepository.findByLogin("petrov_login");
        assertTrue(foundWorker.isPresent());
        assertEquals("petrov_login", foundWorker.get().getLogin());
    }
}
