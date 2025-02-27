package com.kirillov.kirillov.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spare_part")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="part_name", nullable = false)
    private String partName;

    @Column(name="description")
    private String description;

    // Конструкторы
    public SparePart() {
    }

    public SparePart(String partName, String description) {
        this.partName = partName;
        this.description = description;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // Этот метод должен быть добавлен
        this.id = id;
    }


    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
