package facade;

import models.GameCharacter;
import models.WeaponInterface;
import domain.GameSession;
import decorator.*;

/**
 * Facade Pattern - Simplified interface for complex game operations
 * Hides the complexity of creational patterns (Singleton, Factory, Builder) from Lab 2
 */
public class SimpleGameFacade {
    private GameSession gameSession;
    
    public SimpleGameFacade() {
        this.gameSession = GameSession.getInstance();
    }
    
    /**
     * Start a new game - hides Singleton pattern
     */
    public void startNewGame(String gameName) {
        gameSession.startGame(gameName);
        System.out.println("🎮 New game '" + gameName + "' started via facade!");
    }
    
    /**
     * Create player - hides Builder pattern complexity
     */
    public GameCharacter createPlayer(String name, String characterClass) {
        // Using simple constructor instead of complex builder pattern
        GameCharacter player = new GameCharacter(name, characterClass, 100, 50, 1);
        gameSession.addPlayer(player);
        System.out.println("👤 Player created: " + player.getName() + " (" + player.getCharacterClass() + ")");
        return player;
    }
    
    /**
     * Enhance weapon - hides Decorator pattern complexity
     */
    public WeaponInterface enhanceWeapon(WeaponInterface weapon, String enhancement) {
        WeaponInterface enhanced = weapon;
        
        switch (enhancement.toLowerCase()) {
            case "fire":
                enhanced = new FireEnhancement(weapon);
                break;
            case "poison":
                enhanced = new PoisonEnhancement(weapon);
                break;
            case "critical":
                enhanced = new CriticalEnhancement(weapon);
                break;
            default:
                System.out.println("Unknown enhancement: " + enhancement);
        }
        
        System.out.println("⚔️ Weapon enhanced with " + enhancement + ": " + enhanced.getDescription());
        return enhanced;
    }
    
    /**
     * Simulate combat - simplified interface
     */
    public void simulateCombat(String player1Name, String player2Name) {
        System.out.println("⚔️ Combat simulation between " + player1Name + " and " + player2Name);
        System.out.println("💥 Epic battle ensues!");
        System.out.println("🏆 Battle completed!");
    }
    
    /**
     * Show game status - hides session management
     */
    public void showGameStatus() {
        gameSession.showStatus();
    }
    
    /**
     * End game - hides session cleanup
     */
    public void endGame() {
        gameSession.endGame();
    }
}