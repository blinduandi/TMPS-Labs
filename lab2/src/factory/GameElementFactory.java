package factory;

import models.*;

/**
 * Abstract Factory pattern implementation
 * GameElementFactory - creates families of related game objects
 */
public abstract class GameElementFactory {
    
    // Abstract methods for creating family of related products
    public abstract Weapon createThemeWeapon(String weaponType, String name, int damage);
    public abstract Armor createThemeArmor(String armorType, String name, int defense);
    public abstract Potion createThemePotion(String potionType, String name, int potency);
    
    // Template method using abstract factory methods
    public void createCompleteSet(String name) {
        System.out.println("🎨 Creating complete " + getTheme() + " themed set for " + name + ":");
        
        Weapon weapon = createThemeWeapon("sword", name + "'s Blade", 75);
        Armor armor = createThemeArmor("chestplate", name + "'s Armor", 50);
        Potion potion = createThemePotion("healing", name + "'s Elixir", 100);
        
        System.out.println("✅ Complete set created!");
        System.out.println("  • " + weapon);
        System.out.println("  • " + armor);
        System.out.println("  • " + potion);
        System.out.println();
    }
    
    protected abstract String getTheme();
}

/**
 * Medieval themed factory - creates medieval-style game elements
 */
class MedievalFactory extends GameElementFactory {
    
    @Override
    protected String getTheme() {
        return "Medieval";
    }
    
    @Override
    public Weapon createThemeWeapon(String weaponType, String name, int damage) {
        switch (weaponType.toLowerCase()) {
            case "sword":
                return new Sword(name, damage, "Forged steel blade with leather grip");
            case "bow":
                return new Bow(name, damage, "Yew wood bow with iron-tipped arrows");
            case "staff":
                return new Staff(name, damage, "Oak staff with crystal orb");
            default:
                return new Sword(name, damage, "Basic medieval weapon");
        }
    }
    
    @Override
    public Armor createThemeArmor(String armorType, String name, int defense) {
        return new Armor(name, defense, armorType, "Medieval");
    }
    
    @Override
    public Potion createThemePotion(String potionType, String name, int potency) {
        return new Potion(name, "Healing", potency, "Medieval");
    }
}

/**
 * Fantasy themed factory - creates fantasy-style game elements
 */
class FantasyFactory extends GameElementFactory {
    
    @Override
    protected String getTheme() {
        return "Fantasy";
    }
    
    @Override
    public Weapon createThemeWeapon(String weaponType, String name, int damage) {
        switch (weaponType.toLowerCase()) {
            case "sword":
                return new Sword(name, damage, "Enchanted mithril blade glowing with magic");
            case "bow":
                return new Bow(name, damage, "Elven bow that shoots arrows of pure light");
            case "staff":
                return new Staff(name, damage, "Ancient staff crackling with arcane energy");
            default:
                return new Staff(name, damage, "Mystical fantasy weapon");
        }
    }
    
    @Override
    public Armor createThemeArmor(String armorType, String name, int defense) {
        return new Armor(name, defense, armorType, "Fantasy");
    }
    
    @Override
    public Potion createThemePotion(String potionType, String name, int potency) {
        return new Potion(name, "Magical Healing", potency, "Fantasy");
    }
}

/**
 * Modern themed factory - creates modern/sci-fi style game elements  
 */
class ModernFactory extends GameElementFactory {
    
    @Override
    protected String getTheme() {
        return "Modern";
    }
    
    @Override
    public Weapon createThemeWeapon(String weaponType, String name, int damage) {
        switch (weaponType.toLowerCase()) {
            case "sword":
                return new Sword(name, damage, "High-tech plasma sword with energy edge");
            case "bow":
                return new Bow(name, damage, "Compound bow with laser sight and carbon arrows");
            case "staff":
                return new Staff(name, damage, "Tech staff with holographic display and energy core");
            default:
                return new Sword(name, damage, "Advanced modern weapon");
        }
    }
    
    @Override
    public Armor createThemeArmor(String armorType, String name, int defense) {
        return new Armor(name, defense, armorType, "Modern");
    }
    
    @Override
    public Potion createThemePotion(String potionType, String name, int potency) {
        return new Potion(name, "Nano-healing", potency, "Modern");
    }
}