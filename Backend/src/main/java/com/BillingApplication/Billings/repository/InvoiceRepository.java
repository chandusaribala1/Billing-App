package com.BillingApplication.Billings.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BillingApplication.Billings.model.Customer;
import com.BillingApplication.Billings.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
      List<Invoice> findByCustomer(Customer customer);
  List<Invoice> findByStatus(String status);
  @Query("select i from Invoice i where i.dueDate between :from and :to")
  List<Invoice> findByDueDateBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);

}
