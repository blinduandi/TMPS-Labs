package behavioral.strategy;

/**
 * Result of combat execution
 * Contains outcome details and damage information
 */
public class CombatResult {
    private final String winner;
    private final String loser;
    private final int damageDealt;
    private final String combatLog;
    private final boolean isCritical;
    
    public CombatResult(String winner, String loser, int damageDealt, String combatLog, boolean isCritical) {
        this.winner = winner;
        this.loser = loser;
        this.damageDealt = damageDealt;
        this.combatLog = combatLog;
        this.isCritical = isCritical;
    }
    
    // Getters
    public String getWinner() { return winner; }
    public String getLoser() { return loser; }
    public int getDamageDealt() { return damageDealt; }
    public String getCombatLog() { return combatLog; }
    public boolean isCritical() { return isCritical; }
    
    @Override
    public String toString() {
        return String.format("Combat Result: %s defeated %s (Damage: %d%s)", 
                           winner, loser, damageDealt, isCritical ? " - CRITICAL!" : "");
    }
}