package com.BillingApplication.Billings.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BillingApplication.Billings.dto.CreateInvoiceRequest;
import com.BillingApplication.Billings.dto.InvoiceItemRequest;
import com.BillingApplication.Billings.model.Customer;
import com.BillingApplication.Billings.model.Invoice;
import com.BillingApplication.Billings.model.InvoiceItem;
import com.BillingApplication.Billings.model.Product;
import com.BillingApplication.Billings.repository.CustomerRepository;
import com.BillingApplication.Billings.repository.InvoiceRepository;
import com.BillingApplication.Billings.repository.ProductRepository;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;
  public InvoiceService(InvoiceRepository invoiceRepo, CustomerRepository customerRepo, ProductRepository productRepo)
  {
    this.invoiceRepo=invoiceRepo;
    this.customerRepo=customerRepo;
    this.productRepo=productRepo; 
  }
  @Transactional public Invoice createInvoice(CreateInvoiceRequest req){
    Customer c = customerRepo.findById(req.getCustomerId()).orElseThrow(); Invoice inv = new Invoice(); inv.setCustomer(c); inv.setDueDate(req.getDueDate()); inv.setStatus("UNPAID");
    List<InvoiceItem> items = new ArrayList<>(); double total=0;
    for(InvoiceItemRequest ir: req.getItems()){ Product p = productRepo.findById(ir.getProductId()).orElseThrow(); InvoiceItem it=new InvoiceItem(); it.setInvoice(inv); it.setProduct(p); it.setQuantity(ir.getQuantity()); it.setUnitPrice(p.getPrice()); it.setLineTotal(p.getPrice()*ir.getQuantity()); total+=it.getLineTotal(); items.add(it); }
    inv.setItems(items); inv.setTotalAmount(total); return invoiceRepo.save(inv);
  }
  public Invoice updateInvoice(Long id, CreateInvoiceRequest req)
  {
     Invoice inv = invoiceRepo.findById(id).orElseThrow();
      inv.getItems().clear(); 
      double total=0; 
      for(InvoiceItemRequest ir: req.getItems()){
         Product p = productRepo.findById(ir.getProductId()).orElseThrow();
          InvoiceItem it=new InvoiceItem(); it.setInvoice(inv);
        it.setProduct(p);
          it.setQuantity(ir.getQuantity());
           it.setUnitPrice(p.getPrice());
            it.setLineTotal(p.getPrice()*ir.getQuantity());
            total+=it.getLineTotal();
             inv.getItems().add(it);} 
             inv.setDueDate(req.getDueDate()); 
             inv.setTotalAmount(total); 
             return invoiceRepo.save(inv); 
 }
  public void delete(Long id)
  {
    invoiceRepo.deleteById(id);
} 
  public Invoice get(Long id)
  {
    return invoiceRepo.findById(id).orElseThrow();
} 
  public List<Invoice> list()
  {
    return invoiceRepo.findAll();
}
}
