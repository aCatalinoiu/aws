package com.project.bankmanag.service.type;

import com.project.bankmanag.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    Page<Type> getAll(Pageable pageable);

    Type getTypeById(Long id);

    Type addType(Type type);

    Type updateType(Type typeToUpdate, Long id);

    void deleteType(Long id);
}
