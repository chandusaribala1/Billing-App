package com.BillingApplication.Billings.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApplication.Billings.dto.CreateInvoiceRequest;
import com.BillingApplication.Billings.model.Invoice;
import com.BillingApplication.Billings.service.InvoiceService;

@RestController 
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService service;
     public InvoiceController(InvoiceService service)
     {
        this.service=service;
    }
  @PostMapping
   public ResponseEntity<Invoice> create(@RequestBody CreateInvoiceRequest req)
   {
     return ResponseEntity.ok(service.createInvoice(req)); 
    }
  @PutMapping("/{id}") 
  public ResponseEntity<Invoice> update(@PathVariable Long id, @RequestBody CreateInvoiceRequest req)
  {
     return ResponseEntity.ok(service.updateInvoice(id,req)); 
    }
  @DeleteMapping("/{id}") 
  public ResponseEntity<Void> delete(@PathVariable Long id)
  {
     service.delete(id); return ResponseEntity.noContent().build();
     }
  @GetMapping("/{id}") 
  public ResponseEntity<Invoice> get(@PathVariable Long id)
  {
     return ResponseEntity.ok(service.get(id));
     }
  @GetMapping 
  public ResponseEntity<List<Invoice>> list()
  {
     return ResponseEntity.ok(service.list());
     }

}
