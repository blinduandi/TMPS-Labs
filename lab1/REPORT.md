# Lab 1 — SOLID Principles Coffee Shop — Detailed Report

Student: Andi Blindu

Group: FAF-233

Date: 2025-10-17

---

## Objective
This lab demonstrates all five SOLID object-oriented design principles using a simple, easy-to-understand coffee shop system:
- S — Single Responsibility Principle (SRP)
- O — Open/Closed Principle (OCP) 
- L — Liskov Substitution Principle (LSP)
- I — Interface Segregation Principle (ISP)
- D — Dependency Inversion Principle (DIP)

The coffee shop example makes it easy to understand how SOLID principles work in real applications.

## Project structure
```
src/coffeeshop/
├── CoffeeShopDemo.java      # Main demo showing all principles
├── domain/
│   ├── Coffee.java          # Coffee product class
│   ├── Customer.java        # Customer class  
│   └── Order.java           # Order class
├── service/
│   ├── OrderService.java    # Handles order operations (S)
│   ├── PaymentService.java  # Handles payments (S)
│   └── NotificationService.java # Sends notifications (S)
└── discount/
    ├── Discount.java        # Interface for discounts (O)
    ├── PercentageDiscount.java # Percentage-based discount (O,L)
    ├── VipDiscount.java     # VIP customer discount (O,L)
    └── StudentDiscount.java # Student discount (O,L)
```

**Total files: 11** - A realistic project size demonstrating enterprise-level SOLID design.

## Explanations with examples

### 1) Single Responsibility Principle (S)
**Definition:** A class should have only one reason to change. Each class should focus on a single responsibility.

**Coffee Shop Example:**
- `OrderService` - Only handles order creation and management
- `PaymentService` - Only processes payments and receipts  
- `NotificationService` - Only sends customer notifications

**Code snippet:**
```java
public class OrderService {
    public Order createOrder(Customer customer) {
        System.out.println("Creating new order for: " + customer.getName());
        return new Order(customer);
    }
    
    public void addCoffeeToOrder(Order order, Coffee coffee) {
        order.addCoffee(coffee);
        System.out.println("Added " + coffee + " to order");
    }
}
```

**Why this works:** If we need to change how payments are processed, we only modify `PaymentService`. If we change notification methods, we only touch `NotificationService`. Each service has one clear job.---

### 2) Open/Closed Principle (O)
**Definition:** Software should be open for extension but closed for modification.

**Coffee Shop Example:**
The `Discount` interface allows adding new discount types without changing existing code:

**Code snippet:**
```java
public interface Discount {
    double applyDiscount(Order order);
    String getDiscountDescription();
}

// Easy to add new discount types:
public class StudentDiscount implements Discount {
    public double applyDiscount(Order order) {
        double discountAmount = order.getTotal() * 0.15; // 15% off
        order.setTotal(order.getTotal() - discountAmount);
        return discountAmount;
    }
}

public class VipDiscount implements Discount {
    public double applyDiscount(Order order) {
        double discountAmount = 2.0; // $2 off for VIP
        order.setTotal(order.getTotal() - discountAmount);
        return discountAmount;
    }
}
```

**Why this works:** To add a new discount (like `SeniorDiscount` or `HolidayDiscount`), we just create a new class implementing `Discount`. No existing code needs to change.

---

### 3) Liskov Substitution Principle (L)
**Definition:** Objects of a superclass should be replaceable with objects of subclasses without breaking functionality.

**Coffee Shop Example:**
All `Discount` implementations can be used interchangeably:

**Code snippet:**
```java
// This method works with ANY Discount implementation
private static void applyAnyDiscount(Order order, Discount discount) {
    discount.applyDiscount(order);
    System.out.println("Applied: " + discount.getDiscountDescription());
}

// All these calls work the same way:
applyAnyDiscount(order, new StudentDiscount());    // 15% off
applyAnyDiscount(order, new VipDiscount());        // $2 off  
applyAnyDiscount(order, new PercentageDiscount(20)); // 20% off
```

**Why this works:** Every `Discount` implementation honors the contract - they all apply a discount and return a description. The calling code doesn't need to know which specific discount is being used.

---

### 4) Interface Segregation Principle (I)
**Definition:** Clients shouldn't be forced to depend on interfaces they don't use.

**Coffee Shop Example:**
Instead of one large interface, we use small, focused ones:

```java
// Good: Small, focused interfaces
interface Discount {
    double applyDiscount(Order order);
    String getDiscountDescription();
}

// Instead of this large interface:
// interface CoffeeShopOperations {
//     void createOrder();
//     void processPayment();
//     void sendNotification(); 
//     void applyDiscount();
//     void manageInventory();
// }
```

---

### 5) Dependency Inversion Principle (D)  
**Definition:** High-level modules shouldn't depend on low-level modules. Both should depend on abstractions.

**Coffee Shop Example:**
Our demo depends on the `Discount` interface, not concrete classes:

```java
// High-level code depends on abstraction (Discount interface)
private static void demonstrateOrder(..., Discount discount) {
    // Works with any discount implementation
    discount.applyDiscount(order);
}
```

This allows easy testing and swapping of discount strategies without changing the core business logic.

---

## How to run (PowerShell)
Open PowerShell and run the following commands:

```powershell
cd 'c:\Users\andib\OneDrive\Desktop\FAF\TMPS\lab1\src'

# Compile all Java files
javac --release 17 coffeeshop\*.java coffeeshop\domain\*.java coffeeshop\service\*.java coffeeshop\discount\*.java

# Run the demo
java coffeeshop.CoffeeShopDemo
```

## Captured program output (sample)
```
☕ Welcome to SOLID Coffee Shop! ☕

=== SCENARIO 1: Regular Customer ===
Creating new order for: John Doe
Added Small Espresso - $2.5 to order
Added Medium Latte - $4.0 to order
Original total: $6.5
Applying discount: Students get 15% off
Applied student discount (15%): -$0.98

=== ORDER SUMMARY ===
Customer: John Doe
Items:
  - Small Espresso - $2.5
  - Medium Latte - $4.0
Total: $5.525
==================

Processing payment of $5.525 via Credit Card
Payment successful!

=== RECEIPT ===
Thank you, John Doe!
Amount paid: $5.525
Have a great day!
===============

📱 Sending notification to John Doe
   Message: Your coffee order is ready for pickup!
   Email sent to: john@email.com

=== SCENARIO 2: VIP Customer ===
[Shows VIP discount and special offers...]

=== SCENARIO 3: Percentage Discount ===
[Shows 20% percentage discount...]
```

## Key Benefits Demonstrated

### Easy Testing
Each service class has a single responsibility, making unit tests simple:
```java
@Test
public void testStudentDiscount() {
    Order order = new Order(customer);
    order.addCoffee(new Coffee("Latte", 4.0, "Medium"));
    
    Discount discount = new StudentDiscount();
    discount.applyDiscount(order);
    
    assertEquals(3.4, order.getTotal()); // 4.0 - 15% = 3.4
}
```

### Easy Extension  
Adding new features requires minimal changes:
- **New discount type**: Create class implementing `Discount`
- **New notification method**: Add method to `NotificationService`  
- **New payment type**: Extend `PaymentService`

### Real-World Scalability
This structure scales well for enterprise applications:
- Services can be moved to separate microservices
- Interfaces enable easy mocking for tests
- Clean separation allows team members to work on different parts

## Summary
This coffee shop demonstrates how SOLID principles create maintainable, extensible code that's easy to understand and modify. Each principle contributes to a robust design that can grow with business requirements.

---

**Lab completed by:** Andi Blindu (FAF-233)  
**Date:** October 17, 2025  
**Total files created:** 11  
**SOLID principles demonstrated:** All 5 (S, O, L, I, D)
