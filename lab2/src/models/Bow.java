package models;

/**
 * Bow weapon implementation
 * Created by Factory Method pattern
 */
public class Bow extends Weapon {
    
    public Bow(String name, int damage, String description) {
        super(name, damage, "Ranged", description);
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