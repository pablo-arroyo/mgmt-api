package com.university.mgmtapi.controllers;

import com.university.mgmtapi.dto.requests.ProfessorDTO;
import com.university.mgmtapi.entities.Professor;
import com.university.mgmtapi.repositories.DepartmentRepository;
import com.university.mgmtapi.repositories.ProfessorRepository;
import com.university.mgmtapi.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorRepository professorRepository;

    private final DepartmentRepository departmentRepository;

    private final RequestService requestService;

    public ProfessorController(ProfessorRepository professorRepository,
                               DepartmentRepository departmentRepository,
                               RequestService requestService) {
        this.professorRepository = professorRepository;
        this.departmentRepository = departmentRepository;
        this.requestService = requestService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        var professors = new ArrayList<Professor>();
        professorRepository.findAll().forEach(professors::add);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professors);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Professor> getProfessor(
            @PathVariable Long id
    ) {
        if (id != null && id > 0) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(professorRepository.findById(id)
                            .orElse(null));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<String> createProfessor(
            @RequestBody ProfessorDTO professorDTO
    ) {
        if (requestService.isValid(professorDTO)
                && professorDTO.getDepartmentId() > 0) {
            var department = departmentRepository
                    .findById(professorDTO.getDepartmentId())
                    .orElse(null);

            if (department == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No department was found with the specified 'departmentId'");
            }

            var professor = new Professor();
            professor.setName(professorDTO.getName());
            professor.setDepartment(department);
            professorRepository.save(professor);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please include 'name' & 'departmentId' in your request payload");
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteProfessor(
            @PathVariable Long id
    ) {
        if (id != null && id > 0) {
            professorRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please include an 'id' number property in your path");
    }
}
