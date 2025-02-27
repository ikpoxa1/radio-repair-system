package com.kirillov.kirillov.repository;

import com.kirillov.kirillov.model.RadioType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class RadioTypeRepositoryTest {

    @Autowired
    private RadioTypeRepository radioTypeRepository;

    @Test
    void saveAndRetrieveRadioType() {
        RadioType radioType = new RadioType("Type A", "Description A");
        RadioType savedRadioType = radioTypeRepository.save(radioType);

        Optional<RadioType> retrievedRadioType = radioTypeRepository.findById(savedRadioType.getId());
        assertTrue(retrievedRadioType.isPresent());
        assertEquals("Type A", retrievedRadioType.get().getTypeName());
    }
}
