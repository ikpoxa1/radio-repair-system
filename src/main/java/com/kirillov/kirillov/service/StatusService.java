package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.Status;
import com.kirillov.kirillov.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public Optional<Status> getStatusById(Long id) {
        return statusRepository.findById(id);
    }

    public void deleteStatusById(Long id) {
        statusRepository.deleteById(id);
    }
}
