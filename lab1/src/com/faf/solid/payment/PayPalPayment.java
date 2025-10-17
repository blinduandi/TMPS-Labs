package com.faf.solid.payment;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * PayPal payment implementation
 * Open/Closed Principle: Yet another extension without modifying existing code
 */
public class PayPalPayment implements Payment {
    
    private final String email;
    private final String password;
    private final BigDecimal paypalBalance;
    
    public PayPalPayment(String email, String password, BigDecimal paypalBalance) {
        this.email = email;
        this.password = password;
        this.paypalBalance = paypalBalance;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        if (!validatePaymentMethod()) {
            return new PaymentResult(false, null, "Invalid PayPal credentials");
        }
        
        System.out.println("Processing PayPal payment...");
        System.out.println("PayPal account: " + email);
        System.out.println("Amount: $" + amount);
        
        // Simulate PayPal API call
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // PayPal handles insufficient funds differently - it can use linked bank account
        if (paypalBalance.compareTo(amount) < 0) {
            System.out.println("PayPal balance insufficient, using linked bank account...");
        }
        
        String transactionId = "PP_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(true, transactionId, "PayPal payment successful");
    }
    
    @Override
    public boolean validatePaymentMethod() {
        return email != null && email.contains("@") &&
               password != null && password.length() >= 6 &&
               paypalBalance != null;
    }
    
    @Override
    public String getPaymentMethodType() {
        return "PayPal";
    }
    
    @Override
    public boolean supportsRefund() {
        return true;
    }
    
    @Override
    public PaymentResult processRefund(BigDecimal amount, String originalTransactionId) {
        System.out.println("Processing PayPal refund...");
        System.out.println("PayPal account: " + email);
        System.out.println("Original transaction: " + originalTransactionId);
        System.out.println("Refund amount: $" + amount);
        
        String refundId = "REF_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(true, refundId, "PayPal refund processed");
    }
}