package com.university.mgmtapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.mgmtapi.dto.responses.SearchDTO;
import com.university.mgmtapi.entities.*;
import com.university.mgmtapi.services.SearchService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SearchServiceTests {
    private static final String EXPECTED_OUTPUT = "[{\"name\":\"Pablo Arroyo\",\"courses\":[\"My course\",\"My second course\",\"My third course\"]}]";

    private static final List<Schedule> scheduleList = new ArrayList<>();

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private SearchService searchService;

    @BeforeAll()
    public static void setup() {
        var firstSchedule = new Schedule();
        firstSchedule.setId(new ScheduleKey(1L, 1L));
        firstSchedule.setProfessor(
                new Professor(
                        1L,
                        "Pablo Arroyo",
                        new Department(1L, "Department")
                )
        );
        firstSchedule.setCourse(
                new Course(
                        1L,
                        "My course",
                        2,
                        new Department(1L, "Department")
                )
        );
        firstSchedule.setYear(2021);
        firstSchedule.setSemester(3);

        var secondSchedule = new Schedule();
        secondSchedule.setId(new ScheduleKey(1L, 2L));
        secondSchedule.setProfessor(
                new Professor(
                        1L,
                        "Pablo Arroyo",
                        new Department(1L, "Department")
                )
        );
        secondSchedule.setCourse(
                new Course(
                        2L,
                        "My second course",
                        2,
                        new Department(1L, "Department")
                )
        );
        secondSchedule.setYear(2021);
        secondSchedule.setSemester(3);

        var thirdSchedule = new Schedule();
        thirdSchedule.setId(new ScheduleKey(1L, 3L));
        thirdSchedule.setProfessor(
                new Professor(
                        1L,
                        "Pablo Arroyo",
                        new Department(1L, "Department")
                )
        );
        thirdSchedule.setCourse(
                new Course(
                        3L,
                        "My third course",
                        2,
                        new Department(1L, "Department")
                )
        );
        thirdSchedule.setYear(2021);
        thirdSchedule.setSemester(3);


        scheduleList.add(firstSchedule);
        scheduleList.add(secondSchedule);
        scheduleList.add(thirdSchedule);
    }

    @Test
    void searchEndpointServiceGroupsProfessorAsExpected() throws IOException {
        List<SearchDTO> groupedProfessors =
                searchService.groupProfessorCourses(scheduleList);

        assertThat(jsonMapper.writeValueAsString(groupedProfessors))
                .isEqualTo(EXPECTED_OUTPUT);
    }
}
