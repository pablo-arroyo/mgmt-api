package com.university.mgmtapi.dto.responses;

import com.university.mgmtapi.entities.Schedule;

import java.util.List;
import java.util.stream.Collectors;

public class SearchDTO {
    private String name;

    private List<String> courses;

    public SearchDTO(List<Schedule> schedules) {
        name = schedules.get(0).getProfessor().getName();
        courses = schedules.stream()
                .map(schedule -> schedule.getCourse().getName())
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
