package com.BillingApplication.Billings.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity @Table(name="invoices")
public class Invoice {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
  private Long id;
  @ManyToOne(optional=false)
   private Customer customer;
  @OneToMany(mappedBy="invoice", cascade=CascadeType.ALL, orphanRemoval=true) 
  private List<InvoiceItem> items = new ArrayList<>();
  private Double totalAmount; 
  private LocalDate dueDate;
   private String status;
   public Invoice() {
  }
   public Invoice(Long id, Customer customer, List<InvoiceItem> items, Double totalAmount, LocalDate dueDate,
        String status) {
    this.id = id;
    this.customer = customer;
    this.items = items;
    this.totalAmount = totalAmount;
    this.dueDate = dueDate;
    this.status = status;
   }
   public Long getId() {
    return id;
   }
   public void setId(Long id) {
    this.id = id;
   }
   public Customer getCustomer() {
    return customer;
   }
   public void setCustomer(Customer customer) {
    this.customer = customer;
   }
   public List<InvoiceItem> getItems() {
    return items;
   }
   public void setItems(List<InvoiceItem> items) {
    this.items = items;
   }
   public Double getTotalAmount() {
    return totalAmount;
   }
   public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
   }
   public LocalDate getDueDate() {
    return dueDate;
   }
   public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
   }
   public String getStatus() {
    return status;
   }
   public void setStatus(String status) {
    this.status = status;
   }

}
