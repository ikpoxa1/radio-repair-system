package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.RadioType;
import com.kirillov.kirillov.service.RadioTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/radio-types")
public class RadioTypeController {

    private final RadioTypeService radioTypeService;

    @Autowired
    public RadioTypeController(RadioTypeService radioTypeService) {
        this.radioTypeService = radioTypeService;
    }

    @GetMapping
    public String getAllRadioTypes(Model model) {
        model.addAttribute("radioTypes", radioTypeService.getAllRadioTypes());
        return "radio-types/list";
    }

    @GetMapping("/new")
    public String createRadioTypeForm(Model model) {
        model.addAttribute("radioType", new RadioType());
        return "radio-types/create";
    }

    @PostMapping
    public String saveRadioType(@ModelAttribute("radioType") RadioType radioType) {
        radioTypeService.saveRadioType(radioType);
        return "redirect:/radio-types";
    }

    @GetMapping("/edit/{id}")
    public String editRadioTypeForm(@PathVariable Long id, Model model) {
        Optional<RadioType> radioType = radioTypeService.getRadioTypeById(id);
        radioType.ifPresent(rt -> model.addAttribute("radioType", rt));
        return "radio-types/edit";
    }

    @PostMapping("/{id}")
    public String updateRadioType(@PathVariable Long id, @ModelAttribute("radioType") RadioType radioType) {
        radioType.setId(id);
        radioTypeService.saveRadioType(radioType);
        return "redirect:/radio-types";
    }

    @GetMapping("/delete/{id}")
    public String deleteRadioType(@PathVariable Long id) {
        radioTypeService.deleteRadioTypeById(id);
        return "redirect:/radio-types";
    }
}
