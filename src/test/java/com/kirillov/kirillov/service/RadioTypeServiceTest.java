package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.RadioType;
import com.kirillov.kirillov.repository.RadioTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RadioTypeServiceTest {

    @Mock
    private RadioTypeRepository radioTypeRepository;

    @InjectMocks
    private RadioTypeService radioTypeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRadioType_ShouldSaveRadioType() {
        RadioType radioType = new RadioType("Type B", "Description B");
        radioTypeService.saveRadioType(radioType);
        verify(radioTypeRepository).save(radioType);
    }

    @Test
    void getRadioTypeById_ShouldReturnRadioType() {
        RadioType radioType = new RadioType("Type C", "Description C");
        when(radioTypeRepository.findById(1L)).thenReturn(Optional.of(radioType));

        Optional<RadioType> retrievedRadioType = radioTypeService.getRadioTypeById(1L);
        assertTrue(retrievedRadioType.isPresent());
        assertEquals("Type C", retrievedRadioType.get().getTypeName());
    }
}
