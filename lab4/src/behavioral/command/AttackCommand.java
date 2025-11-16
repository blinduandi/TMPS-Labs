package behavioral.command;

import domain.GamePlayer;

/**
 * Command for simulating combat between players
 */
public class AttackCommand implements GameCommand {
    private final GamePlayer attacker;
    private final GamePlayer target;
    private int damageDealt = 0;
    private boolean executed = false;
    
    public AttackCommand(GamePlayer attacker, GamePlayer target) {
        this.attacker = attacker;
        this.target = target;
    }
    
    @Override
    public void execute() {
        if (!executed) {
            // Calculate damage based on attacker's level and some randomness
            damageDealt = (attacker.getLevel() * 10) + (int)(Math.random() * 20) + 5;
            
            int targetOldHealth = target.getHealth();
            target.takeDamage(damageDealt);
            executed = true;
            
            System.out.println("⚔️ " + attacker.getName() + " attacks " + target.getName() + 
                             " for " + damageDealt + " damage!");
            System.out.println("💔 " + target.getName() + " health: " + targetOldHealth + 
                             " → " + target.getHealth());
        }
    }
    
    @Override
    public void undo() {
        if (executed) {
            // Restore target's health
            target.heal(damageDealt);
            System.out.println("↩️ Attack undone: " + target.getName() + " health restored by " + damageDealt);
            executed = false;
        }
    }
    
    @Override
    public String getDescription() {
        return attacker.getName() + " attacks " + target.getName();
    }
    
    @Override
    public boolean canUndo() {
        return true; // Combat can be undone for demonstration
    }
}