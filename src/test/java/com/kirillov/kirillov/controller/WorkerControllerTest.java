package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.Role;
import com.kirillov.kirillov.model.Worker;
import com.kirillov.kirillov.service.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WorkerControllerTest {

    @Mock
    private WorkerService workerService;

    @Mock
    private Model model;

    @InjectMocks
    private WorkerController workerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllWorkers_ShouldReturnWorkerListPage() {
        String view = workerController.getAllWorkers(model);
        verify(workerService).getAllWorkers();
        assertEquals("workers/list", view);
    }

    @Test
    void createWorkerForm_ShouldReturnCreatePage() {
        String view = workerController.createWorkerForm(model);
        verify(model).addAttribute(eq("worker"), any(Worker.class));
        verify(model).addAttribute("roles", Role.values());
        assertEquals("workers/create", view);
    }

    @Test
    void saveWorker_ShouldRedirectToWorkersPage() {
        Worker worker = new Worker("Ivanov", "Engineer", Role.USER, "ivanov", "password123");
        String view = workerController.saveWorker(worker);
        verify(workerService).saveWorker(worker);
        assertEquals("redirect:/workers", view);
    }

    @Test
    void editWorkerForm_ShouldReturnEditPageIfWorkerExists() {
        Worker worker = new Worker("Petrov", "Viewer", Role.VIEWER, "petrov", "pass456");
        when(workerService.getWorkerById(1L)).thenReturn(Optional.of(worker));

        String view = workerController.editWorkerForm(1L, model);

        verify(workerService).getWorkerById(1L);
        verify(model).addAttribute("worker", worker);
        verify(model).addAttribute("roles", Role.values());
        assertEquals("workers/edit", view);
    }

    @Test
    void deleteWorker_ShouldRedirectToWorkersPage() {
        String view = workerController.deleteWorker(1L);
        verify(workerService).deleteWorkerById(1L);
        assertEquals("redirect:/workers", view);
    }
}
