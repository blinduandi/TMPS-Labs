package factory;

import models.*;

/**
 * Abstract WeaponFactory - Factory Method pattern
 * Defines interface for creating weapons without specifying exact classes
 */
public abstract class WeaponFactory {
    
    /**
     * Factory method - subclasses decide which weapon to create
     */
    public abstract Weapon createWeapon(String name, int damage, String description);
    
    /**
     * Template method that uses the factory method
     * Demonstrates how the factory method is used in a larger algorithm
     */
    public Weapon createAndEnchantWeapon(String name, int damage, String description, String enchantment) {
        // Create weapon using factory method
        Weapon weapon = createWeapon(name, damage, description);
        
        // Add enchantment (common logic for all weapon types)
        System.out.println("✨ Enchanting " + weapon.getName() + " with " + enchantment + "!");
        
        return weapon;
    }
}

/**
 * Concrete factory for creating Swords
 */
class SwordFactory extends WeaponFactory {
    @Override
    public Weapon createWeapon(String name, int damage, String description) {
        System.out.println("🔨 Forging a new sword: " + name);
        return new Sword(name, damage, description);
    }
}

/**
 * Concrete factory for creating Bows
 */
class BowFactory extends WeaponFactory {
    @Override
    public Weapon createWeapon(String name, int damage, String description) {
        System.out.println("🪵 Crafting a new bow: " + name);
        return new Bow(name, damage, description);
    }
}

/**
 * Concrete factory for creating Staffs
 */
class StaffFactory extends WeaponFactory {
    @Override
    public Weapon createWeapon(String name, int damage, String description) {
        System.out.println("🔮 Imbuing a new staff: " + name);
        return new Staff(name, damage, description);
    }
}