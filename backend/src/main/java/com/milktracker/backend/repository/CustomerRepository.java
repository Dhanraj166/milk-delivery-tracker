package com.milktracker.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milktracker.backend.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}