package behavioral.strategy;

/**
 * Context class for managing combat strategies
 * Allows runtime switching between different combat algorithms
 */
public class CombatContext {
    private CombatStrategy strategy;
    
    public CombatContext(CombatStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Change combat strategy at runtime
     */
    public void setStrategy(CombatStrategy strategy) {
        this.strategy = strategy;
        System.out.println("🔄 Combat strategy changed to: " + strategy.getStrategyName());
    }
    
    /**
     * Execute combat using current strategy
     */
    public CombatResult executeCombat(domain.GamePlayer attacker, domain.GamePlayer defender) {
        System.out.println("⚔️ Executing combat: " + attacker.getName() + " vs " + defender.getName());
        System.out.println("📋 Strategy: " + strategy.getStrategyName() + " - " + strategy.getDescription());
        
        CombatResult result = strategy.executeCombat(attacker, defender);
        
        System.out.println("🏆 Result: " + result.toString());
        return result;
    }
    
    /**
     * Get current strategy info
     */
    public String getCurrentStrategyInfo() {
        return strategy.getStrategyName() + ": " + strategy.getDescription();
    }
    
    /**
     * Get current strategy
     */
    public CombatStrategy getStrategy() {
        return strategy;
    }
}