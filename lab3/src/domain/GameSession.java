package domain;

import models.GameCharacter;
import java.util.ArrayList;
import java.util.List;

/**
 * GameSession - Singleton pattern for managing game state
 * Used internally by facade
 */
public class GameSession {
    private static GameSession instance;
    private List<GameCharacter> players;
    private String gameName;
    private boolean gameRunning;
    private long gameStartTime;
    
    private GameSession() {
        this.players = new ArrayList<>();
        this.gameRunning = false;
    }
    
    public static synchronized GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }
    
    public void startGame(String gameName) {
        this.gameName = gameName;
        this.gameRunning = true;
        this.gameStartTime = System.currentTimeMillis();
        System.out.println("🎮 Game '" + gameName + "' started!");
    }
    
    public void endGame() {
        this.gameRunning = false;
        long duration = (System.currentTimeMillis() - gameStartTime) / 1000;
        System.out.println("🏁 Game '" + gameName + "' ended after " + duration + " seconds");
    }
    
    public void addPlayer(GameCharacter player) {
        players.add(player);
    }
    
    public void showStatus() {
        System.out.println("=== GAME STATUS ===");
        System.out.println("Game: " + (gameName != null ? gameName : "No Game"));
        System.out.println("Running: " + gameRunning);
        System.out.println("Players: " + players.size());
        
        if (!players.isEmpty()) {
            System.out.println("Active Players:");
            for (int i = 0; i < players.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + players.get(i));
            }
        }
        System.out.println("===================\n");
    }
    
    public List<GameCharacter> getPlayers() { return new ArrayList<>(players); }
    public boolean isGameRunning() { return gameRunning; }
}