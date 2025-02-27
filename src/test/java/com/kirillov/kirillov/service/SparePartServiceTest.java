package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.SparePart;
import com.kirillov.kirillov.repository.SparePartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SparePartServiceTest {

    @Mock
    private SparePartRepository sparePartRepository;

    @InjectMocks
    private SparePartService sparePartService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSpareParts_ShouldReturnList() {
        sparePartService.getAllSpareParts();
        verify(sparePartRepository).findAll();
    }

    @Test
    void saveSparePart_ShouldSaveSparePart() {
        SparePart sparePart = new SparePart("Part Name", "Description");
        sparePartService.saveSparePart(sparePart);
        verify(sparePartRepository).save(sparePart);
    }

    @Test
    void getSparePartById_ShouldReturnSparePart() {
        SparePart sparePart = new SparePart("Part Name", "Description");
        when(sparePartRepository.findById(1L)).thenReturn(Optional.of(sparePart));

        Optional<SparePart> retrievedSparePart = sparePartService.getSparePartById(1L);
        assertTrue(retrievedSparePart.isPresent());
        assertEquals("Part Name", retrievedSparePart.get().getPartName());
    }

    @Test
    void deleteSparePartById_ShouldDeleteSparePart() {
        Long id = 1L;
        sparePartService.deleteSparePartById(id);
        verify(sparePartRepository).deleteById(id);
    }
}
