package behavioral.observer;

/**
 * Observer interface for game events
 * Allows objects to be notified of game state changes
 */
public interface GameEventObserver {
    void onPlayerJoined(String playerName, String playerClass);
    void onPlayerLevelUp(String playerName, int newLevel);
    void onCombatStarted(String attacker, String defender);
    void onCombatEnded(String winner, String loser, int damage);
    void onItemEquipped(String playerName, String itemName);
    void onGameEvent(String eventType, String message);
}