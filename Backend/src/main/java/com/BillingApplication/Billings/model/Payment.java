package com.BillingApplication.Billings.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity @Table(name="payments")
public class Payment {
     @Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
     private Long id;
  @ManyToOne(optional=false) 
  private Invoice invoice;
  private Double amount;
   private LocalDate paymentDate;
   public Payment() {
   }
   public Payment(Long id, Invoice invoice, Double amount, LocalDate paymentDate) {
    this.id = id;
    this.invoice = invoice;
    this.amount = amount;
    this.paymentDate = paymentDate;
   }
   public Long getId() {
    return id;
   }
   public void setId(Long id) {
    this.id = id;
   }
   public Invoice getInvoice() {
    return invoice;
   }
   public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
   }
   public Double getAmount() {
    return amount;
   }
   public void setAmount(Double amount) {
    this.amount = amount;
   }
   public LocalDate getPaymentDate() {
    return paymentDate;
   }
   public void setPaymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
   }

}
