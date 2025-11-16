package behavioral.strategy;

import domain.GamePlayer;

/**
 * Defensive combat strategy - lower damage but more consistent and safer
 */
public class DefensiveCombatStrategy implements CombatStrategy {
    
    @Override
    public CombatResult executeCombat(GamePlayer attacker, GamePlayer defender) {
        System.out.println("🛡️ Using DEFENSIVE combat strategy!");
        
        // Defensive strategy: Lower but more consistent damage
        int baseDamage = attacker.getLevel() * 8; // Lower base damage
        double criticalChance = 0.15; // 15% critical chance
        boolean isCritical = Math.random() < criticalChance;
        
        // Defensive strategy has damage reduction mitigation
        int finalDamage = isCritical ? (int)(baseDamage * 1.5) : baseDamage;
        
        // Less randomness for more consistency
        finalDamage += (int)(Math.random() * 10) - 5;
        finalDamage = Math.max(8, finalDamage); // Higher minimum damage for consistency
        
        String combatLog = String.format("🛡️ %s takes a calculated, defensive approach", attacker.getName());
        if (isCritical) {
            combatLog += String.format(" ⚡ %s finds an opening for a precise strike!", attacker.getName());
        }
        
        defender.takeDamage(finalDamage);
        
        System.out.println(combatLog);
        System.out.println("🎯 Damage dealt: " + finalDamage + (isCritical ? " (Precise hit!)" : " (Steady damage)"));
        
        return new CombatResult(attacker.getName(), defender.getName(), finalDamage, combatLog, isCritical);
    }
    
    @Override
    public String getStrategyName() {
        return "Defensive";
    }
    
    @Override
    public String getDescription() {
        return "Consistent, reliable damage with lower variance";
    }
}