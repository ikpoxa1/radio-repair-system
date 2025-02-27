package com.kirillov.kirillov.model;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="department_name", nullable = false)
    private String departmentName;

    @Column(name="address", nullable = false)
    private String address;

    // Конструкторы
    public Department() {
    }

    public Department(String departmentName, String address) {
        this.departmentName = departmentName;
        this.address = address;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // Этот метод должен быть добавлен
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
