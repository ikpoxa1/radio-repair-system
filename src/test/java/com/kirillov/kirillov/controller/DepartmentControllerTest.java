package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.Department;
import com.kirillov.kirillov.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private Model model;

    @InjectMocks
    private DepartmentController departmentController;

    public DepartmentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDepartments_ShouldReturnListTemplate() {
        assertEquals("departments/list", departmentController.getAllDepartments(model));
        verify(departmentService).getAllDepartments();
    }

    @Test
    void createDepartmentForm_ShouldReturnCreateTemplate() {
        assertEquals("departments/create", departmentController.createDepartmentForm(model));
    }

    @Test
    void saveDepartment_ShouldRedirectToDepartments() {
        Department department = new Department();
        assertEquals("redirect:/departments", departmentController.saveDepartment(department));
        verify(departmentService).saveDepartment(department);
    }

    @Test
    void editDepartmentForm_ShouldReturnEditTemplate() {
        Long id = 1L;
        when(departmentService.getDepartmentById(id)).thenReturn(Optional.of(new Department()));
        assertEquals("departments/edit", departmentController.editDepartmentForm(id, model));
    }

    @Test
    void updateDepartment_ShouldRedirectToDepartments() {
        Department department = new Department();
        department.setId(1L);
        assertEquals("redirect:/departments", departmentController.updateDepartment(department.getId(), department));
        verify(departmentService).saveDepartment(department);
    }

    @Test
    void deleteDepartment_ShouldRedirectToDepartments() {
        Long id = 1L;
        assertEquals("redirect:/departments", departmentController.deleteDepartment(id));
        verify(departmentService).deleteDepartmentById(id);
    }
}
