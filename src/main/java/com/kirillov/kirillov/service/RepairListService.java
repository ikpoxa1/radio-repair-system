package com.kirillov.kirillov.service;

import com.kirillov.kirillov.model.RepairList;
import com.kirillov.kirillov.repository.RepairListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class RepairListService {

    private final RepairListRepository repairListRepository;

    @Autowired
    public RepairListService(RepairListRepository repairListRepository) {
        this.repairListRepository = repairListRepository;
    }

    public Page<RepairList> getAllRepairs(Pageable pageable) {
        return repairListRepository.findAll(pageable);
    }

    public RepairList saveRepair(RepairList repairList) {
        return repairListRepository.save(repairList);
    }

    public Optional<RepairList> getRepairById(Long id) {
        return repairListRepository.findById(id);
    }

    public void deleteRepairById(Long id) {
        repairListRepository.deleteById(id);
    }

    public Page<RepairList> searchRepairs(String query, Pageable pageable) {
        // Попытка преобразовать строку query в LocalDateTime для поиска по statusTimestamp
        LocalDateTime timestampQuery = null;
        try {
            timestampQuery = LocalDateTime.parse(query);
        } catch (DateTimeParseException ignored) {}

        // Если query содержит дату, ищем по статусу времени, иначе ищем по текстовым полям
        if (timestampQuery != null) {
            return repairListRepository.findByStatusTimestampBetween(
                    timestampQuery.withHour(0).withMinute(0).withSecond(0),
                    timestampQuery.withHour(23).withMinute(59).withSecond(59),
                    pageable
            );
        } else {
            return repairListRepository.findByInventoryNumberContainingIgnoreCaseOrFactoryNumberContainingIgnoreCaseOrRadioType_TypeNameContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCaseOrWorker_LastNameContainingIgnoreCaseOrSparePart_PartNameContainingIgnoreCaseOrStatus_RstatusContainingIgnoreCase(
                    query, query, query, query, query, query, query, pageable
            );
        }
    }

    public Page<RepairList> searchByInventoryNumber(String inventoryNumber, Pageable pageable) {
        return repairListRepository.findByInventoryNumberContainingIgnoreCase(inventoryNumber, pageable);
    }

    public Page<RepairList> searchByFactoryNumber(String factoryNumber, Pageable pageable) {
        return repairListRepository.findByFactoryNumberContainingIgnoreCase(factoryNumber, pageable);
    }

    public Page<RepairList> searchByRadioType(String radioType, Pageable pageable) {
        return repairListRepository.findByRadioType_TypeNameContainingIgnoreCase(radioType, pageable);
    }

    public Page<RepairList> searchByDepartment(String department, Pageable pageable) {
        return repairListRepository.findByDepartment_DepartmentNameContainingIgnoreCase(department, pageable);
    }

    public Page<RepairList> searchByWorker(String worker, Pageable pageable) {
        return repairListRepository.findByWorker_LastNameContainingIgnoreCase(worker, pageable);
    }

    public Page<RepairList> searchBySparePart(String sparePart, Pageable pageable) {
        return repairListRepository.findBySparePart_PartNameContainingIgnoreCase(sparePart, pageable);
    }

    public Page<RepairList> searchByStatus(String status, Pageable pageable) {
        return repairListRepository.findByStatus_RstatusContainingIgnoreCase(status, pageable);
    }
}
