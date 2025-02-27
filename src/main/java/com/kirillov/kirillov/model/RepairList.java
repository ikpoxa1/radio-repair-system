package com.kirillov.kirillov.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "repair_list")
public class RepairList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inventory_number", nullable = false)
    private String inventoryNumber;

    @Column(name = "factory_number", nullable = false)
    private String factoryNumber;

    // Связь с RadioType
    @ManyToOne
    @JoinColumn(name = "radio_type_id", nullable = false)
    private RadioType radioType;

    // Связь с Department
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // Связь с Worker
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    // Связь с SparePart
    @ManyToOne
    @JoinColumn(name = "spare_part_id", nullable = false)
    private SparePart sparePart;

    // Связь с Status
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    // Поле для хранения времени изменения статуса
    @Column(name = "status_timestamp", nullable = false)
    private LocalDateTime statusTimestamp;

    public RepairList() {
        this.statusTimestamp = LocalDateTime.now();
    }

    public RepairList(String inventoryNumber, String factoryNumber, RadioType radioType, Department department, Worker worker, SparePart sparePart, Status status) {
        this.inventoryNumber = inventoryNumber;
        this.factoryNumber = factoryNumber;
        this.radioType = radioType;
        this.department = department;
        this.worker = worker;
        this.sparePart = sparePart;
        this.status = status;
        this.statusTimestamp = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public RadioType getRadioType() {
        return radioType;
    }

    public void setRadioType(RadioType radioType) {
        this.radioType = radioType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public SparePart getSparePart() {
        return sparePart;
    }

    public void setSparePart(SparePart sparePart) {
        this.sparePart = sparePart;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.statusTimestamp = LocalDateTime.now(); // Обновляем время при изменении статуса
    }

    public LocalDateTime getStatusTimestamp() {
        return statusTimestamp;
    }

    public void setStatusTimestamp(LocalDateTime statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
    }

    // Метод для форматированного вывода времени статуса
    public String getFormattedStatusTimestamp() {
        return statusTimestamp != null ? statusTimestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "";
    }
}
