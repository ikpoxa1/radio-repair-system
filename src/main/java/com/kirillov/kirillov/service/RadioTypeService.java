package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.RadioType;
import com.kirillov.kirillov.repository.RadioTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RadioTypeService {

    private final RadioTypeRepository radioTypeRepository;

    @Autowired
    public RadioTypeService(RadioTypeRepository radioTypeRepository) {
        this.radioTypeRepository = radioTypeRepository;
    }

    public List<RadioType> getAllRadioTypes() {
        return radioTypeRepository.findAll();
    }

    public RadioType saveRadioType(RadioType radioType) {
        return radioTypeRepository.save(radioType);
    }

    public Optional<RadioType> getRadioTypeById(Long id) {
        return radioTypeRepository.findById(id);
    }

    public void deleteRadioTypeById(Long id) {
        radioTypeRepository.deleteById(id);
    }
}
