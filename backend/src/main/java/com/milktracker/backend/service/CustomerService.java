package com.milktracker.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktracker.backend.entity.Customer;
import com.milktracker.backend.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get one customer by id
    public Customer getCustomerById(Long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            return customer.get();
        }

        throw new RuntimeException("Customer not found with id: " + customerId);
    }

    // Add a new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update an existing customer
    public Customer updateCustomer(long id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setDailyQty(updatedCustomer.getDailyQty());
        existingCustomer.setRate(updatedCustomer.getRate());

        return customerRepository.save(existingCustomer);
    }

    // Delete a customer
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }
}