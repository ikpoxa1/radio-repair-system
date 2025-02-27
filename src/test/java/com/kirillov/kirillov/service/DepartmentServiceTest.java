package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.Department;
import com.kirillov.kirillov.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDepartments_ShouldReturnList() {
        departmentService.getAllDepartments();
        verify(departmentRepository).findAll();
    }

    @Test
    void saveDepartment_ShouldSaveDepartment() {
        Department department = new Department("Department", "Address");
        when(departmentRepository.save(department)).thenReturn(department);

        Department savedDepartment = departmentService.saveDepartment(department);

        verify(departmentRepository).save(department);
        assertEquals(department, savedDepartment);
    }

    @Test
    void getDepartmentById_ShouldReturnDepartment() {
        Department department = new Department("New Department", "123 Address St.");
        department.setId(1L);
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> retrievedDepartment = departmentService.getDepartmentById(1L);

        assertTrue(retrievedDepartment.isPresent());
        assertEquals(department.getId(), retrievedDepartment.get().getId());
        assertEquals(department.getDepartmentName(), retrievedDepartment.get().getDepartmentName());
        assertEquals(department.getAddress(), retrievedDepartment.get().getAddress());
    }

    @Test
    void deleteDepartmentById_ShouldDeleteDepartment() {
        Long id = 1L;
        departmentService.deleteDepartmentById(id);
        verify(departmentRepository).deleteById(id);
    }
}
