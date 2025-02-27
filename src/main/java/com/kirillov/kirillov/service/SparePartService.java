package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.SparePart;
import com.kirillov.kirillov.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SparePartService {

    private final SparePartRepository sparePartRepository;

    @Autowired
    public SparePartService(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    public List<SparePart> getAllSpareParts() {
        return sparePartRepository.findAll();
    }

    public SparePart saveSparePart(SparePart sparePart) {
        return sparePartRepository.save(sparePart);
    }

    public Optional<SparePart> getSparePartById(Long id) {
        return sparePartRepository.findById(id);
    }

    public void deleteSparePartById(Long id) {
        sparePartRepository.deleteById(id);
    }
}
