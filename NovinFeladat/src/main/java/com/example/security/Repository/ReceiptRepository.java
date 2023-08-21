package com.example.security.Repository;

import com.example.security.Model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository  extends JpaRepository<Receipt, Long> {
    Receipt findByName(String name);

    Receipt findReceiptById(Long id);
}
