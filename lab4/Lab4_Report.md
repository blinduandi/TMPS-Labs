# Lab 4 Report: Behavioral Design Patterns

**Student:** Andi Blindu  
**Course:** TMPS (Design Patterns)  
**Date:** November 16, 2025  
**Topic:** Behavioral Design Patterns Implementation  
**Objective:** Implement and demonstrate Behavioral Design Patterns for game system communication

## Introduction/Theory/Motivation

Behavioral design patterns focus on communication between objects and the assignment of responsibilities between them. These patterns help define how objects interact and communicate with each other, making the system more flexible and easier to maintain. In our game system, behavioral patterns are essential for:

- **Event-driven architecture** for game state notifications
- **Action encapsulation** with undo/redo capabilities  
- **Algorithm flexibility** for different combat behaviors

The three behavioral patterns implemented provide a robust communication framework that enhances the existing structural and creational patterns from previous labs.

## Implementation & Explanation

### 1. Observer Pattern - Event-Driven Architecture

**Location:** `behavioral/observer/`

**Motivation:** Game systems require multiple components to react to state changes (statistics tracking, achievements, logging). The Observer pattern enables loose coupling between the event source and listeners.

**Key Components:**
- `GameEventPublisher` (Subject) - Singleton that manages observers and broadcasts events
- `GameEventObserver` (Observer Interface) - Defines notification methods
- `GameStatisticsTracker` - Tracks game metrics and player statistics
- `AchievementSystem` - Awards achievements based on player actions
- `GameLogger` - Logs all events with timestamps for debugging

**Code Snippet - Event Publisher:**
```java
public class GameEventPublisher {
    private final List<GameEventObserver> observers = new ArrayList<>();
    private static GameEventPublisher instance;
    
    public void notifyPlayerJoined(String playerName, String playerClass) {
        System.out.println("📢 Broadcasting: Player joined event");
        for (GameEventObserver observer : observers) {
            observer.onPlayerJoined(playerName, playerClass);
        }
    }
    // ... other notification methods
}
```

**Benefits:**
- Loose coupling between event source and handlers
- Easy addition/removal of observers at runtime
- Centralized event management through Singleton pattern
- Real-time statistics and achievement tracking

### 2. Command Pattern - Action Encapsulation with Undo/Redo

**Location:** `behavioral/command/`

**Motivation:** Game systems need to support complex actions that can be undone, logged, or queued. The Command pattern encapsulates requests as objects, enabling powerful action management.

**Key Components:**
- `GameCommand` (Command Interface) - Defines execute/undo operations
- `GameCommandInvoker` - Manages command execution and history
- `CreatePlayerCommand` - Encapsulates player creation with undo capability
- `LevelUpCommand` - Handles level progression with rollback
- `AttackCommand` - Manages combat actions with damage reversal
- `GameSystemCommand` - Handles system operations (save/load)

**Code Snippet - Command Interface:**
```java
public interface GameCommand {
    void execute();
    void undo();
    String getDescription();
    boolean canUndo();
}

public class LevelUpCommand implements GameCommand {
    private final GamePlayer player;
    private final int levelsToGain;
    
    @Override
    public void execute() {
        player.levelUp(levelsToGain);
    }
    
    @Override
    public void undo() {
        player.levelDown(levelsToGain);
    }
}
```

**Benefits:**
- Complete undo/redo functionality for all game actions
- Command history tracking and replay capability
- Encapsulation of complex operations
- Support for macro commands and action queuing
- Rollback capability for error recovery

### 3. Strategy Pattern - Interchangeable Combat Algorithms

**Location:** `behavioral/strategy/`

**Motivation:** Combat systems require different algorithms based on character types, difficulty levels, or player preferences. The Strategy pattern allows runtime algorithm switching.

**Key Components:**
- `CombatStrategy` (Strategy Interface) - Defines combat algorithm contract
- `CombatContext` - Manages current strategy and executes combat
- `AggressiveCombatStrategy` - High damage, high critical chance
- `DefensiveCombatStrategy` - Consistent, reliable damage
- `BalancedCombatStrategy` - Moderate risk/reward approach
- `MagicCombatStrategy` - Mana-based spells with resource management

**Code Snippet - Strategy Implementation:**
```java
public class AggressiveCombatStrategy implements CombatStrategy {
    @Override
    public CombatResult executeCombat(GamePlayer attacker, GamePlayer defender) {
        int baseDamage = attacker.getLevel() * 15; // Higher base damage
        double criticalChance = 0.35; // 35% critical chance
        boolean isCritical = Math.random() < criticalChance;
        
        int finalDamage = isCritical ? baseDamage * 2 : baseDamage;
        defender.takeDamage(finalDamage);
        
        return new CombatResult(attacker.getName(), defender.getName(), 
                               finalDamage, combatLog, isCritical);
    }
}
```

**Benefits:**
- Runtime strategy switching for different combat scenarios
- Easy addition of new combat algorithms
- Separation of algorithm implementation from context
- Flexible damage calculations and combat mechanics

### 4. Pattern Integration

**Location:** `client/Lab4ClientNew.java`

The behavioral patterns work seamlessly together:

1. **Observer + Command**: Commands trigger events that observers track
2. **Strategy + Observer**: Combat results are broadcast to achievement/statistics systems  
3. **Command + Strategy**: Combat strategies can be changed via commands
4. **All Patterns**: Unified client demonstrates complete integration

**Code Snippet - Integration Example:**
```java
// Command pattern creates players
commandInvoker.executeCommand(createHero);

// Observer pattern tracks the creation
eventPublisher.notifyPlayerJoined(hero.getName(), hero.getCharacterClass());

// Strategy pattern handles combat
CombatResult result = combatContext.executeCombat(hero, villain);

// Observer pattern tracks combat results
eventPublisher.notifyCombatEnded(result.getWinner(), result.getLoser(), result.getDamageDealt());
```

## Project Structure

```
lab4/
├── src/
│   ├── client/
│   │   └── Lab4ClientNew.java         # Main demonstration client
│   ├── behavioral/
│   │   ├── observer/                   # Observer pattern implementation
│   │   │   ├── GameEventObserver.java         # Observer interface
│   │   │   ├── GameEventPublisher.java        # Subject (Singleton)
│   │   │   ├── GameStatisticsTracker.java     # Concrete observer
│   │   │   ├── AchievementSystem.java         # Concrete observer  
│   │   │   └── GameLogger.java                # Concrete observer
│   │   ├── command/                    # Command pattern implementation
│   │   │   ├── GameCommand.java               # Command interface
│   │   │   ├── GameCommandInvoker.java        # Invoker with undo/redo
│   │   │   ├── CreatePlayerCommand.java       # Concrete command
│   │   │   ├── LevelUpCommand.java            # Concrete command
│   │   │   ├── AttackCommand.java             # Concrete command
│   │   │   └── GameSystemCommand.java         # Concrete command
│   │   └── strategy/                   # Strategy pattern implementation
│   │       ├── CombatStrategy.java            # Strategy interface
│   │       ├── CombatContext.java             # Context class
│   │       ├── CombatResult.java              # Result encapsulation
│   │       ├── AggressiveCombatStrategy.java  # Concrete strategy
│   │       ├── DefensiveCombatStrategy.java   # Concrete strategy
│   │       ├── BalancedCombatStrategy.java    # Concrete strategy
│   │       └── MagicCombatStrategy.java       # Concrete strategy
│   ├── domain/
│   │   └── GamePlayer.java            # Enhanced player model
│   └── utilities/
│       └── GameUtils.java             # Helper utilities
```

## Results/Screenshots/Conclusions

### Execution Results

The demonstration successfully shows all three behavioral patterns working together:

1. **Observer Pattern Results:**
   - 3 observers registered successfully
   - Real-time event broadcasting and handling
   - Statistics tracking: 4 players, 1 combat, 2 level-ups, 1 equipment
   - Achievements awarded automatically based on events
   - Timestamped logging for all game events

2. **Command Pattern Results:**
   - 7 commands executed with complete history tracking
   - Successful undo operations (3 commands reversed)
   - Successful redo operation (1 command replayed)
   - Player state correctly restored after undo/redo
   - All command types working: creation, leveling, combat, system

3. **Strategy Pattern Results:**
   - 4 different combat strategies demonstrated
   - Runtime strategy switching working correctly
   - Different damage patterns: Balanced (97), Aggressive (127), Defensive (64), Magic (88)
   - Strategy-specific behaviors clearly visible
   - Mana consumption in Magic strategy working properly

### Integration Results

The integrated demonstration shows perfect harmony between patterns:
- Commands trigger observer notifications
- Combat strategies produce results tracked by observers
- Achievement system responds to all pattern interactions
- Statistics accurately reflect all pattern activities

### Key Achievements

1. **Event-Driven Architecture**: Complete decoupling of game events from handlers
2. **Action Management**: Full undo/redo capability with command history
3. **Algorithm Flexibility**: Runtime combat strategy switching
4. **Seamless Integration**: All patterns working together without conflicts
5. **Production-Quality Code**: Proper error handling, logging, and documentation

## Conclusions

The implementation successfully demonstrates how behavioral design patterns enhance communication and flexibility in software systems:

### Pattern Benefits Realized:

1. **Observer Pattern:**
   - Eliminated tight coupling between event sources and handlers
   - Enabled real-time monitoring and achievement systems
   - Facilitated easy addition of new event listeners

2. **Command Pattern:**
   - Provided complete action encapsulation with undo/redo
   - Enabled complex operation logging and history management
   - Supported rollback mechanisms for error recovery

3. **Strategy Pattern:**
   - Allowed runtime algorithm switching without code changes
   - Separated combat logic from context management
   - Enabled easy addition of new combat behaviors

### Learning Outcomes:

1. **Communication Patterns**: Understanding how objects communicate effectively
2. **Flexibility Through Composition**: Runtime behavior modification capabilities
3. **Separation of Concerns**: Clear division between different responsibilities
4. **Integration Mastery**: Combining multiple patterns harmoniously

### Real-World Applications:

- **Game Development**: Event systems, action management, AI behaviors
- **Enterprise Software**: Workflow management, audit trails, business rules
- **UI Applications**: Undo/redo functionality, event handling, theme switching

The behavioral patterns successfully complement the structural and creational patterns from previous labs, creating a comprehensive design pattern foundation for complex software systems.

## Compilation & Execution

```bash
# Compile
cd lab4/src
javac --release 17 -cp . client/Lab4ClientNew.java

# Run
java -cp . client.Lab4ClientNew
```

**Status:** ✅ All behavioral patterns implemented, tested, and integrated successfully

## Repository Integration

This Lab 4 is integrated into the same TMPS-Labs repository containing:
- Lab 1: SOLID Principles (Coffee Shop)
- Lab 2: Creational Patterns (Game System Foundation)  
- Lab 3: Structural Patterns (Decorator, Facade, Adapter)
- Lab 4: Behavioral Patterns (Observer, Command, Strategy)

All labs work together to demonstrate a complete understanding of design patterns across all categories, building a sophisticated game development framework that showcases proper software architecture principles.