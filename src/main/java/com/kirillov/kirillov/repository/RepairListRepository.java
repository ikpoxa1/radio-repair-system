package com.kirillov.kirillov.repository;

import com.kirillov.kirillov.model.RepairList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RepairListRepository extends JpaRepository<RepairList, Long> {

    // Поиск по всем полям кроме `statusTimestamp` с поддержкой пейджинга и сортировки
    Page<RepairList> findByInventoryNumberContainingIgnoreCaseOrFactoryNumberContainingIgnoreCaseOrRadioType_TypeNameContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCaseOrWorker_LastNameContainingIgnoreCaseOrSparePart_PartNameContainingIgnoreCaseOrStatus_RstatusContainingIgnoreCase(
            String inventoryNumber,
            String factoryNumber,
            String radioTypeName,
            String departmentName,
            String workerLastName,
            String sparePartName,
            String status,
            Pageable pageable
    );

    // Поиск по отдельным полям с поддержкой пейджинга и сортировки
    Page<RepairList> findByInventoryNumberContainingIgnoreCase(String inventoryNumber, Pageable pageable);
    Page<RepairList> findByFactoryNumberContainingIgnoreCase(String factoryNumber, Pageable pageable);
    Page<RepairList> findByRadioType_TypeNameContainingIgnoreCase(String radioTypeName, Pageable pageable);
    Page<RepairList> findByDepartment_DepartmentNameContainingIgnoreCase(String departmentName, Pageable pageable);
    Page<RepairList> findByWorker_LastNameContainingIgnoreCase(String workerLastName, Pageable pageable);
    Page<RepairList> findBySparePart_PartNameContainingIgnoreCase(String sparePartName, Pageable pageable);
    Page<RepairList> findByStatus_RstatusContainingIgnoreCase(String status, Pageable pageable);

    // Поиск по точному совпадению `statusTimestamp`
    Page<RepairList> findByStatusTimestamp(LocalDateTime statusTimestamp, Pageable pageable);

    // Поиск по диапазону `statusTimestamp`
    Page<RepairList> findByStatusTimestampBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
