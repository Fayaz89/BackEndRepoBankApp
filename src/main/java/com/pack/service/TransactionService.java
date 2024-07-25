package com.pack.service;

import com.pack.exception.TransactionNotFoundException;
import com.pack.model.Transaction;
import com.pack.model.User;
import com.pack.repository.TransactionRepository;
import com.pack.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private UserRepository userRepository;

	public Transaction createTransaction(Transaction transaction) {
		transaction.setCreatedtimestamp(LocalDateTime.now());
		transaction.setUpdatedtimestamp(LocalDateTime.now());
		return transactionRepository.save(transaction);
	}

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public Transaction getTransactionById(int transactionId) throws TransactionNotFoundException {
		Transaction transaction = transactionRepository.findById(transactionId).orElse(null);

		if (transaction != null) {
			return transaction;
		}
		throw new TransactionNotFoundException("Transaction not found");
	}

	public List<Transaction> getTransactionsByCardId(int cardId) {
		return transactionRepository.findByCardId(cardId);
	}

	public List<Transaction> getTransactionsByUserId(int userId) {

		List<Transaction> list1 = transactionRepository.findByUserId(userId);
		List<Transaction> list2 = transactionRepository.findByMasterId(userId);

		List<Transaction> mergedList = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());

		return mergedList;
	}

	public List<Transaction> getTransactionsByMasterId(int masterId) {
		return transactionRepository.findByMasterId(masterId);
	}
}
