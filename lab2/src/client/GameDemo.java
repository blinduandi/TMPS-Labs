package client;

import domain.GameManager;
import factory.*;
import models.*;

/**
 * GameDemo - Client class demonstrating all Creational Design Patterns
 * 
 * Patterns demonstrated:
 * 1. Singleton - GameManager (single game instance management)
 * 2. Builder - Player (complex object construction)
 * 3. Factory Method - WeaponFactory (creating different weapon types)
 * 4. Abstract Factory - GameElementFactory (creating themed game element families)
 */
public class GameDemo {
    
    public static void main(String[] args) {
        System.out.println("🎮 === CREATIONAL DESIGN PATTERNS GAME DEMO ===\n");
        
        demonstrateSingletonPattern();
        demonstrateBuilderPattern();
        demonstrateFactoryMethodPattern();
        demonstrateAbstractFactoryPattern();
        
        // Final game status
        GameManager.INSTANCE.showGameStatus();
        GameManager.INSTANCE.stopGame();
    }
    
    /**
     * Demonstrates Singleton pattern with GameManager
     * Ensures only one game instance exists throughout the application
     */
    private static void demonstrateSingletonPattern() {
        System.out.println("🔹 === SINGLETON PATTERN DEMO ===");
        System.out.println("GameManager ensures only one game instance exists\n");
        
        // Get the singleton instance
        GameManager gameManager = GameManager.INSTANCE;
        gameManager.startGame();
        gameManager.setGameMode("RPG Adventure");
        
        // Prove it's the same instance
        GameManager sameInstance = GameManager.INSTANCE;
        System.out.println("🔍 Same instance? " + (gameManager == sameInstance));
        
        sameInstance.levelUp();
        System.out.println("✅ Singleton pattern: One GameManager controls all game state\n");
    }
    
    /**
     * Demonstrates Builder pattern for complex Player creation
     * Allows flexible construction of Player objects with many optional parameters
     */
    private static void demonstrateBuilderPattern() {
        System.out.println("🔹 === BUILDER PATTERN DEMO ===");
        System.out.println("Builder pattern allows flexible Player creation with optional parameters\n");
        
        // Create different players using Builder pattern
        Player warrior = new Player.PlayerBuilder("Aragorn", "Warrior")
                .level(25)
                .health(200)
                .strength(30)
                .agility(15)
                .weapon("Steel Sword")
                .armor("Chain Mail")
                .build();
        
        Player mage = new Player.PlayerBuilder("Gandalf", "Mage")
                .level(30)
                .health(120)
                .mana(200)
                .intelligence(35)
                .weapon("Magic Staff")
                .armor("Robes of Power")
                .build();
        
        Player archer = new Player.PlayerBuilder("Legolas", "Archer")
                .level(22)
                .health(150)
                .agility(40)
                .strength(20)
                .weapon("Elven Bow")
                .build(); // Some parameters use defaults
        
        // Add players to game
        GameManager.INSTANCE.addPlayer(warrior);
        GameManager.INSTANCE.addPlayer(mage);
        GameManager.INSTANCE.addPlayer(archer);
        
        System.out.println("👤 Created Players:");
        System.out.println("  • " + warrior.getName() + " (Level " + warrior.getLevel() + " " + warrior.getCharacterClass() + ")");
        System.out.println("  • " + mage.getName() + " (Level " + mage.getLevel() + " " + mage.getCharacterClass() + ")");
        System.out.println("  • " + archer.getName() + " (Level " + archer.getLevel() + " " + archer.getCharacterClass() + ")");
        
        System.out.println("✅ Builder pattern: Flexible object creation with optional parameters\n");
    }
    
    /**
     * Demonstrates Factory Method pattern for weapon creation
     * Different factories create different types of weapons
     */
    private static void demonstrateFactoryMethodPattern() {
        System.out.println("🔹 === FACTORY METHOD PATTERN DEMO ===");
        System.out.println("Factory Method creates different weapon types without specifying exact classes\n");
        
        // Create weapons using different factories
        WeaponFactory swordFactory = WeaponFactoryProvider.getFactory("sword");
        WeaponFactory bowFactory = WeaponFactoryProvider.getFactory("bow");
        WeaponFactory staffFactory = WeaponFactoryProvider.getFactory("staff");
        
        // Use factory methods to create weapons
        Weapon excalibur = swordFactory.createAndEnchantWeapon("Excalibur", 100, "Legendary blade of kings", "Holy Light");
        Weapon elvishBow = bowFactory.createAndEnchantWeapon("Elvish Bow", 85, "Bow crafted by elven masters", "Wind Speed");
        Weapon wizardStaff = staffFactory.createAndEnchantWeapon("Wizard Staff", 120, "Staff of ancient wisdom", "Mana Boost");
        
        System.out.println("\n🗡️ Created Weapons:");
        System.out.println("  • " + excalibur);
        System.out.println("    Special: " + excalibur.getSpecialAbility());
        System.out.println("  • " + elvishBow);
        System.out.println("    Special: " + elvishBow.getSpecialAbility());
        System.out.println("  • " + wizardStaff);
        System.out.println("    Special: " + wizardStaff.getSpecialAbility());
        
        // Demonstrate weapon usage
        System.out.println("\n⚔️ Weapon Usage Demo:");
        excalibur.use();
        elvishBow.use();
        wizardStaff.use();
        
        System.out.println("✅ Factory Method: Different factories create different weapon types\n");
    }
    
    /**
     * Demonstrates Abstract Factory pattern for themed game elements
     * Creates families of related objects with consistent themes
     */
    private static void demonstrateAbstractFactoryPattern() {
        System.out.println("🔹 === ABSTRACT FACTORY PATTERN DEMO ===");
        System.out.println("Abstract Factory creates families of themed game elements\n");
        
        // Create different themed factories
        GameElementFactory medievalFactory = GameElementFactoryProvider.getFactory("medieval");
        GameElementFactory fantasyFactory = GameElementFactoryProvider.getFactory("fantasy");
        GameElementFactory modernFactory = GameElementFactoryProvider.getFactory("modern");
        
        // Create complete themed sets
        medievalFactory.createCompleteSet("Knight");
        fantasyFactory.createCompleteSet("Wizard");
        modernFactory.createCompleteSet("Soldier");
        
        // Demonstrate individual themed elements
        System.out.println("🎭 Individual Themed Elements:");
        
        Weapon medievalSword = medievalFactory.createThemeWeapon("sword", "Knight's Blade", 80);
        Armor fantasyArmor = fantasyFactory.createThemeArmor("robe", "Arcane Robes", 40);
        Potion modernPotion = modernFactory.createThemePotion("healing", "Nano-Stim", 150);
        
        System.out.println("  • " + medievalSword);
        System.out.println("  • " + fantasyArmor);
        System.out.println("  • " + modernPotion);
        
        System.out.println("\n🎨 Usage Demo:");
        medievalSword.use();
        fantasyArmor.wear();
        modernPotion.use();
        
        System.out.println("✅ Abstract Factory: Consistent themed families of related objects\n");
    }
}