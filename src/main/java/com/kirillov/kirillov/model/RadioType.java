package com.kirillov.kirillov.model;

import jakarta.persistence.*;

@Entity
@Table(name = "radio_type")
public class RadioType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_name", nullable = false)
    private String typeName;

    @Column(name="description")
    private String description;

    // Конструкторы
    public RadioType() {
    }

    public RadioType(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // Этот метод должен быть добавлен
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
