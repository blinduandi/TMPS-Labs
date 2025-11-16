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
public class Lab4Client {
    
    public static void main(String[] args) {
        System.out.println("🧠 LAB 4: BEHAVIORAL DESIGN PATTERNS DEMONSTRATION");
        System.out.println("=" .repeat(65));
        
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
     * Shows event-driven architecture with multiple observers
     */
    private static void setupObserverPattern() {
        System.out.println("\n👁️ 1. OBSERVER PATTERN - Event-Driven Architecture");
        System.out.println("-".repeat(55));
        
        // Get the singleton event publisher
        GameEventPublisher eventPublisher = GameEventPublisher.getInstance();
        
        // Create and register different types of observers
        GameStatisticsTracker statsTracker = new GameStatisticsTracker();
        AchievementSystem achievementSystem = new AchievementSystem();
        GameLogger gameLogger = new GameLogger();
        
        // Register all observers
        eventPublisher.subscribe(statsTracker);
        eventPublisher.subscribe(achievementSystem);
        eventPublisher.subscribe(gameLogger);
        
        System.out.println("📊 Registered " + eventPublisher.getObserverCount() + " observers");
        
        // Trigger some events to show observer pattern in action
        eventPublisher.notifyGameEvent("GAME_START", "Lab 4 Behavioral Patterns Demo");
        eventPublisher.notifyPlayerJoined("Aragorn", "Warrior");
        eventPublisher.notifyPlayerJoined("Gandalf", "Mage");
        eventPublisher.notifyPlayerLevelUp("Aragorn", 5);
        eventPublisher.notifyItemEquipped("Gandalf", "Staff of Fire");
        
        // Show statistics
        statsTracker.displayStatistics();
        
        System.out.println("✅ Observer Pattern: Successfully demonstrated event-driven architecture!");
    }
    
    /**
     * 2. COMMAND PATTERN DEMONSTRATION
     * Shows encapsulated actions with undo/redo functionality
     */
    private static void demonstrateCommandPattern() {
        System.out.println("\n🔧 2. COMMAND PATTERN - Encapsulated Actions & Undo/Redo");
        System.out.println("-".repeat(55));
        
        // Create command invoker
        GameCommandInvoker commandInvoker = new GameCommandInvoker();
        
        // Create some players using commands
        CreatePlayerCommand createWarrior = new CreatePlayerCommand("Boromir", "Warrior", 150, 30);
        CreatePlayerCommand createMage = new CreatePlayerCommand("Saruman", "Mage", 80, 120);
        
        // Execute creation commands
        commandInvoker.executeCommand(createWarrior);
        commandInvoker.executeCommand(createMage);
        
        // Get created players for further commands
        GamePlayer warrior = createWarrior.getCreatedPlayer();
        GamePlayer mage = createMage.getCreatedPlayer();
        
        System.out.println("\n📊 Player Status After Creation:");
        System.out.println(warrior);
        System.out.println(mage);
        
        // Level up commands
        LevelUpCommand levelUpWarrior = new LevelUpCommand(warrior, 3);
        LevelUpCommand levelUpMage = new LevelUpCommand(mage, 2);
        
        commandInvoker.executeCommand(levelUpWarrior);
        commandInvoker.executeCommand(levelUpMage);
        
        // Combat commands
        AttackCommand attack1 = new AttackCommand(warrior, mage);
        AttackCommand attack2 = new AttackCommand(mage, warrior);
        
        commandInvoker.executeCommand(attack1);
        commandInvoker.executeCommand(attack2);
        
        // System commands
        GameSystemCommand saveGame = new GameSystemCommand("save", "game_state_001.sav");
        commandInvoker.executeCommand(saveGame);\n        
        // Show command history
        commandInvoker.showHistory();
        
        System.out.println("\n📊 Player Status After Commands:");
        System.out.println(warrior);
        System.out.println(mage);
        
        // Demonstrate undo functionality
        System.out.println("\n↩️ UNDO DEMONSTRATION:");\n        commandInvoker.undoLastCommand(); // Undo save
        commandInvoker.undoLastCommand(); // Undo attack2
        commandInvoker.undoLastCommand(); // Undo attack1
        
        System.out.println("\n📊 Player Status After Undo:");
        System.out.println(warrior);
        System.out.println(mage);
        
        // Demonstrate redo functionality
        System.out.println("\n↪️ REDO DEMONSTRATION:");
        commandInvoker.redoLastCommand(); // Redo attack1
        
        System.out.println("\n📊 Final Player Status:");
        System.out.println(warrior);
        System.out.println(mage);
        
        System.out.println("✅ Command Pattern: Successfully demonstrated encapsulated actions with undo/redo!");\n    }\n    \n    /**\n     * 3. STRATEGY PATTERN DEMONSTRATION\n     * Shows interchangeable algorithms for combat\n     */\n    private static void demonstrateStrategyPattern() {
        System.out.println("\n⚔️ 3. STRATEGY PATTERN - Interchangeable Combat Algorithms");
        System.out.println("-".repeat(55));\n        
        // Create fresh players for strategy demonstration
        GamePlayer fighter = new GamePlayer("Legolas", "Archer", 120, 60, 8);
        GamePlayer enemy = new GamePlayer("Orc Warrior", "Warrior", 100, 20, 6);
        
        System.out.println("🥊 COMBAT PARTICIPANTS:");
        System.out.println(fighter);
        System.out.println(enemy);\n        
        // Create combat context with different strategies
        CombatContext combatContext = new CombatContext(new BalancedCombatStrategy());
        
        System.out.println("\n⚖️ ROUND 1 - BALANCED STRATEGY:");
        CombatResult result1 = combatContext.executeCombat(fighter, enemy);\n        \n        // Switch to aggressive strategy\n        combatContext.setStrategy(new AggressiveCombatStrategy());\n        System.out.println(\"\\n⚔️ ROUND 2 - AGGRESSIVE STRATEGY:\");\n        CombatResult result2 = combatContext.executeCombat(fighter, enemy);\n        \n        // Switch to defensive strategy\n        combatContext.setStrategy(new DefensiveCombatStrategy());\n        System.out.println(\"\\n🛡️ ROUND 3 - DEFENSIVE STRATEGY:\");\n        CombatResult result3 = combatContext.executeCombat(fighter, enemy);\n        \n        // Switch to magic strategy\n        combatContext.setStrategy(new MagicCombatStrategy());\n        System.out.println(\"\\n🔮 ROUND 4 - MAGIC STRATEGY:\");\n        CombatResult result4 = combatContext.executeCombat(fighter, enemy);\n        \n        // Show final status\n        System.out.println(\"\\n📊 FINAL STATUS:\");\n        System.out.println(fighter);\n        System.out.println(enemy);\n        \n        System.out.println(\"✅ Strategy Pattern: Successfully demonstrated interchangeable combat algorithms!\");\n    }\n    \n    /**\n     * 4. INTEGRATED BEHAVIORAL DEMONSTRATION\n     * Shows all patterns working together in harmony\n     */\n    private static void demonstrateIntegratedBehavior() {
        System.out.println("\n🔗 4. INTEGRATED BEHAVIORAL PATTERNS");
        System.out.println("-".repeat(55));\n        \n        GameEventPublisher eventPublisher = GameEventPublisher.getInstance();\n        GameCommandInvoker commandInvoker = new GameCommandInvoker();\n        \n        // Create players using Command pattern\n        CreatePlayerCommand createHero = new CreatePlayerCommand(\"Hero\", \"Paladin\", 140, 80);\n        CreatePlayerCommand createVillain = new CreatePlayerCommand(\"Villain\", \"Dark Knight\", 130, 70);\n        \n        commandInvoker.executeCommand(createHero);\n        commandInvoker.executeCommand(createVillain);\n        \n        GamePlayer hero = createHero.getCreatedPlayer();\n        GamePlayer villain = createVillain.getCreatedPlayer();\n        \n        // Observer pattern automatically logs these events\n        eventPublisher.notifyPlayerJoined(hero.getName(), hero.getCharacterClass());\n        eventPublisher.notifyPlayerJoined(villain.getName(), villain.getCharacterClass());\n        \n        // Level up using commands (observed by achievement system)\n        LevelUpCommand heroLevelUp = new LevelUpCommand(hero, 2);\n        commandInvoker.executeCommand(heroLevelUp);\n        eventPublisher.notifyPlayerLevelUp(hero.getName(), hero.getLevel());\n        \n        // Combat using different strategies (observed and commanded)\n        CombatContext combatContext = new CombatContext(new AggressiveCombatStrategy());\n        \n        System.out.println(\"\\n⚔️ INTEGRATED COMBAT SEQUENCE:\");\n        eventPublisher.notifyCombatStarted(hero.getName(), villain.getName());\n        \n        // Execute combat with strategy pattern\n        CombatResult result = combatContext.executeCombat(hero, villain);\n        \n        // Notify observers about combat result\n        eventPublisher.notifyCombatEnded(result.getWinner(), result.getLoser(), result.getDamageDealt());\n        \n        // Create attack command for undo capability\n        AttackCommand attackCommand = new AttackCommand(hero, villain);\n        commandInvoker.executeCommand(attackCommand);\n        \n        System.out.println(\"\\n🔄 DEMONSTRATING PATTERN INTEGRATION:\");\n        System.out.println(\"• Commands encapsulate actions (Command Pattern)\");\n        System.out.println(\"• Observers track all events (Observer Pattern)\");\n        System.out.println(\"• Strategies determine combat behavior (Strategy Pattern)\");\n        System.out.println(\"• All patterns work together seamlessly!\");\n        \n        // Final event\n        eventPublisher.notifyGameEvent(\"INTEGRATION_DEMO\", \"All behavioral patterns successfully integrated!\");\n        \n        System.out.println(\"✅ Integration Complete: All behavioral patterns working in harmony!\");\n    }\n}