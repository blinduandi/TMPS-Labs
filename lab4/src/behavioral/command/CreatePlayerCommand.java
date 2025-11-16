package behavioral.command;

import domain.GamePlayer;

/**
 * Command for creating a new player
 */
public class CreatePlayerCommand implements GameCommand {
    private final String playerName;
    private final String playerClass;
    private final int health;
    private final int mana;
    private GamePlayer createdPlayer;
    private boolean executed = false;
    
    public CreatePlayerCommand(String playerName, String playerClass, int health, int mana) {
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.health = health;
        this.mana = mana;
    }
    
    @Override
    public void execute() {
        if (!executed) {
            createdPlayer = new GamePlayer(playerName, playerClass, health, mana, 1);
            executed = true;
            System.out.println("👤 Player created: " + playerName + " (" + playerClass + ")");
        }
    }
    
    @Override
    public void undo() {
        if (executed && createdPlayer != null) {
            // In a real game, this would remove the player from the game state
            System.out.println("👤 Player removed: " + playerName);
            executed = false;
        }
    }
    
    @Override
    public String getDescription() {
        return "Create player: " + playerName + " (" + playerClass + ")";
    }
    
    @Override
    public boolean canUndo() {
        return true;
    }
    
    public GamePlayer getCreatedPlayer() {
        return createdPlayer;
    }
}