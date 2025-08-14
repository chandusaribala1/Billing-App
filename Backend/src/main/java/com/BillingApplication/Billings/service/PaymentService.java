package com.BillingApplication.Billings.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BillingApplication.Billings.dto.PaymentRequest;
import com.BillingApplication.Billings.model.Invoice;
import com.BillingApplication.Billings.model.Payment;
import com.BillingApplication.Billings.repository.InvoiceRepository;
import com.BillingApplication.Billings.repository.PaymentRepository;
@Service
public class PaymentService {
    private final PaymentRepository paymentRepo; private final InvoiceRepository invoiceRepo;
  public PaymentService(PaymentRepository paymentRepo, InvoiceRepository invoiceRepo){this.paymentRepo=paymentRepo; this.invoiceRepo=invoiceRepo;}
  @Transactional public Payment record(PaymentRequest req)
  {
     Invoice inv = invoiceRepo.findById(req.getInvoiceId()).orElseThrow();
     Payment p=new Payment(); 
     p.setInvoice(inv);
     p.setAmount(req.getAmount()); 
     p.setPaymentDate(req.getPaymentDate()); 
     Payment saved = paymentRepo.save(p); 
     double paid = paymentRepo.findByInvoice(inv).stream().mapToDouble(Payment::getAmount).sum(); 
     if(paid>=inv.getTotalAmount()) inv.setStatus("PAID"); 
     else if(paid>0) inv.setStatus("PARTIALLY_PAID"); 
     else inv.setStatus("UNPAID"); 
     invoiceRepo.save(inv); 
     return saved;
    }
  public List<Payment> byInvoice(Long invoiceId)
  {
     Invoice inv = invoiceRepo.findById(invoiceId).orElseThrow();
      return paymentRepo.findByInvoice(inv); 
  }
  public List<Payment> list()
  { 
    return paymentRepo.findAll(); 
  }
}
