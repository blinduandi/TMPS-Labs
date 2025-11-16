package models;

/**
 * Concrete Sword implementation
 */
public class Sword extends BaseWeapon {
    
    public Sword(String name, int damage, String description, double cost) {
        super(name, damage, "Melee", description, cost);
    }
    
    @Override
    public void use() {
        System.out.println("⚔️ Swinging " + name + " with a powerful slash!");
        System.out.println("💥 Dealing " + damage + " melee damage!");
    }
    
    @Override
    public String getSpecialAbility() {
        return "Critical Strike - 20% chance for double damage";
    }
}

/**
 * Concrete Bow implementation
 */
class Bow extends BaseWeapon {
    
    public Bow(String name, int damage, String description, double cost) {
        super(name, damage, "Ranged", description, cost);
    }
    
    @Override
    public void use() {
        System.out.println("🏹 Drawing " + name + " and releasing arrow!");
        System.out.println("🎯 Dealing " + damage + " ranged damage!");
    }
    
    @Override
    public String getSpecialAbility() {
        return "Piercing Shot - Can hit multiple enemies in a line";
    }
}

/**
 * Concrete Staff implementation
 */
class Staff extends BaseWeapon {
    
    public Staff(String name, int damage, String description, double cost) {
        super(name, damage, "Magic", description, cost);
    }
    
    @Override
    public void use() {
        System.out.println("🪄 Channeling magic through " + name + "!");
        System.out.println("✨ Casting spell for " + damage + " magical damage!");
    }
    
    @Override
    public String getSpecialAbility() {
        return "Mana Regeneration - Restores mana with each successful hit";
    }
}