package com.project.bankmanag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.bankmanag.model.Type;

@Repository
public interface TypeRepository extends PagingAndSortingRepository<Type, Long> {

}
