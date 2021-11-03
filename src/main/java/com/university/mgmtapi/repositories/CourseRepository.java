package com.university.mgmtapi.repositories;

import com.university.mgmtapi.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
