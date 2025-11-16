package behavioral.strategy;

import domain.GamePlayer;

/**
 * Strategy interface for combat algorithms
 * Allows different combat behaviors to be used interchangeably
 */
public interface CombatStrategy {
    /**
     * Execute combat between attacker and defender
     * @param attacker The attacking player
     * @param defender The defending player
     * @return Combat result with damage and outcome
     */
    CombatResult executeCombat(GamePlayer attacker, GamePlayer defender);
    
    /**
     * Get strategy name for display
     */
    String getStrategyName();
    
    /**
     * Get strategy description
     */
    String getDescription();
}