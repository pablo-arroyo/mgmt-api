package com.university.mgmtapi.services;

import com.university.mgmtapi.dto.responses.SearchDTO;
import com.university.mgmtapi.entities.Schedule;

import java.util.List;

public interface SearchService {
    List<SearchDTO> groupProfessorCourses(List<Schedule> schedules);
}
