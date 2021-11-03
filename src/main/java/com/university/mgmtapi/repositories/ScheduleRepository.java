package com.university.mgmtapi.repositories;

import com.university.mgmtapi.entities.Schedule;
import com.university.mgmtapi.entities.ScheduleKey;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, ScheduleKey> {
}
