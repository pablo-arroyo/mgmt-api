package com.university.mgmtapi.repositories;

import com.university.mgmtapi.entities.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
