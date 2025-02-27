package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.RadioType;
import com.kirillov.kirillov.service.RadioTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RadioTypeControllerTest {

    @Mock
    private RadioTypeService radioTypeService;

    @Mock
    private Model model;

    @InjectMocks
    private RadioTypeController radioTypeController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRadioTypes_ShouldAddRadioTypesToModelAndReturnListView() {
        String viewName = radioTypeController.getAllRadioTypes(model);
        verify(radioTypeService).getAllRadioTypes();
        verify(model).addAttribute(eq("radioTypes"), anyList());
        assertEquals("radio-types/list", viewName);
    }

    @Test
    void createRadioTypeForm_ShouldAddNewRadioTypeToModelAndReturnCreateView() {
        String viewName = radioTypeController.createRadioTypeForm(model);
        verify(model).addAttribute(eq("radioType"), any(RadioType.class));
        assertEquals("radio-types/create", viewName);
    }

    @Test
    void saveRadioType_ShouldRedirectToRadioTypesList() {
        RadioType radioType = new RadioType("Type D", "Description D");
        String viewName = radioTypeController.saveRadioType(radioType);
        verify(radioTypeService).saveRadioType(radioType);
        assertEquals("redirect:/radio-types", viewName);
    }

    @Test
    void editRadioTypeForm_ShouldAddExistingRadioTypeToModelAndReturnEditView() {
        RadioType radioType = new RadioType("Type E", "Description E");
        when(radioTypeService.getRadioTypeById(1L)).thenReturn(Optional.of(radioType));

        String viewName = radioTypeController.editRadioTypeForm(1L, model);
        verify(model).addAttribute("radioType", radioType);
        assertEquals("radio-types/edit", viewName);
    }

    @Test
    void deleteRadioType_ShouldRedirectToRadioTypesList() {
        String viewName = radioTypeController.deleteRadioType(1L);
        verify(radioTypeService).deleteRadioTypeById(1L);
        assertEquals("redirect:/radio-types", viewName);
    }
}
