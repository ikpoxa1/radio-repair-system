package com.kirillov.kirillov.repository;

import com.kirillov.kirillov.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    // Метод для поиска работника по логину
    Optional<Worker> findByLogin(String login);
}
