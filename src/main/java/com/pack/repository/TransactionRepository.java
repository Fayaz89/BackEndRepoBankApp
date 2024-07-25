package com.pack.repository;

import com.pack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByCardId(int cardId);
    List<Transaction> findByUserId(int userId);
    List<Transaction> findByMasterId(int masterId);
}
