package com.kocfcs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kocfcs.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	Customer findByIdNumber(String idNumber);

}
