package com.pack.model;


import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Card")
public class Card {

    @Id
    private String cardNumber;
   // private String userId; // Reference to the user who owns the card
    private int userId;
    private int masterId;
    
    private String cardHolderName;
    private String status; // e.g., ACTIVE, INACTIVE, ON_HOLD
    private double spendingLimit;
    private String expiryDate; // Add expiry date field
    
    private int cvv;
    
    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            cardNumber.append(random.nextInt(10));
        }

        return cardNumber.toString();
    }

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSpendingLimit() {
		return spendingLimit;
	}

	public void setSpendingLimit(double spendingLimit) {
		this.spendingLimit = spendingLimit;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Card(String cardNumber, int userId, int masterId, String cardHolderName, String status, double spendingLimit,
			String expiryDate, int cvv) {
		super();
		this.cardNumber = cardNumber;
		this.userId = userId;
		this.masterId = masterId;
		this.cardHolderName = cardHolderName;
		this.status = status;
		this.spendingLimit = spendingLimit;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	
    
}


