package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.Worker;
import com.kirillov.kirillov.repository.WorkerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final WorkerRepository workerRepository;

    public CustomUserDetailsService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Worker worker = workerRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

        // Добавляем {noop} только если пароль не содержит префикса
        String passwordWithPrefix = worker.getPassword().startsWith("{noop}")
                ? worker.getPassword()
                : "{noop}" + worker.getPassword();

        return new org.springframework.security.core.userdetails.User(
                worker.getLogin(),
                passwordWithPrefix,
                Collections.singletonList(new SimpleGrantedAuthority(worker.getRole().getAuthorityName()))
        );
    }
}
