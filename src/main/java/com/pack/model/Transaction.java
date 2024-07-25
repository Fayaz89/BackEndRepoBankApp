package com.pack.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private int cardId;

    private int userId;

    private int masterId;  
    
    private BigDecimal amount;

    private LocalDateTime createdtimestamp;
    
    private LocalDateTime updatedtimestamp;

    private String description;
    
    private String expensecategory;
    
    private String merchant_name;
    
    private String status;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreatedtimestamp() {
		return createdtimestamp;
	}

	public void setCreatedtimestamp(LocalDateTime createdtimestamp) {
		this.createdtimestamp = createdtimestamp;
	}

	public LocalDateTime getUpdatedtimestamp() {
		return updatedtimestamp;
	}

	public void setUpdatedtimestamp(LocalDateTime updatedtimestamp) {
		this.updatedtimestamp = updatedtimestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpensecategory() {
		return expensecategory;
	}

	public void setExpensecategory(String expensecategory) {
		this.expensecategory = expensecategory;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Transaction(int transactionId, int cardId, int userId, int masterId, BigDecimal amount,
			LocalDateTime createdtimestamp, LocalDateTime updatedtimestamp, String description, String expensecategory,
			String merchant_name, String status) {
		super();
		this.transactionId = transactionId;
		this.cardId = cardId;
		this.userId = userId;
		this.masterId = masterId;
		this.amount = amount;
		this.createdtimestamp = createdtimestamp;
		this.updatedtimestamp = updatedtimestamp;
		this.description = description;
		this.expensecategory = expensecategory;
		this.merchant_name = merchant_name;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", cardId=" + cardId + ", userId=" + userId
				+ ", masterId=" + masterId + ", amount=" + amount + ", createdtimestamp=" + createdtimestamp
				+ ", updatedtimestamp=" + updatedtimestamp + ", description=" + description + ", expensecategory="
				+ expensecategory + ", merchant_name=" + merchant_name + ", status=" + status + "]";
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
}
