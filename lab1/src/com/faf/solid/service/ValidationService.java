package com.faf.solid.service;

import com.faf.solid.domain.User;
import com.faf.solid.domain.Product;
import java.util.regex.Pattern;

/**
 * Validation service - handles all validation operations
 * Single Responsibility: Only responsible for data validation
 */
public class ValidationService {
    
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    
    private static final Pattern USERNAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9_]{3,20}$");

    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public boolean isValidUser(User user) {
        if (user == null) {
            return false;
        }
        
        return isValidUsername(user.getUsername()) &&
               isValidEmail(user.getEmail()) &&
               user.getFirstName() != null && !user.getFirstName().trim().isEmpty() &&
               user.getLastName() != null && !user.getLastName().trim().isEmpty();
    }

    public boolean isValidProduct(Product product) {
        if (product == null) {
            return false;
        }
        
        return product.getName() != null && !product.getName().trim().isEmpty() &&
               product.getPrice() != null && product.getPrice().compareTo(java.math.BigDecimal.ZERO) > 0 &&
               product.getStockQuantity() >= 0;
    }

    public boolean isValidQuantity(int quantity) {
        return quantity > 0 && quantity <= 1000; // reasonable limit
    }

    public String getValidationErrors(User user) {
        StringBuilder errors = new StringBuilder();
        
        if (!isValidUsername(user.getUsername())) {
            errors.append("Invalid username (3-20 alphanumeric characters only). ");
        }
        if (!isValidEmail(user.getEmail())) {
            errors.append("Invalid email format. ");
        }
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            errors.append("First name is required. ");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            errors.append("Last name is required. ");
        }
        
        return errors.toString();
    }
}