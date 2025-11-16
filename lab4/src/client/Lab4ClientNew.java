package client;

import behavioral.observer.*;
import behavioral.command.*;
import behavioral.strategy.*;
import domain.GamePlayer;

/**
 * Lab 4 Client - Demonstrates all Behavioral Design Patterns
 * 
 * Patterns Demonstrated:
 * 1. Observer Pattern - Game event notifications and tracking
 * 2. Command Pattern - Encapsulated actions with undo/redo functionality  
 * 3. Strategy Pattern - Interchangeable combat algorithms
 */
public class Lab4ClientNew {
    
    public static void main(String[] args) {
        System.out.println("🧠 LAB 4: BEHAVIORAL DESIGN PATTERNS DEMONSTRATION");
        System.out.println("=".repeat(65));
        
        // Initialize behavioral pattern systems
        setupObserverPattern();
        demonstrateCommandPattern();
        demonstrateStrategyPattern();
        demonstrateIntegratedBehavior();
        
        System.out.println("\n🎯 LAB 4 COMPLETED SUCCESSFULLY!");
        System.out.println("All behavioral patterns demonstrated with seamless integration.");
    }
    
    /**
     * 1. OBSERVER PATTERN DEMONSTRATION
     */
    private static void setupObserverPattern() {
        System.out.println("\n👁️ 1. OBSERVER PATTERN - Event-Driven Architecture");
        System.out.println("-".repeat(55));
        
        GameEventPublisher eventPublisher = GameEventPublisher.getInstance();
        
        GameStatisticsTracker statsTracker = new GameStatisticsTracker();
        AchievementSystem achievementSystem = new AchievementSystem();
        GameLogger gameLogger = new GameLogger();
        
        eventPublisher.subscribe(statsTracker);
        eventPublisher.subscribe(achievementSystem);
        eventPublisher.subscribe(gameLogger);
        
        System.out.println("📊 Registered " + eventPublisher.getObserverCount() + " observers");
        
        eventPublisher.notifyGameEvent("GAME_START", "Lab 4 Behavioral Patterns Demo");
        eventPublisher.notifyPlayerJoined("Aragorn", "Warrior");
        eventPublisher.notifyPlayerJoined("Gandalf", "Mage");
        eventPublisher.notifyPlayerLevelUp("Aragorn", 5);
        eventPublisher.notifyItemEquipped("Gandalf", "Staff of Fire");
        
        statsTracker.displayStatistics();
        
        System.out.println("✅ Observer Pattern: Successfully demonstrated event-driven architecture!");
    }
    
    /**
     * 2. COMMAND PATTERN DEMONSTRATION
     */
    private static void demonstrateCommandPattern() {
        System.out.println("\n🔧 2. COMMAND PATTERN - Encapsulated Actions & Undo/Redo");
        System.out.println("-".repeat(55));
        
        GameCommandInvoker commandInvoker = new GameCommandInvoker();
        
        CreatePlayerCommand createWarrior = new CreatePlayerCommand("Boromir", "Warrior", 150, 30);
        CreatePlayerCommand createMage = new CreatePlayerCommand("Saruman", "Mage", 80, 120);
        
        commandInvoker.executeCommand(createWarrior);
        commandInvoker.executeCommand(createMage);
        
        GamePlayer warrior = createWarrior.getCreatedPlayer();
        GamePlayer mage = createMage.getCreatedPlayer();
        
        System.out.println("\n📊 Player Status After Creation:");
        System.out.println(warrior);
        System.out.println(mage);
        
        LevelUpCommand levelUpWarrior = new LevelUpCommand(warrior, 3);
        LevelUpCommand levelUpMage = new LevelUpCommand(mage, 2);
        
        commandInvoker.executeCommand(levelUpWarrior);
        commandInvoker.executeCommand(levelUpMage);
        
        AttackCommand attack1 = new AttackCommand(warrior, mage);
        AttackCommand attack2 = new AttackCommand(mage, warrior);
        
        commandInvoker.executeCommand(attack1);
        commandInvoker.executeCommand(attack2);
        
        GameSystemCommand saveGame = new GameSystemCommand("save", "game_state_001.sav");
        commandInvoker.executeCommand(saveGame);
        
        commandInvoker.showHistory();
        
        System.out.println("\n📊 Player Status After Commands:");
        System.out.println(warrior);
        System.out.println(mage);
        
        System.out.println("\n↩️ UNDO DEMONSTRATION:");
        commandInvoker.undoLastCommand();
        commandInvoker.undoLastCommand();
        commandInvoker.undoLastCommand();
        
        System.out.println("\n📊 Player Status After Undo:");
        System.out.println(warrior);
        System.out.println(mage);
        
        System.out.println("\n↪️ REDO DEMONSTRATION:");
        commandInvoker.redoLastCommand();
        
        System.out.println("\n📊 Final Player Status:");
        System.out.println(warrior);
        System.out.println(mage);
        
        System.out.println("✅ Command Pattern: Successfully demonstrated encapsulated actions with undo/redo!");
    }
    
    /**
     * 3. STRATEGY PATTERN DEMONSTRATION
     */
    private static void demonstrateStrategyPattern() {
        System.out.println("\n⚔️ 3. STRATEGY PATTERN - Interchangeable Combat Algorithms");
        System.out.println("-".repeat(55));
        
        GamePlayer fighter = new GamePlayer("Legolas", "Archer", 120, 60, 8);
        GamePlayer enemy = new GamePlayer("Orc Warrior", "Warrior", 100, 20, 6);
        
        System.out.println("🥊 COMBAT PARTICIPANTS:");
        System.out.println(fighter);
        System.out.println(enemy);
        
        CombatContext combatContext = new CombatContext(new BalancedCombatStrategy());
        
        System.out.println("\n⚖️ ROUND 1 - BALANCED STRATEGY:");
        CombatResult result1 = combatContext.executeCombat(fighter, enemy);
        
        combatContext.setStrategy(new AggressiveCombatStrategy());
        System.out.println("\n⚔️ ROUND 2 - AGGRESSIVE STRATEGY:");
        CombatResult result2 = combatContext.executeCombat(fighter, enemy);
        
        combatContext.setStrategy(new DefensiveCombatStrategy());
        System.out.println("\n🛡️ ROUND 3 - DEFENSIVE STRATEGY:");
        CombatResult result3 = combatContext.executeCombat(fighter, enemy);
        
        combatContext.setStrategy(new MagicCombatStrategy());
        System.out.println("\n🔮 ROUND 4 - MAGIC STRATEGY:");
        CombatResult result4 = combatContext.executeCombat(fighter, enemy);
        
        System.out.println("\n📊 FINAL STATUS:");
        System.out.println(fighter);
        System.out.println(enemy);
        
        System.out.println("✅ Strategy Pattern: Successfully demonstrated interchangeable combat algorithms!");
    }
    
    /**
     * 4. INTEGRATED BEHAVIORAL DEMONSTRATION
     */
    private static void demonstrateIntegratedBehavior() {
        System.out.println("\n🔗 4. INTEGRATED BEHAVIORAL PATTERNS");
        System.out.println("-".repeat(55));
        
        GameEventPublisher eventPublisher = GameEventPublisher.getInstance();
        GameCommandInvoker commandInvoker = new GameCommandInvoker();
        
        CreatePlayerCommand createHero = new CreatePlayerCommand("Hero", "Paladin", 140, 80);
        CreatePlayerCommand createVillain = new CreatePlayerCommand("Villain", "Dark Knight", 130, 70);
        
        commandInvoker.executeCommand(createHero);
        commandInvoker.executeCommand(createVillain);
        
        GamePlayer hero = createHero.getCreatedPlayer();
        GamePlayer villain = createVillain.getCreatedPlayer();
        
        eventPublisher.notifyPlayerJoined(hero.getName(), hero.getCharacterClass());
        eventPublisher.notifyPlayerJoined(villain.getName(), villain.getCharacterClass());
        
        LevelUpCommand heroLevelUp = new LevelUpCommand(hero, 2);
        commandInvoker.executeCommand(heroLevelUp);
        eventPublisher.notifyPlayerLevelUp(hero.getName(), hero.getLevel());
        
        CombatContext combatContext = new CombatContext(new AggressiveCombatStrategy());
        
        System.out.println("\n⚔️ INTEGRATED COMBAT SEQUENCE:");
        eventPublisher.notifyCombatStarted(hero.getName(), villain.getName());
        
        CombatResult result = combatContext.executeCombat(hero, villain);
        
        eventPublisher.notifyCombatEnded(result.getWinner(), result.getLoser(), result.getDamageDealt());
        
        AttackCommand attackCommand = new AttackCommand(hero, villain);
        commandInvoker.executeCommand(attackCommand);
        
        System.out.println("\n🔄 DEMONSTRATING PATTERN INTEGRATION:");
        System.out.println("• Commands encapsulate actions (Command Pattern)");
        System.out.println("• Observers track all events (Observer Pattern)");
        System.out.println("• Strategies determine combat behavior (Strategy Pattern)");
        System.out.println("• All patterns work together seamlessly!");
        
        eventPublisher.notifyGameEvent("INTEGRATION_DEMO", "All behavioral patterns successfully integrated!");
        
        System.out.println("✅ Integration Complete: All behavioral patterns working in harmony!");
    }
}