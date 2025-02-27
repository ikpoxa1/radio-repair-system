package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.Department;
import com.kirillov.kirillov.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Отображение списка подразделений
    @GetMapping
    public String getAllDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "departments/list";  // Переход к шаблону list.html
    }

    // Переход на страницу создания подразделения
    @GetMapping("/new")
    public String createDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments/create";  // Переход к шаблону create.html
    }

    // Сохранение нового подразделения
    @PostMapping
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    // Переход на страницу редактирования подразделения
    @GetMapping("/edit/{id}")
    public String editDepartmentForm(@PathVariable Long id, Model model) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        department.ifPresent(d -> model.addAttribute("department", d));
        return "departments/edit";  // Переход к шаблону edit.html
    }

    // Обновление существующего подразделения
    @PostMapping("/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("department") Department department) {
        department.setId(id);
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    // Удаление подразделения
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}
