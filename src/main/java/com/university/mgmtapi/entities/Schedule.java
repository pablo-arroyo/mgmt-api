package com.university.mgmtapi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Schedule {
    @EmbeddedId
    private ScheduleKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("professorId")
    @JoinColumn(name = "professor_id")
    private Professor professor;

    private int semester;

    private int year;

    public Schedule() {
    }

    public Schedule(ScheduleKey id, Course course, Professor professor, int semester, int year) {
        this.id = id;
        this.course = course;
        this.professor = professor;
        this.semester = semester;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return semester == schedule.semester
                && year == schedule.year
                && id.equals(schedule.id)
                && Objects.equals(course, schedule.course)
                && Objects.equals(professor, schedule.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, professor, semester, year);
    }

    public ScheduleKey getId() {
        return id;
    }

    public void setId(ScheduleKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
