package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.SparePart;
import com.kirillov.kirillov.service.SparePartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SparePartControllerTest {

    @Mock
    private SparePartService sparePartService;

    @Mock
    private Model model;

    @InjectMocks
    private SparePartController sparePartController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSpareParts_ShouldReturnListPage() {
        String view = sparePartController.getAllSpareParts(model);
        verify(sparePartService).getAllSpareParts();
        assertEquals("spare-parts/list", view);
    }

    @Test
    void createSparePartForm_ShouldReturnCreatePage() {
        String view = sparePartController.createSparePartForm(model);
        verify(model).addAttribute(eq("sparePart"), any(SparePart.class));
        assertEquals("spare-parts/create", view);
    }

    @Test
    void saveSparePart_ShouldRedirectToList() {
        SparePart sparePart = new SparePart("Part Name", "Description");
        String view = sparePartController.saveSparePart(sparePart);
        verify(sparePartService).saveSparePart(sparePart);
        assertEquals("redirect:/spare-parts", view);
    }

    @Test
    void editSparePartForm_ShouldReturnEditPage() {
        Long id = 1L;
        SparePart sparePart = new SparePart("Part Name", "Description");
        when(sparePartService.getSparePartById(id)).thenReturn(Optional.of(sparePart));

        String view = sparePartController.editSparePartForm(id, model);
        verify(model).addAttribute("sparePart", sparePart);
        assertEquals("spare-parts/edit", view);
    }

    @Test
    void deleteSparePart_ShouldRedirectToList() {
        Long id = 1L;
        String view = sparePartController.deleteSparePart(id);
        verify(sparePartService).deleteSparePartById(id);
        assertEquals("redirect:/spare-parts", view);
    }
}
