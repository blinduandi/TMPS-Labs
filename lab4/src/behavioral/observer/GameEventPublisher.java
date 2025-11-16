package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class for Observer pattern
 * Manages game events and notifies registered observers
 */
public class GameEventPublisher {
    private final List<GameEventObserver> observers = new ArrayList<>();
    private static GameEventPublisher instance;
    
    // Singleton pattern for centralized event management
    public static synchronized GameEventPublisher getInstance() {
        if (instance == null) {
            instance = new GameEventPublisher();
        }
        return instance;
    }
    
    private GameEventPublisher() {}
    
    /**
     * Register observer to receive notifications
     */
    public void subscribe(GameEventObserver observer) {
        observers.add(observer);
        System.out.println("📋 Observer registered: " + observer.getClass().getSimpleName());
    }
    
    /**
     * Unregister observer from notifications
     */
    public void unsubscribe(GameEventObserver observer) {
        observers.remove(observer);
        System.out.println("❌ Observer unregistered: " + observer.getClass().getSimpleName());
    }
    
    /**
     * Notify all observers of player joining
     */
    public void notifyPlayerJoined(String playerName, String playerClass) {
        System.out.println("📢 Broadcasting: Player joined event");
        for (GameEventObserver observer : observers) {
            observer.onPlayerJoined(playerName, playerClass);
        }
    }
    
    /**
     * Notify all observers of player level up
     */
    public void notifyPlayerLevelUp(String playerName, int newLevel) {
        System.out.println("📢 Broadcasting: Player level up event");
        for (GameEventObserver observer : observers) {
            observer.onPlayerLevelUp(playerName, newLevel);
        }
    }
    
    /**
     * Notify all observers of combat start
     */
    public void notifyCombatStarted(String attacker, String defender) {
        System.out.println("📢 Broadcasting: Combat started event");
        for (GameEventObserver observer : observers) {
            observer.onCombatStarted(attacker, defender);
        }
    }
    
    /**
     * Notify all observers of combat end
     */
    public void notifyCombatEnded(String winner, String loser, int damage) {
        System.out.println("📢 Broadcasting: Combat ended event");
        for (GameEventObserver observer : observers) {
            observer.onCombatEnded(winner, loser, damage);
        }
    }
    
    /**
     * Notify all observers of item equipment
     */
    public void notifyItemEquipped(String playerName, String itemName) {
        System.out.println("📢 Broadcasting: Item equipped event");
        for (GameEventObserver observer : observers) {
            observer.onItemEquipped(playerName, itemName);
        }
    }
    
    /**
     * Generic event notification
     */
    public void notifyGameEvent(String eventType, String message) {
        System.out.println("📢 Broadcasting: " + eventType + " event");
        for (GameEventObserver observer : observers) {
            observer.onGameEvent(eventType, message);
        }
    }
    
    /**
     * Get current number of observers
     */
    public int getObserverCount() {
        return observers.size();
    }
}