package com.BillingApplication.Billings.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity @Table(name="invoice_items")
public class InvoiceItem {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
private Long id;
  @ManyToOne(optional=false) 
  private Invoice invoice;
  @ManyToOne(optional=false) 
  private Product product;
  private Integer quantity; 
  private Double unitPrice; 
  private Double lineTotal;
  public InvoiceItem(Long id, Invoice invoice, Product product, Integer quantity, Double unitPrice, Double lineTotal) {
    this.id = id;
    this.invoice = invoice;
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.lineTotal = lineTotal;
  }
  public InvoiceItem() {
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
  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }
  public Integer getQuantity() {
    return quantity;
  }
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
  public Double getUnitPrice() {
    return unitPrice;
  }
  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }
  public Double getLineTotal() {
    return lineTotal;
  }
  public void setLineTotal(Double lineTotal) {
    this.lineTotal = lineTotal;
  }
  
}
