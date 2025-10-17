package com.faf.solid.payment;

import java.math.BigDecimal;

/**
 * Payment interface - defines contract for payment processing
 * Open/Closed Principle: Open for extension (new payment methods), closed for modification
 */
public interface Payment {
    
    /**
     * Process payment for the given amount
     * @param amount the amount to charge
     * @return PaymentResult indicating success/failure and transaction details
     */
    PaymentResult processPayment(BigDecimal amount);
    
    /**
     * Validate payment method details
     * @return true if payment method is valid, false otherwise
     */
    boolean validatePaymentMethod();
    
    /**
     * Get the payment method type
     * @return string representing the payment method
     */
    String getPaymentMethodType();
    
    /**
     * Check if refund is supported by this payment method
     * @return true if refunds are supported
     */
    boolean supportsRefund();
    
    /**
     * Process refund for the given amount
     * @param amount the amount to refund
     * @param originalTransactionId the original transaction ID
     * @return PaymentResult indicating success/failure
     */
    default PaymentResult processRefund(BigDecimal amount, String originalTransactionId) {
        if (!supportsRefund()) {
            return new PaymentResult(false, null, "Refunds not supported for " + getPaymentMethodType());
        }
        // Default implementation - can be overridden
        return new PaymentResult(false, null, "Refund not implemented");
    }
}