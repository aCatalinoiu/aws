package com.project.bankmanag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.bankmanag.model.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
	// findbyName etc.
}
