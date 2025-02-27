package com.kirillov.kirillov.model;

import jakarta.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rstatus", nullable = false)
    private String rstatus;

    @Column(name = "rstatcomment")
    private String rstatcomment;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRstatus() {
        return rstatus;
    }

    public void setRstatus(String rstatus) {
        this.rstatus = rstatus;
    }

    public String getRstatcomment() {
        return rstatcomment;
    }

    public void setRstatcomment(String rstatcomment) {
        this.rstatcomment = rstatcomment;
    }
}
