package com.faf.solid.vehicle;

import java.math.BigDecimal;

/**
 * Vehicle base class - defines common behavior for all vehicles
 * Liskov Substitution Principle: All subclasses must honor the contracts defined here
 */
public abstract class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected BigDecimal price;
    protected double fuelLevel; // 0.0 to 100.0 (percentage)
    protected double maxFuelCapacity; // in gallons
    protected boolean engineRunning;
    
    public Vehicle(String make, String model, int year, BigDecimal price, double maxFuelCapacity) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.maxFuelCapacity = maxFuelCapacity;
        this.fuelLevel = 100.0; // Start with full tank
        this.engineRunning = false;
    }
    
    /**
     * Start the vehicle engine
     * Contract: Must set engineRunning to true if successful
     * @return true if engine started successfully
     */
    public boolean startEngine() {
        if (fuelLevel <= 0) {
            System.out.println("Cannot start engine - no fuel!");
            return false;
        }
        
        engineRunning = true;
        System.out.println(getVehicleInfo() + " engine started.");
        return true;
    }
    
    /**
     * Stop the vehicle engine
     * Contract: Must set engineRunning to false
     */
    public void stopEngine() {
        engineRunning = false;
        System.out.println(getVehicleInfo() + " engine stopped.");
    }
    
    /**
     * Move the vehicle a certain distance
     * Contract: Must consume fuel and return actual distance traveled
     * @param requestedDistance distance to travel in miles
     * @return actual distance traveled (may be less if fuel runs out)
     */
    public abstract double move(double requestedDistance);
    
    /**
     * Get fuel efficiency for this vehicle
     * Contract: Must return miles per gallon (positive value)
     * @return miles per gallon
     */
    public abstract double getFuelEfficiency();
    
    /**
     * Get vehicle type description
     * @return string describing the vehicle type
     */
    public abstract String getVehicleType();
    
    /**
     * Refuel the vehicle
     * Contract: Cannot exceed maxFuelCapacity, cannot go below 0
     * @param gallons amount of fuel to add
     * @return actual gallons added
     */
    public double refuel(double gallons) {
        if (gallons < 0) {
            throw new IllegalArgumentException("Cannot add negative fuel");
        }
        
        double currentFuelGallons = (fuelLevel / 100.0) * maxFuelCapacity;
        double maxCanAdd = maxFuelCapacity - currentFuelGallons;
        double actualAdded = Math.min(gallons, maxCanAdd);
        
        double newFuelGallons = currentFuelGallons + actualAdded;
        fuelLevel = (newFuelGallons / maxFuelCapacity) * 100.0;
        
        System.out.println("Added " + actualAdded + " gallons. Fuel level: " + 
                         String.format("%.1f%%", fuelLevel));
        return actualAdded;
    }
    
    /**
     * Calculate fuel consumption for a given distance
     * @param distance distance in miles
     * @return fuel needed in gallons
     */
    protected double calculateFuelConsumption(double distance) {
        return distance / getFuelEfficiency();
    }
    
    /**
     * Consume fuel from the tank
     * @param gallons amount to consume
     * @return true if enough fuel was available
     */
    protected boolean consumeFuel(double gallons) {
        double currentFuelGallons = (fuelLevel / 100.0) * maxFuelCapacity;
        if (currentFuelGallons >= gallons) {
            double newFuelGallons = currentFuelGallons - gallons;
            fuelLevel = (newFuelGallons / maxFuelCapacity) * 100.0;
            return true;
        }
        return false;
    }
    
    public String getVehicleInfo() {
        return year + " " + make + " " + model;
    }
    
    // Getters
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public BigDecimal getPrice() { return price; }
    public double getFuelLevel() { return fuelLevel; }
    public double getMaxFuelCapacity() { return maxFuelCapacity; }
    public boolean isEngineRunning() { return engineRunning; }
    
    @Override
    public String toString() {
        return getVehicleType() + ": " + getVehicleInfo() + 
               " (Fuel: " + String.format("%.1f%%", fuelLevel) + 
               ", Engine: " + (engineRunning ? "On" : "Off") + ")";
    }
}