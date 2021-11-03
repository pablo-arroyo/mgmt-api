package com.university.mgmtapi.controllers;

import com.university.mgmtapi.dto.responses.SearchDTO;
import com.university.mgmtapi.entities.Schedule;
import com.university.mgmtapi.repositories.ScheduleRepository;
import com.university.mgmtapi.services.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final ScheduleRepository scheduleRepository;

    private final SearchService searchService;

    public SearchController(ScheduleRepository scheduleRepository,
                            SearchService searchService) {
        this.scheduleRepository = scheduleRepository;
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<List<SearchDTO>> getAllProfessorCourses() {
        var schedules = new ArrayList<Schedule>();
        scheduleRepository.findAll().forEach(schedules::add);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(searchService.groupProfessorCourses(schedules));
    }

}
