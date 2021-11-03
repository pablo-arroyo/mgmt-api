package com.university.mgmtapi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_seq"
    )
    @SequenceGenerator(
            name = "course_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private int credits;

    @ManyToOne
    private Department department;

    public Course() {
    }

    public Course(Long id, String name, int credits, Department department) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return credits == course.credits
                && id.equals(course.id)
                && name.equals(course.name)
                && Objects.equals(department, course.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, credits, department);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
