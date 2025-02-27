package com.kirillov.kirillov.repository;

import com.kirillov.kirillov.model.SparePart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SparePartRepositoryTest {

    @Autowired
    private SparePartRepository sparePartRepository;

    @Test
    void saveAndRetrieveSparePart() {
        SparePart sparePart = new SparePart("New Spare Part", "Spare Part Description");
        SparePart savedSparePart = sparePartRepository.save(sparePart);

        assertNotNull(savedSparePart.getId());
        assertEquals("New Spare Part", savedSparePart.getPartName());
    }

    @Test
    void findSparePartById_ShouldReturnSparePart() {
        SparePart sparePart = new SparePart("New Spare Part", "Description");
        SparePart savedSparePart = sparePartRepository.save(sparePart);

        Optional<SparePart> retrievedSparePart = sparePartRepository.findById(savedSparePart.getId());
        assertTrue(retrievedSparePart.isPresent());
        assertEquals(savedSparePart.getPartName(), retrievedSparePart.get().getPartName());
    }
}
