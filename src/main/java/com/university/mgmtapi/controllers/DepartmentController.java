package com.university.mgmtapi.controllers;

import com.university.mgmtapi.dto.requests.DepartmentDTO;
import com.university.mgmtapi.entities.Department;
import com.university.mgmtapi.repositories.DepartmentRepository;
import com.university.mgmtapi.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    private final RequestService requestService;

    public DepartmentController(DepartmentRepository departmentRepository,
                                RequestService requestService) {
        this.departmentRepository = departmentRepository;
        this.requestService = requestService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        var departments = new ArrayList<Department>();
        departmentRepository.findAll().forEach(departments::add);

        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(departments);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Department> getDepartment(
            @PathVariable Long id
    ) {
        if (id != null && id > 0) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(departmentRepository.findById(id)
                            .orElse(null));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(
            @RequestBody DepartmentDTO departmentDTO
    ) {
        if (requestService.isValid(departmentDTO)) {
            departmentRepository.save(
                    new Department(departmentDTO.getName())
            );

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please include 'name' property with value");
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Long id
    ) {
        if (id != null && id > 0) {
            departmentRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please include an 'id' number property in your path");
    }
}
