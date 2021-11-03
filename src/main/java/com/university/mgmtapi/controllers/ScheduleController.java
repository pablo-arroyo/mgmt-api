package com.university.mgmtapi.controllers;

import com.university.mgmtapi.dto.requests.ScheduleDTO;
import com.university.mgmtapi.entities.Schedule;
import com.university.mgmtapi.entities.ScheduleKey;
import com.university.mgmtapi.repositories.CourseRepository;
import com.university.mgmtapi.repositories.ProfessorRepository;
import com.university.mgmtapi.repositories.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    private final CourseRepository courseRepository;

    private final ProfessorRepository professorRepository;

    public ScheduleController(ScheduleRepository scheduleRepository,
                              CourseRepository courseRepository,
                              ProfessorRepository professorRepository) {
        this.scheduleRepository = scheduleRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        var schedules = new ArrayList<Schedule>();
        scheduleRepository.findAll().forEach(schedules::add);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(schedules);
    }

    @PostMapping
    public ResponseEntity<String> createSchedule(
            @RequestBody ScheduleDTO scheduleDTO
    ) {
        if (scheduleDTO.getCourseId() > 0 && scheduleDTO.getProfessorId() > 0) {
            var professor = professorRepository
                    .findById(scheduleDTO.getProfessorId())
                    .orElse(null);

            var course = courseRepository
                    .findById(scheduleDTO.getCourseId())
                    .orElse(null);

            if (professor == null || course == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No professor or course was found with the specified 'id'");
            }

            var schedule = new Schedule();
            schedule.setSemester(scheduleDTO.getSemester());
            schedule.setYear(scheduleDTO.getYear());
            schedule.setCourse(course);
            schedule.setProfessor(professor);
            scheduleRepository.save(schedule);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please include 'courseId' & 'professorId' in your request payload");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSchedule(
            @RequestBody ScheduleDTO scheduleDTO
    ) {
        if (scheduleDTO.getProfessorId() > 0 && scheduleDTO.getCourseId() > 0) {
            var scheduleKey = new ScheduleKey();
            scheduleKey.setCourseId(scheduleDTO.getCourseId());
            scheduleKey.setProfessorId(scheduleDTO.getProfessorId());

            scheduleRepository.deleteById(scheduleKey);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please include an 'courseId' & 'professorId' property in your request");
    }
}
