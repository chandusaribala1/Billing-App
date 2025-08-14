package com.BillingApplication.Billings.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApplication.Billings.dto.PaymentRequest;
import com.BillingApplication.Billings.model.Payment;
import com.BillingApplication.Billings.service.PaymentService;

@RestController
 @RequestMapping("/payments")
public class PaymentController {
    private final PaymentService service;
     public PaymentController(PaymentService service)
     {
        this.service=service;
    }
  @PostMapping
   public ResponseEntity<Payment> record(@RequestBody PaymentRequest req)
   {
     return ResponseEntity.ok(service.record(req));
     }
  @GetMapping
   public ResponseEntity<List<Payment>> list()
   {
     return ResponseEntity.ok(service.list()); 
    }
  @GetMapping("/invoice/{invoiceId}")
   public ResponseEntity<List<Payment>> byInvoice(@PathVariable Long invoiceId)
   {
     return ResponseEntity.ok(service.byInvoice(invoiceId)); 
    }

}
