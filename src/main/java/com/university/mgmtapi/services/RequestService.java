package com.university.mgmtapi.services;

import com.university.mgmtapi.dto.EntityDTO;

public interface RequestService {
    boolean isValid(EntityDTO entityDTO);
}
