package com.university.mgmtapi.controllers;

import com.university.mgmtapi.dto.requests.CourseDTO;
import com.university.mgmtapi.entities.Course;
import com.university.mgmtapi.repositories.CourseRepository;
import com.university.mgmtapi.repositories.DepartmentRepository;
import com.university.mgmtapi.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    private final CourseRepository courseRepository;

    private final DepartmentRepository departmentRepository;

    private final RequestService requestService;

    public CourseController(CourseRepository courseRepository,
                            DepartmentRepository departmentRepository,
                            RequestService requestService) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.requestService = requestService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        var courses = new ArrayList<Course>();
        courseRepository.findAll().forEach(courses::add);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courses);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Course> getCourse(
            @PathVariable Long id
    ) {
        if (id != null && id > 0) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(courseRepository.findById(id)
                            .orElse(null));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<String> createCourse(
            @RequestBody CourseDTO courseDTO
    ) {
        if (requestService.isValid(courseDTO)
                && courseDTO.getDepartmentId() > 0) {
            var department = departmentRepository
                    .findById(courseDTO.getDepartmentId())
                    .orElse(null);

            if (department == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No department was found with the specified 'departmentId'");
            }

            var course = new Course();
            course.setName(courseDTO.getName());
            course.setCredits(courseDTO.getCredits());
            course.setDepartment(department);
            courseRepository.save(course);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please include 'name' & 'departmentId' in your request payload");
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable Long id
    ) {
        if (id != null && id > 0) {
            courseRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please include an 'id' number property in your path");
    }
}
