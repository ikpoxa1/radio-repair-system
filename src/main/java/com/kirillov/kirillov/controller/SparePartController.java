package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.SparePart;
import com.kirillov.kirillov.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/spare-parts")
public class SparePartController {

    private final SparePartService sparePartService;

    @Autowired
    public SparePartController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    @GetMapping
    public String getAllSpareParts(Model model) {
        model.addAttribute("spareParts", sparePartService.getAllSpareParts());
        return "spare-parts/list";
    }

    @GetMapping("/new")
    public String createSparePartForm(Model model) {
        model.addAttribute("sparePart", new SparePart());
        return "spare-parts/create";
    }

    @PostMapping
    public String saveSparePart(@ModelAttribute("sparePart") SparePart sparePart) {
        sparePartService.saveSparePart(sparePart);
        return "redirect:/spare-parts";
    }

    @GetMapping("/edit/{id}")
    public String editSparePartForm(@PathVariable Long id, Model model) {
        Optional<SparePart> sparePart = sparePartService.getSparePartById(id);
        sparePart.ifPresent(sp -> model.addAttribute("sparePart", sp));
        return "spare-parts/edit";
    }

    @PostMapping("/{id}")
    public String updateSparePart(@PathVariable Long id, @ModelAttribute("sparePart") SparePart sparePart) {
        sparePart.setId(id);
        sparePartService.saveSparePart(sparePart);
        return "redirect:/spare-parts";
    }

    @GetMapping("/delete/{id}")
    public String deleteSparePart(@PathVariable Long id) {
        sparePartService.deleteSparePartById(id);
        return "redirect:/spare-parts";
    }
}
