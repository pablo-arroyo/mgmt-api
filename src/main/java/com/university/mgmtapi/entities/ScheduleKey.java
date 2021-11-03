package com.university.mgmtapi.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ScheduleKey implements Serializable {
    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "course_id")
    private Long courseId;

    public ScheduleKey() {
    }

    public ScheduleKey(Long professorId, Long courseId) {
        this.professorId = professorId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleKey schedule = (ScheduleKey) o;
        return professorId.equals(schedule.professorId)
                && courseId.equals(schedule.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, courseId);
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
