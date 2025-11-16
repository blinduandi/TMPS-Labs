package behavioral.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Game logger observer
 * Logs all game events with timestamps for debugging and analysis
 */
public class GameLogger implements GameEventObserver {
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private String getTimestamp() {
        return LocalDateTime.now().format(timeFormatter);
    }
    
    @Override
    public void onPlayerJoined(String playerName, String playerClass) {
        System.out.println("📝 [LOG " + getTimestamp() + "] PLAYER_JOINED: " + playerName + 
                         " (class: " + playerClass + ")");
    }
    
    @Override
    public void onPlayerLevelUp(String playerName, int newLevel) {
        System.out.println("📝 [LOG " + getTimestamp() + "] LEVEL_UP: " + playerName + 
                         " advanced to level " + newLevel);
    }
    
    @Override
    public void onCombatStarted(String attacker, String defender) {
        System.out.println("📝 [LOG " + getTimestamp() + "] COMBAT_START: " + attacker + 
                         " initiated combat with " + defender);
    }
    
    @Override
    public void onCombatEnded(String winner, String loser, int damage) {
        System.out.println("📝 [LOG " + getTimestamp() + "] COMBAT_END: " + winner + 
                         " defeated " + loser + " (damage: " + damage + ")");
    }
    
    @Override
    public void onItemEquipped(String playerName, String itemName) {
        System.out.println("📝 [LOG " + getTimestamp() + "] ITEM_EQUIPPED: " + playerName + 
                         " equipped " + itemName);
    }
    
    @Override
    public void onGameEvent(String eventType, String message) {
        System.out.println("📝 [LOG " + getTimestamp() + "] " + eventType + ": " + message);
    }
}