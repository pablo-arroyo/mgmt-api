package com.university.mgmtapi.dto.requests;

import com.university.mgmtapi.dto.EntityDTO;

public class ProfessorDTO extends EntityDTO {

    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
