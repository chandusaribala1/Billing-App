package com.BillingApplication.Billings.dto;

import java.time.LocalDate;

public class PaymentRequest {
  private Long invoiceId;
    private double amount;
    private LocalDate paymentDate;

    public PaymentRequest() {}

    public PaymentRequest(Long invoiceId, double amount, LocalDate paymentDate) {
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    
}
