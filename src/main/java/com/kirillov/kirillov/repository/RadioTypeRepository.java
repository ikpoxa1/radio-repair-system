package com.kirillov.kirillov.repository;

import com.kirillov.kirillov.model.RadioType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadioTypeRepository extends JpaRepository<RadioType, Long> {
}
