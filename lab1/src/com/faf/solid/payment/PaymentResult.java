package com.faf.solid.payment;

/**
 * PaymentResult - represents the result of a payment operation
 */
public class PaymentResult {
    private final boolean success;
    private final String transactionId;
    private final String message;
    
    public PaymentResult(boolean success, String transactionId, String message) {
        this.success = success;
        this.transactionId = transactionId;
        this.message = message;
    }
    
    public boolean isSuccess() { return success; }
    public String getTransactionId() { return transactionId; }
    public String getMessage() { return message; }
    
    @Override
    public String toString() {
        return "PaymentResult{success=" + success + 
               ", transactionId='" + transactionId + 
               "', message='" + message + "'}";
    }
}