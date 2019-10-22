package com.project.bankmanag.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bankmanag.models.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

}
