package com.faf.solid.payment;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Debit Card payment implementation
 * Open/Closed Principle: Another extension of Payment interface
 */
public class DebitCardPayment implements Payment {
    
    private final String cardNumber;
    private final String pin;
    private final String bankName;
    private BigDecimal accountBalance;
    
    public DebitCardPayment(String cardNumber, String pin, String bankName, BigDecimal accountBalance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.bankName = bankName;
        this.accountBalance = accountBalance;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        if (!validatePaymentMethod()) {
            return new PaymentResult(false, null, "Invalid debit card details");
        }
        
        if (accountBalance.compareTo(amount) < 0) {
            return new PaymentResult(false, null, "Insufficient funds");
        }
        
        System.out.println("Processing debit card payment...");
        System.out.println("Bank: " + bankName);
        System.out.println("Card: ****-****-****-" + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Amount: $" + amount);
        
        // Simulate processing
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Deduct from account balance
        accountBalance = accountBalance.subtract(amount);
        
        String transactionId = "DB_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(true, transactionId, "Debit card payment successful");
    }
    
    @Override
    public boolean validatePaymentMethod() {
        return cardNumber != null && cardNumber.length() == 16 &&
               pin != null && pin.length() == 4 &&
               bankName != null && !bankName.trim().isEmpty() &&
               accountBalance != null;
    }
    
    @Override
    public String getPaymentMethodType() {
        return "Debit Card";
    }
    
    @Override
    public boolean supportsRefund() {
        return true;
    }
    
    @Override
    public PaymentResult processRefund(BigDecimal amount, String originalTransactionId) {
        System.out.println("Processing debit card refund...");
        System.out.println("Bank: " + bankName);
        System.out.println("Refund amount: $" + amount);
        
        // Add back to account balance
        accountBalance = accountBalance.add(amount);
        
        String refundId = "REF_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(true, refundId, "Debit card refund processed");
    }
    
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }
}