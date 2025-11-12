package domain;

import models.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * GameManager - Singleton pattern implementation
 * Ensures only one instance manages the game state
 * Thread-safe implementation using enum singleton pattern
 */
public enum GameManager {
    INSTANCE;
    
    private List<Player> players;
    private String currentGameMode;
    private int gameLevel;
    private boolean gameRunning;
    private long gameStartTime;
    
    // Constructor equivalent for enum singleton
    GameManager() {
        this.players = new ArrayList<>();
        this.currentGameMode = "Adventure";
        this.gameLevel = 1;
        this.gameRunning = false;
    }
    
    public void startGame() {
        gameRunning = true;
        gameStartTime = System.currentTimeMillis();
        System.out.println("🎮 Game Started!");
        System.out.println("📍 Mode: " + currentGameMode);
        System.out.println("🆙 Level: " + gameLevel);
        System.out.println();
    }
    
    public void stopGame() {
        gameRunning = false;
        long duration = (System.currentTimeMillis() - gameStartTime) / 1000;
        System.out.println("🏁 Game Ended!");
        System.out.println("⏱️ Duration: " + duration + " seconds");
        System.out.println();
    }
    
    public void addPlayer(Player player) {
        players.add(player);
        System.out.println("👤 Player '" + player.getName() + "' joined the game!");
    }
    
    public void setGameMode(String mode) {
        this.currentGameMode = mode;
        System.out.println("🎯 Game mode changed to: " + mode);
    }
    
    public void levelUp() {
        this.gameLevel++;
        System.out.println("🆙 Game advanced to level: " + gameLevel);
    }
    
    public void showGameStatus() {
        System.out.println("=== 🎮 GAME STATUS ===");
        System.out.println("Mode: " + currentGameMode);
        System.out.println("Level: " + gameLevel);
        System.out.println("Running: " + (gameRunning ? "Yes" : "No"));
        System.out.println("Players: " + players.size());
        
        if (!players.isEmpty()) {
            System.out.println("\n👥 Active Players:");
            for (int i = 0; i < players.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + players.get(i).getName() + 
                                 " (Level " + players.get(i).getLevel() + ")");
            }
        }
        System.out.println("==================\n");
    }
    
    // Getters
    public List<Player> getPlayers() { return new ArrayList<>(players); }
    public String getCurrentGameMode() { return currentGameMode; }
    public int getGameLevel() { return gameLevel; }
    public boolean isGameRunning() { return gameRunning; }
}