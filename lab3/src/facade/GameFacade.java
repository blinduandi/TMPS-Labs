package facade;

import models.GameCharacter;
import models.WeaponComponent;
import factories.CharacterFactory;
import factories.WeaponFactory;
import decorators.*;
import domain.GameSession;

/**
 * FACADE PATTERN Implementation
 * 
 * GameFacade - provides simplified interface to complex game subsystems
 * Hides complexity of creational patterns, decorators, and game management
 */
public class GameFacade {
    private GameSession gameSession;
    
    public GameFacade() {
        this.gameSession = GameSession.getInstance();
    }
    
    /**
     * Start a new game session - simplified interface
     */
    public void startNewGame(String gameName) {
        System.out.println("🎮 Starting new game: " + gameName);
        gameSession.startGame(gameName);
    }
    
    /**
     * Create and add player to game - hides builder pattern complexity
     */
    public GameCharacter createPlayer(String name, String characterClass) {
        System.out.println("👤 Creating new " + characterClass + " named " + name + "...");
        
        GameCharacter character;
        switch (characterClass.toLowerCase()) {
            case "warrior":
                character = CharacterFactory.createWarrior(name);
                break;
            case "archer":
                character = CharacterFactory.createArcher(name);
                break;
            case "mage":
                character = CharacterFactory.createMage(name);
                break;
            default:
                character = CharacterFactory.createCustomCharacter(name, characterClass, 1, "sword");
        }
        
        gameSession.addPlayer(character);
        System.out.println("✅ Character created and added to game!");
        return character;
    }
    
    /**
     * Enhance weapon with magical properties - simplified decorator usage
     */
    public WeaponComponent enhanceWeapon(WeaponComponent weapon, String enhancementType) {
        System.out.println("🔮 Enhancing " + weapon.getName() + " with " + enhancementType + " magic...");
        
        WeaponComponent enhanced;
        switch (enhancementType.toLowerCase()) {
            case "fire":
                enhanced = new FireEnhancement(weapon);
                break;
            case "ice":
                enhanced = new IceEnhancement(weapon);
                break;
            case "lightning":
                enhanced = new LightningEnhancement(weapon);
                break;
            default:
                System.out.println("❌ Unknown enhancement type: " + enhancementType);
                return weapon;
        }
        
        System.out.println("✨ Enhancement complete! " + enhanced.getName() + 
                         " now deals " + enhanced.getDamage() + " damage!");
        return enhanced;
    }
    
    /**
     * Create and enhance weapon in one step - combines factory + decorator
     */
    public WeaponComponent createEnhancedWeapon(String weaponType, String enhancement) {
        System.out.println("⚒️ Crafting enhanced " + weaponType + " with " + enhancement + " enhancement...");
        
        // Use factory to create base weapon
        WeaponComponent baseWeapon = WeaponFactory.createAdvancedWeapon(weaponType);
        
        // Apply enhancement using decorator
        WeaponComponent enhancedWeapon = enhanceWeapon(baseWeapon, enhancement);
        
        return enhancedWeapon;
    }
    
    /**
     * Equip character with enhanced weapon - complete workflow
     */
    public void equipCharacterWithEnhancedWeapon(GameCharacter character, String weaponType, String enhancement) {
        System.out.println("🎯 Equipping " + character.getName() + " with enhanced weapon...");
        
        WeaponComponent enhancedWeapon = createEnhancedWeapon(weaponType, enhancement);
        character.setWeapon(enhancedWeapon);
        
        System.out.println("✅ " + character.getName() + " is now wielding " + enhancedWeapon.getName() + "!");
    }
    
    /**
     * Simulate combat encounter - orchestrates multiple subsystems
     */
    public void simulateCombat(GameCharacter character, String enemyName) {
        System.out.println("\n⚔️ COMBAT ENCOUNTER ⚔️");
        System.out.println("🏟️ " + character.getName() + " vs " + enemyName);
        System.out.println();
        
        // Show character status
        System.out.println("👤 Fighter: " + character.toString());
        System.out.println("🗡️ Weapon: " + character.getWeapon().toString());
        System.out.println();
        
        // Perform attack
        character.performAttack();
        
        System.out.println("💀 " + enemyName + " takes " + character.getWeapon().getDamage() + " damage!");
        System.out.println("🏆 " + character.getName() + " wins the encounter!");
        System.out.println();
    }
    
    /**
     * End game session
     */
    public void endGame() {
        gameSession.endGame();
        System.out.println("👋 Thanks for playing!");
    }
    
    /**
     * Show current game status - aggregates information from multiple subsystems
     */
    public void showGameStatus() {
        gameSession.showStatus();
    }
    
    /**
     * Get weapon shop catalog - simplified factory interface
     */
    public void showWeaponCatalog() {
        System.out.println("🏪 WEAPON SHOP CATALOG");
        System.out.println("Basic Weapons:");
        System.out.println("  - " + WeaponFactory.createBasicSword());
        System.out.println("  - " + WeaponFactory.createBasicBow());
        System.out.println("  - " + WeaponFactory.createBasicStaff());
        System.out.println();
        System.out.println("Advanced Weapons:");
        System.out.println("  - " + WeaponFactory.createAdvancedWeapon("sword"));
        System.out.println("  - " + WeaponFactory.createAdvancedWeapon("bow"));
        System.out.println("  - " + WeaponFactory.createAdvancedWeapon("staff"));
        System.out.println();
        System.out.println("Available Enhancements: Fire, Ice, Lightning");
        System.out.println();
    }
}