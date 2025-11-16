package behavioral.command;

import domain.GamePlayer;

/**
 * Command for leveling up a player
 */
public class LevelUpCommand implements GameCommand {
    private final GamePlayer player;
    private final int levelsToGain;
    private boolean executed = false;
    
    public LevelUpCommand(GamePlayer player, int levelsToGain) {
        this.player = player;
        this.levelsToGain = levelsToGain;
    }
    
    @Override
    public void execute() {
        if (!executed) {
            int oldLevel = player.getLevel();
            player.levelUp(levelsToGain);
            executed = true;
            System.out.println("📈 " + player.getName() + " leveled up: " + oldLevel + " → " + player.getLevel());
        }
    }
    
    @Override
    public void undo() {
        if (executed) {
            int oldLevel = player.getLevel();
            player.levelDown(levelsToGain);
            executed = false;
            System.out.println("📉 " + player.getName() + " level reduced: " + oldLevel + " → " + player.getLevel());
        }
    }
    
    @Override
    public String getDescription() {
        return "Level up " + player.getName() + " by " + levelsToGain + " level(s)";
    }
    
    @Override
    public boolean canUndo() {
        return true;
    }
}