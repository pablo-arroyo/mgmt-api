package com.university.mgmtapi.repositories;

import com.university.mgmtapi.entities.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
