package com.project.bankmanag.service.type;

import com.project.bankmanag.exception.type.TypeNotFoundException;
import com.project.bankmanag.model.Type;
import com.project.bankmanag.repository.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    private final Logger logger = LoggerFactory.getLogger(TypeService.class);
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Page<Type> getAll(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeRepository.findById(id).orElseThrow(() -> new TypeNotFoundException(id));
    }

    @Override
    public Type addType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type updateType(Type typeToUpdate, Long id) {
        typeRepository.findById(id).orElseThrow(() -> new TypeNotFoundException(id));
        typeToUpdate.setId(id);
        return typeRepository.save(typeToUpdate);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.findById(id).orElseThrow(() -> new TypeNotFoundException(id));
        typeRepository.deleteById(id);
    }
}
