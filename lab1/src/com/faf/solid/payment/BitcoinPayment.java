package com.faf.solid.payment;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Bitcoin payment implementation - demonstrates how easy it is to add new payment methods
 * Open/Closed Principle: New payment method added without modifying existing code
 */
public class BitcoinPayment implements Payment {
    
    private final String walletAddress;
    private final String privateKey;
    private final BigDecimal bitcoinBalance;
    
    public BitcoinPayment(String walletAddress, String privateKey, BigDecimal bitcoinBalance) {
        this.walletAddress = walletAddress;
        this.privateKey = privateKey;
        this.bitcoinBalance = bitcoinBalance;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        if (!validatePaymentMethod()) {
            return new PaymentResult(false, null, "Invalid Bitcoin wallet details");
        }
        
        // Convert USD to Bitcoin (simplified - using fixed rate for demo)
        BigDecimal bitcoinAmount = amount.divide(BigDecimal.valueOf(45000)); // $45,000 per BTC
        
        if (bitcoinBalance.compareTo(bitcoinAmount) < 0) {
            return new PaymentResult(false, null, "Insufficient Bitcoin balance");
        }
        
        System.out.println("Processing Bitcoin payment...");
        System.out.println("Wallet: " + walletAddress.substring(0, 8) + "...");
        System.out.println("Amount: " + bitcoinAmount + " BTC (~$" + amount + ")");
        System.out.println("Broadcasting to blockchain...");
        
        // Simulate blockchain confirmation time
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        String transactionId = "BTC_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(true, transactionId, "Bitcoin payment confirmed on blockchain");
    }
    
    @Override
    public boolean validatePaymentMethod() {
        return walletAddress != null && walletAddress.length() >= 26 &&
               privateKey != null && privateKey.length() >= 51 &&
               bitcoinBalance != null && bitcoinBalance.compareTo(BigDecimal.ZERO) > 0;
    }
    
    @Override
    public String getPaymentMethodType() {
        return "Bitcoin";
    }
    
    @Override
    public boolean supportsRefund() {
        return false; // Bitcoin transactions are irreversible
    }
    
    @Override
    public PaymentResult processRefund(BigDecimal amount, String originalTransactionId) {
        return new PaymentResult(false, null, 
            "Bitcoin transactions are irreversible - refunds not supported");
    }
}