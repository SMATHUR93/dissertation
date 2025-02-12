package com.example.dissertation.service;

import com.example.dissertation.entity.Invoice;
import com.example.dissertation.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository InvoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository InvoiceRepository) {
        this.InvoiceRepository = InvoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return InvoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return InvoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice Invoice) {
        return InvoiceRepository.save(Invoice);
    }

    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        if (InvoiceRepository.existsById(id)) {
            updatedInvoice.setId(id);
            return InvoiceRepository.save(updatedInvoice);
        } else {
            throw new RuntimeException("Invoice not found with id: " + id);
        }
    }

    public void deleteInvoice(Long id) {
        InvoiceRepository.deleteById(id);
    }
}
