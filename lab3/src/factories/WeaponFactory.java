package factories;

import models.*;

/**
 * Weapon Factory - hides Factory Method pattern implementation
 * Used internally by facade, not exposed to client
 */
public class WeaponFactory {
    
    public static WeaponComponent createWeapon(String type, String name, int damage, String description, double cost) {
        switch (type.toLowerCase()) {
            case "sword":
                return new Sword(name, damage, description, cost);
            case "bow":
                return new Bow(name, damage, description, cost);
            case "staff":
                return new Staff(name, damage, description, cost);
            default:
                throw new IllegalArgumentException("Unknown weapon type: " + type);
        }
    }
    
    // Predefined weapon creation methods
    public static WeaponComponent createBasicSword() {
        return createWeapon("sword", "Iron Sword", 25, "A sturdy iron blade", 100.0);
    }
    
    public static WeaponComponent createBasicBow() {
        return createWeapon("bow", "Hunting Bow", 20, "A reliable wooden bow", 80.0);
    }
    
    public static WeaponComponent createBasicStaff() {
        return createWeapon("staff", "Apprentice Staff", 18, "A simple wooden staff", 90.0);
    }
    
    // Advanced weapons
    public static WeaponComponent createAdvancedWeapon(String type) {
        switch (type.toLowerCase()) {
            case "sword":
                return createWeapon("sword", "Masterwork Blade", 45, "An expertly crafted steel sword", 300.0);
            case "bow":
                return createWeapon("bow", "Elven Longbow", 40, "An elegant bow of elven make", 280.0);
            case "staff":
                return createWeapon("staff", "Archmage Staff", 42, "A staff imbued with ancient magic", 320.0);
            default:
                throw new IllegalArgumentException("Unknown weapon type: " + type);
        }
    }
}