package com.university.mgmtapi.services;

import com.university.mgmtapi.dto.responses.SearchDTO;
import com.university.mgmtapi.entities.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{
    @Override
    public List<SearchDTO> groupProfessorCourses(List<Schedule> schedules) {
        return schedules.stream()
                .collect(Collectors.groupingBy(Schedule::getProfessor))
                .values()
                .stream()
                .map(SearchDTO::new)
                .collect(Collectors.toList());
    }
}
