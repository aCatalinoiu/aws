package com.project.bankmanag.repository;

import com.project.bankmanag.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends PagingAndSortingRepository<Bank, Long> {
}
