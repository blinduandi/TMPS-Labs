import facade.SimpleGameFacade;
import decorator.*;
import adapter.*;
import models.GameCharacter;
import models.WeaponInterface;

/**
 * Lab 3 Client - Demonstrates all Structural Design Patterns
 * 
 * Patterns Demonstrated:
 * 1. Decorator Pattern - Dynamic weapon enhancement
 * 2. Facade Pattern - Simplified game management interface
 * 3. Adapter Pattern - Integration with legacy systems
 */
public class Lab3Client {
    
    public static void main(String[] args) {
        System.out.println("🏗️ LAB 3: STRUCTURAL DESIGN PATTERNS DEMONSTRATION");
        System.out.println("=" .repeat(60));
        
        // Initialize facade (hides complex creation patterns from Lab 2)
        SimpleGameFacade gameFacade = new SimpleGameFacade();
        
        demonstrateDecoratorPattern();
        demonstrateFacadePattern(gameFacade);
        demonstrateAdapterPattern(gameFacade);
        
        System.out.println("\n🎯 LAB 3 COMPLETED SUCCESSFULLY!");
        System.out.println("All structural patterns demonstrated with integration to creational patterns.");
    }
    
    /**
     * 1. DECORATOR PATTERN DEMONSTRATION
     * Shows dynamic enhancement of weapons without changing original classes
     */
    private static void demonstrateDecoratorPattern() {
        System.out.println("\n🎨 1. DECORATOR PATTERN - Dynamic Weapon Enhancement");
        System.out.println("-".repeat(50));
        
        // Start with basic weapon
        WeaponInterface basicSword = new BasicWeapon("Iron Sword", 50);
        System.out.println("Base weapon: " + basicSword.getDescription() 
                         + " (Damage: " + basicSword.getDamage() + ")");
        
        // Add fire enhancement
        WeaponInterface fireSword = new FireEnhancement(basicSword);
        System.out.println("After Fire: " + fireSword.getDescription() 
                         + " (Damage: " + fireSword.getDamage() + ")");
        
        // Add poison enhancement on top of fire
        WeaponInterface poisonFireSword = new PoisonEnhancement(fireSword);
        System.out.println("After Poison: " + poisonFireSword.getDescription() 
                         + " (Damage: " + poisonFireSword.getDamage() + ")");
        
        // Add critical enhancement on top of everything
        WeaponInterface ultimateWeapon = new CriticalEnhancement(poisonFireSword);
        System.out.println("Final weapon: " + ultimateWeapon.getDescription() 
                         + " (Damage: " + ultimateWeapon.getDamage() + ")");
        
        System.out.println("✅ Decorator Pattern: Successfully enhanced weapon through multiple decorators!");
    }
    
    /**
     * 2. FACADE PATTERN DEMONSTRATION  
     * Shows simplified interface hiding complex creational patterns
     */
    private static void demonstrateFacadePattern(SimpleGameFacade facade) {
        System.out.println("\n🏢 2. FACADE PATTERN - Simplified Game Management");
        System.out.println("-".repeat(50));
        
        // Client doesn't need to know about Singleton, Factory, Builder patterns
        facade.startNewGame("Epic Adventure");
        
        // Simple player creation (hides Builder pattern)
        GameCharacter hero = facade.createPlayer("Aragorn", "warrior");
        GameCharacter mage = facade.createPlayer("Gandalf", "mage");
        
        // Simple weapon enhancement (hides Factory + Decorator patterns)
        WeaponInterface basicSword = new BasicWeapon("Legendary Sword", 60);
        WeaponInterface enhancedSword = facade.enhanceWeapon(basicSword, "fire");
        System.out.println("Enhanced weapon created: " + enhancedSword.getDescription() 
                         + " (Damage: " + enhancedSword.getDamage() + ")");
        
        // Simple combat simulation
        facade.simulateCombat("Aragorn", "Gandalf");
        
        facade.showGameStatus();
        facade.endGame();
        
        System.out.println("✅ Facade Pattern: Complex subsystems hidden behind simple interface!");
    }
    
    /**
     * 3. ADAPTER PATTERN DEMONSTRATION
     * Shows integration with incompatible external systems
     */
    private static void demonstrateAdapterPattern(SimpleGameFacade facade) {
        System.out.println("\n🔌 3. ADAPTER PATTERN - External System Integration");
        System.out.println("-".repeat(50));
        
        // Demonstrate Legacy Player System Adapter
        System.out.println("📜 Integrating Legacy Player System:");
        LegacyPlayerSystem legacySystem = new LegacyPlayerSystem();
        LegacyPlayerAdapter playerAdapter = new LegacyPlayerAdapter(legacySystem);
        
        // Import legacy players
        GameCharacter[] legacyPlayers = playerAdapter.importAllLegacyPlayers();
        
        facade.startNewGame("Legacy Integration Test");
        
        for (GameCharacter player : legacyPlayers) {
            System.out.println("  Imported: " + player);
        }
        
        // Demonstrate Audio System Adapter
        System.out.println("\n🔊 Integrating Third-Party Audio System:");
        ThirdPartyAudioLib audioLib = new ThirdPartyAudioLib();
        GameAudioManager audioManager = new AudioSystemAdapter(audioLib);
        
        // Use adapted audio interface
        audioManager.setVolume(75);
        audioManager.playBackgroundMusic("epic_battle_theme");
        audioManager.playSoundEffect("sword_clash");
        audioManager.playSoundEffect("magic_spell");
        audioManager.stopMusic();
        
        facade.endGame();
        
        System.out.println("✅ Adapter Pattern: Successfully integrated incompatible external systems!");
    }
}