package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.Role;
import com.kirillov.kirillov.model.Worker;
import com.kirillov.kirillov.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/workers")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public String getAllWorkers(Model model) {
        model.addAttribute("workers", workerService.getAllWorkers());
        return "workers/list";
    }

    @GetMapping("/new")
    public String createWorkerForm(Model model) {
        model.addAttribute("worker", new Worker());
        model.addAttribute("roles", Role.values());
        return "workers/create";
    }

    @PostMapping
    public String saveWorker(@ModelAttribute("worker") Worker worker) {
        workerService.saveWorker(worker);
        return "redirect:/workers";
    }

    @GetMapping("/edit/{id}")
    public String editWorkerForm(@PathVariable Long id, Model model) {
        Optional<Worker> worker = workerService.getWorkerById(id);
        worker.ifPresent(w -> model.addAttribute("worker", w));
        model.addAttribute("roles", Role.values());
        return "workers/edit";
    }

    @PostMapping("/{id}")
    public String updateWorker(@PathVariable Long id, @ModelAttribute("worker") Worker worker) {
        worker.setId(id);
        workerService.saveWorker(worker);
        return "redirect:/workers";
    }

    @GetMapping("/delete/{id}")
    public String deleteWorker(@PathVariable Long id) {
        workerService.deleteWorkerById(id);
        return "redirect:/workers";
    }
}
