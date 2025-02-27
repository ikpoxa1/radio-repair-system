package com.kirillov.kirillov.repository;

import com.kirillov.kirillov.model.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertNotNull(departmentRepository);
    }

    @Test
    void saveAndRetrieveDepartment() {
        Department department = new Department("New Department", "123 Address St.");
        Department savedDepartment = departmentRepository.save(department);
        assertNotNull(departmentRepository.findById(savedDepartment.getId()));
    }
}
