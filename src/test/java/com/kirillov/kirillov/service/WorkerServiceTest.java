package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.Role;
import com.kirillov.kirillov.model.Worker;
import com.kirillov.kirillov.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkerServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private WorkerService workerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllWorkers_ShouldReturnWorkerList() {
        workerService.getAllWorkers();
        verify(workerRepository).findAll();
    }

    @Test
    void saveWorker_ShouldSaveWorker() {
        Worker worker = new Worker("Ivanov", "Engineer", Role.USER, "ivanov", "password123");
        workerService.saveWorker(worker);
        verify(workerRepository).save(worker);
    }

    @Test
    void getWorkerById_ShouldReturnWorker() {
        Worker worker = new Worker("Petrov", "Viewer", Role.VIEWER, "petrov", "pass456");
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));

        Optional<Worker> foundWorker = workerService.getWorkerById(1L);

        assertTrue(foundWorker.isPresent());
        assertEquals("Petrov", foundWorker.get().getLastName());
    }

    @Test
    void deleteWorkerById_ShouldDeleteWorker() {
        Long id = 1L;
        workerService.deleteWorkerById(id);
        verify(workerRepository).deleteById(id);
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails() {
        Worker worker = new Worker("Sidorov", "Admin", Role.ADMIN, "sidorov", "{noop}adminPass");
        when(workerRepository.findByLogin("sidorov")).thenReturn(Optional.of(worker));

        var userDetails = workerService.loadUserByUsername("sidorov");

        assertEquals("sidorov", userDetails.getUsername());
        assertEquals("{noop}adminPass", userDetails.getPassword());
    }

    @Test
    void loadUserByUsername_ShouldThrowExceptionIfNotFound() {
        when(workerRepository.findByLogin("unknown")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> workerService.loadUserByUsername("unknown"));
    }
}
