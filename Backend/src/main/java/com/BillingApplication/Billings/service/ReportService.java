package com.BillingApplication.Billings.service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.BillingApplication.Billings.model.Customer;
import com.BillingApplication.Billings.model.Invoice;
import com.BillingApplication.Billings.repository.CustomerRepository;
import com.BillingApplication.Billings.repository.InvoiceRepository;
@Service
public class ReportService {
private final InvoiceRepository invoiceRepo; 
private final CustomerRepository customerRepo;
  public ReportService(InvoiceRepository invoiceRepo, CustomerRepository customerRepo)
  {
    this.invoiceRepo=invoiceRepo; this.customerRepo=customerRepo;
}
  public Map<String,Object> invoiceSummary(LocalDate from, LocalDate to)
  {
     List<Invoice> invoices = invoiceRepo.findByDueDateBetween(from,to); 
     double total = invoices.stream().mapToDouble(i->i.getTotalAmount()==null?0.0:i.getTotalAmount()).sum();
      Map<String,Object> m=new HashMap<>(); 
      m.put("count", invoices.size()); 
      m.put("totalAmount", total); 
      m.put("invoices", invoices); return m;
 }
public Map<String,Object> outstanding()
{
     List<Invoice> outs = invoiceRepo.findByStatus("UNPAID");
      List<Invoice> partial = invoiceRepo.findByStatus("PARTIALLY_PAID"); 
      Map<String,Object> m=new HashMap<>(); 
      m.put("unpaid", outs); 
      m.put("partiallyPaid", partial); 
      return m;
    }
  public Map<String,Object> customerHistory(Long customerId)
  {
     Customer c = customerRepo.findById(customerId).orElseThrow(); 
     List<Invoice> invoices = invoiceRepo.findByCustomer(c); 
     Map<String,Object> m=new HashMap<>(); 
     m.put("customer", c); 
     m.put("invoices", invoices); 
     return m;
    }
}
