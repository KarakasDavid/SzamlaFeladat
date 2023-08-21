package com.example.security.Service;

import com.example.security.Model.Receipt;
import com.example.security.Repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt createReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }
    public Receipt findById(Long id) {
        return receiptRepository.findReceiptById(id);
    }

}
