package com.university.mgmtapi.services;

import com.university.mgmtapi.dto.EntityDTO;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    @Override
    public boolean isValid(EntityDTO entityDTO) {
        return entityDTO != null
                && entityDTO.getName() != null
                && !entityDTO.getName().isBlank();
    }
}
