package com.faf.solid.payment;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Credit Card payment implementation
 * Open/Closed Principle: Extends Payment interface without modifying existing code
 */
public class CreditCardPayment implements Payment {
    
    private final String cardNumber;
    private final String cardHolderName;
    private final String expiryDate;
    private final String cvv;
    
    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        if (!validatePaymentMethod()) {
            return new PaymentResult(false, null, "Invalid credit card details");
        }
        
        // Simulate credit card processing
        System.out.println("Processing credit card payment...");
        System.out.println("Card: ****-****-****-" + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Amount: $" + amount);
        
        // Simulate processing time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate 95% success rate
        if (Math.random() > 0.05) {
            String transactionId = "CC_" + UUID.randomUUID().toString().substring(0, 8);
            return new PaymentResult(true, transactionId, "Credit card payment successful");
        } else {
            return new PaymentResult(false, null, "Credit card declined");
        }
    }
    
    @Override
    public boolean validatePaymentMethod() {
        return cardNumber != null && cardNumber.length() == 16 &&
               cardHolderName != null && !cardHolderName.trim().isEmpty() &&
               expiryDate != null && expiryDate.matches("\\d{2}/\\d{2}") &&
               cvv != null && cvv.length() == 3;
    }
    
    @Override
    public String getPaymentMethodType() {
        return "Credit Card";
    }
    
    @Override
    public boolean supportsRefund() {
        return true;
    }
    
    @Override
    public PaymentResult processRefund(BigDecimal amount, String originalTransactionId) {
        System.out.println("Processing credit card refund...");
        System.out.println("Original transaction: " + originalTransactionId);
        System.out.println("Refund amount: $" + amount);
        
        String refundId = "REF_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(true, refundId, "Credit card refund processed");
    }
}