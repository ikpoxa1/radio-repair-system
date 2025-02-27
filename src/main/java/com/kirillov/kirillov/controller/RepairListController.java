package com.kirillov.kirillov.controller;

import com.kirillov.kirillov.model.RepairList;
import com.kirillov.kirillov.model.Worker;
import com.kirillov.kirillov.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/repairs")
public class RepairListController {

    private final RepairListService repairListService;
    private final RadioTypeService radioTypeService;
    private final DepartmentService departmentService;
    private final WorkerService workerService;
    private final SparePartService sparePartService;
    private final StatusService statusService;

    @Autowired
    public RepairListController(RepairListService repairListService,
                                RadioTypeService radioTypeService,
                                DepartmentService departmentService,
                                WorkerService workerService,
                                SparePartService sparePartService,
                                StatusService statusService) {
        this.repairListService = repairListService;
        this.radioTypeService = radioTypeService;
        this.departmentService = departmentService;
        this.workerService = workerService;
        this.sparePartService = sparePartService;
        this.statusService = statusService;
    }

    @GetMapping
    public String getAllRepairs(@RequestParam(value = "query", required = false) String query,
                                @RequestParam(value = "filter", defaultValue = "all") String filter,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                @RequestParam(value = "sort", defaultValue = "id") String sort,
                                @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<RepairList> repairs;

        // Логика для поиска на основе фильтра
        if (query != null && !query.isEmpty()) {
            switch (filter) {
                case "inventoryNumber":
                    repairs = repairListService.searchByInventoryNumber(query, pageable);
                    break;
                case "factoryNumber":
                    repairs = repairListService.searchByFactoryNumber(query, pageable);
                    break;
                case "radioType":
                    repairs = repairListService.searchByRadioType(query, pageable);
                    break;
                case "department":
                    repairs = repairListService.searchByDepartment(query, pageable);
                    break;
                case "worker":
                    repairs = repairListService.searchByWorker(query, pageable);
                    break;
                case "sparePart":
                    repairs = repairListService.searchBySparePart(query, pageable);
                    break;
                case "status":
                    repairs = repairListService.searchByStatus(query, pageable);
                    break;
                case "pageNumber":
                    int pageNumber = Integer.parseInt(query) - 1;
                    return "redirect:/repairs?page=" + pageNumber + "&sort=" + sort + "&direction=" + direction + "&size=" + size;
                default:
                    repairs = repairListService.searchRepairs(query, pageable);
                    break;
            }
            model.addAttribute("query", query);
            model.addAttribute("filter", filter);
        } else {
            repairs = repairListService.getAllRepairs(pageable);
        }

        // Получение текущего аутентифицированного пользователя и поиск объекта Worker в базе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Worker> currentUserOpt = workerService.getWorkerByLogin(username);
        currentUserOpt.ifPresent(currentUser -> model.addAttribute("currentUser", currentUser));

        model.addAttribute("repairs", repairs);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", repairs.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);

        return "repairs/list";
    }


    @GetMapping("/new")
    public String createRepairForm(Model model) {
        model.addAttribute("repair", new RepairList());
        model.addAttribute("radioTypes", radioTypeService.getAllRadioTypes());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("workers", workerService.getAllWorkers());
        model.addAttribute("spareParts", sparePartService.getAllSpareParts());
        model.addAttribute("statuses", statusService.getAllStatuses());
        return "repairs/create";
    }

    @PostMapping
    public String saveRepair(@ModelAttribute("repair") RepairList repairList,
                             @RequestParam("radioType") Long radioTypeId,
                             @RequestParam("department") Long departmentId,
                             @RequestParam("worker") Long workerId,
                             @RequestParam("sparePart") Long sparePartId,
                             @RequestParam("status") Long statusId) {

        repairList.setRadioType(radioTypeService.getRadioTypeById(radioTypeId).orElse(null));
        repairList.setDepartment(departmentService.getDepartmentById(departmentId).orElse(null));
        repairList.setWorker(workerService.getWorkerById(workerId).orElse(null));
        repairList.setSparePart(sparePartService.getSparePartById(sparePartId).orElse(null));
        repairList.setStatus(statusService.getStatusById(statusId).orElse(null));

        repairListService.saveRepair(repairList);
        return "redirect:/repairs";
    }

    @GetMapping("/edit/{id}")
    public String editRepairForm(@PathVariable Long id, Model model) {
        Optional<RepairList> repair = repairListService.getRepairById(id);
        if (repair.isPresent()) {
            model.addAttribute("repair", repair.get());
            model.addAttribute("radioTypes", radioTypeService.getAllRadioTypes());
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("workers", workerService.getAllWorkers());
            model.addAttribute("spareParts", sparePartService.getAllSpareParts());
            model.addAttribute("statuses", statusService.getAllStatuses());
            return "repairs/edit";
        }
        return "redirect:/repairs";
    }

    @PostMapping("/{id}")
    public String updateRepair(@PathVariable Long id,
                               @ModelAttribute("repair") RepairList repair,
                               @RequestParam("radioType") Long radioTypeId,
                               @RequestParam("department") Long departmentId,
                               @RequestParam("worker") Long workerId,
                               @RequestParam("sparePart") Long sparePartId,
                               @RequestParam("status") Long statusId) {

        RepairList existingRepair = repairListService.getRepairById(id).orElse(null);
        if (existingRepair != null) {
            if (!existingRepair.getStatus().getId().equals(statusId)) {
                repair.setStatusTimestamp(java.time.LocalDateTime.now());
                repair.setStatus(statusService.getStatusById(statusId).orElse(null));
            } else {
                repair.setStatus(existingRepair.getStatus());
                repair.setStatusTimestamp(existingRepair.getStatusTimestamp());
            }

            repair.setId(id);
            repair.setRadioType(radioTypeService.getRadioTypeById(radioTypeId).orElse(null));
            repair.setDepartment(departmentService.getDepartmentById(departmentId).orElse(null));
            repair.setWorker(workerService.getWorkerById(workerId).orElse(null));
            repair.setSparePart(sparePartService.getSparePartById(sparePartId).orElse(null));

            repairListService.saveRepair(repair);
        }
        return "redirect:/repairs";
    }

    @GetMapping("/delete/{id}")
    public String deleteRepair(@PathVariable Long id) {
        repairListService.deleteRepairById(id);
        return "redirect:/repairs";
    }
}
