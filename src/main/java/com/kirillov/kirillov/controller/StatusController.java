package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.Status;
import com.kirillov.kirillov.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/statuses")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public String getAllStatuses(Model model) {
        model.addAttribute("statuses", statusService.getAllStatuses());
        return "statuses/list";
    }

    @GetMapping("/new")
    public String createStatusForm(Model model) {
        model.addAttribute("status", new Status());
        return "statuses/create";
    }

    @PostMapping
    public String saveStatus(@ModelAttribute("status") Status status) {
        statusService.saveStatus(status);
        return "redirect:/statuses";
    }

    @GetMapping("/edit/{id}")
    public String editStatusForm(@PathVariable Long id, Model model) {
        Optional<Status> status = statusService.getStatusById(id);
        if (status.isPresent()) {
            model.addAttribute("status", status.get());
            return "statuses/edit";
        }
        return "redirect:/statuses";
    }

    @PostMapping("/{id}")
    public String updateStatus(@PathVariable Long id, @ModelAttribute("status") Status status) {
        status.setId(id);
        statusService.saveStatus(status);
        return "redirect:/statuses";
    }

    @GetMapping("/delete/{id}")
    public String deleteStatus(@PathVariable Long id) {
        statusService.deleteStatusById(id);
        return "redirect:/statuses";
    }
}
