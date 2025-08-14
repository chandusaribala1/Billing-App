package com.BillingApplication.Billings.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApplication.Billings.model.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByNameContainingIgnoreCase(String name);
  List<Customer> findByEmailContainingIgnoreCase(String email);

}
