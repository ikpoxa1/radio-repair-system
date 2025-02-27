package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.Worker;
import com.kirillov.kirillov.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class WorkerDetailsService implements UserDetailsService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerDetailsService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Worker worker = workerRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

        return User.builder()
                .username(worker.getLogin())
                .password(worker.getPassword())
                .roles(worker.getRole().name())  // используем роль из БД
                .build();
    }
}
