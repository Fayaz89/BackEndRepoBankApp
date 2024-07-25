package com.pack.controller;

import com.pack.exception.TransactionNotFoundException;
import com.pack.model.Transaction;
import com.pack.response.ResponseHandler;
import com.pack.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }
    
//    @PutMapping
//    public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction) {
//        Transaction transaction = transactionService.getTransactionById(transaction.getTransactionId());
//        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<Object> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Object> getTransactionById(@PathVariable int transactionId) throws TransactionNotFoundException {
    	
    	return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
				transactionService.getTransactionById(transactionId));
   }

    @GetMapping("/cards/{cardId}")
    public ResponseEntity<Object> getTransactionsByCardId(@PathVariable int cardId) {
        List<Transaction> transactions = transactionService.getTransactionsByCardId(cardId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getTransactionsByUserId(@PathVariable int userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/masters/{masterId}")
    public ResponseEntity<Object> getTransactionsByMasterId(@PathVariable int masterId) {
        List<Transaction> transactions = transactionService.getTransactionsByMasterId(masterId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
