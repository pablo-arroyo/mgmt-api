package com.university.mgmtapi.dto.requests;

import com.university.mgmtapi.dto.EntityDTO;

public class CourseDTO extends EntityDTO {

    private Long departmentId;

    private int credits;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
