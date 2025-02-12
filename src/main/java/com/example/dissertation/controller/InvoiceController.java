package com.example.dissertation.controller;

import com.example.dissertation.entity.Invoice;
import com.example.dissertation.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Invoices")
public class InvoiceController {
    private final InvoiceService InvoiceService;

    @Autowired
    public InvoiceController(InvoiceService InvoiceService) {
        this.InvoiceService = InvoiceService;
    }

    @GetMapping("/isServiceUp")
    public String isServiceUp() {
        return "Service is UP !!";
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return InvoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> Invoice = InvoiceService.getInvoiceById(id);
        return Invoice.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice Invoice) {
        Invoice createdInvoice = InvoiceService.createInvoice(Invoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice Invoice) {
        Invoice updatedInvoice = InvoiceService.updateInvoice(id, Invoice);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        InvoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

