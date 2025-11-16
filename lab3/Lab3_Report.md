# Lab 3 Report: Structural Design Patterns

**Student:** Andi Blindu  
**Course:** TMPS (Design Patterns)  
**Date:** November 16, 2025  
**Objective:** Implement and demonstrate Structural Design Patterns

## Overview

This lab demonstrates three key structural design patterns that complement the creational patterns from Lab 2:

1. **Decorator Pattern** - Dynamic object enhancement
2. **Facade Pattern** - Simplified interface to complex subsystems  
3. **Adapter Pattern** - Integration with incompatible interfaces

## Implementation

### 1. Decorator Pattern

**Purpose:** Dynamically enhance weapons without modifying original classes.

**Structure:**
```
WeaponInterface (Component)
├── BasicWeapon (Concrete Component)
└── WeaponDecorator (Base Decorator)
    ├── FireEnhancement (+15 damage)
    ├── PoisonEnhancement (+10 damage)
    └── CriticalEnhancement (+20 damage)
```

**Key Benefits:**
- Runtime composition over inheritance
- Multiple enhancements can be stacked
- Open/Closed Principle compliance
- No modification of existing weapon classes

**Example:**
```java
WeaponInterface sword = new BasicWeapon("Iron Sword", 50);
sword = new FireEnhancement(sword);        // +15 damage
sword = new PoisonEnhancement(sword);      // +10 damage  
sword = new CriticalEnhancement(sword);    // +20 damage
// Final: "Iron Sword + Fire + Poison + Critical" with 95 damage
```

### 2. Facade Pattern

**Purpose:** Hide complexity of creational patterns (Lab 2) behind simple interface.

**Simplified Operations:**
- `startNewGame(name)` - Hides Singleton pattern
- `createPlayer(name, class)` - Hides Builder pattern complexity
- `enhanceWeapon(weapon, type)` - Hides Factory + Decorator patterns
- `simulateCombat(p1, p2)` - Hides complex battle system

**Benefits:**
- Reduces client coupling to subsystems
- Provides easier API for common operations
- Maintains backward compatibility
- Encapsulates complex pattern interactions

**Example:**
```java
SimpleGameFacade facade = new SimpleGameFacade();
facade.startNewGame("Epic Adventure");  // Simple call, complex implementation
GameCharacter hero = facade.createPlayer("Aragorn", "warrior");
```

### 3. Adapter Pattern

**Purpose:** Enable integration with incompatible external systems.

**Two Adapters Implemented:**

#### Legacy Player System Adapter
- **Problem:** Legacy system uses "type;health;mana" format
- **Solution:** Convert to modern GameCharacter objects
- **Benefit:** Reuse existing player database without modification

#### Third-Party Audio System Adapter  
- **Problem:** Complex audio API vs simple game audio interface
- **Solution:** Adapt volume scales (0-100 vs 0.0-1.0) and method signatures
- **Benefit:** Use powerful audio library with simple interface

**Example:**
```java
// Legacy system integration
LegacyPlayerAdapter adapter = new LegacyPlayerAdapter(legacySystem);
GameCharacter[] players = adapter.importAllLegacyPlayers();

// Audio system integration  
GameAudioManager audio = new AudioSystemAdapter(thirdPartyLib);
audio.setVolume(75);  // Automatically converts to 0.75f
```

## Design Principles Demonstrated

### SOLID Principles Applied:
- **Single Responsibility:** Each decorator has one enhancement responsibility
- **Open/Closed:** New decorators can be added without modifying existing code
- **Liskov Substitution:** All decorators are interchangeable with base weapon
- **Interface Segregation:** Small, focused interfaces (WeaponInterface, GameAudioManager)
- **Dependency Inversion:** High-level facade doesn't depend on low-level implementations

### Pattern Integration:
- **Structural + Creational:** Facade hides creational pattern complexity
- **Decorator + Factory:** Factory can create pre-decorated weapons
- **Adapter + Singleton:** Adapters work with singleton game session

## Project Structure
```
lab3/
├── src/
│   ├── Lab3Client.java           # Main demonstration
│   ├── models/
│   │   ├── WeaponInterface.java   # Component interface
│   │   ├── BasicWeapon.java       # Concrete component
│   │   └── GameCharacter.java     # Player model
│   ├── decorator/
│   │   ├── WeaponDecorator.java   # Base decorator
│   │   ├── FireEnhancement.java   # Fire decorator
│   │   ├── PoisonEnhancement.java # Poison decorator
│   │   └── CriticalEnhancement.java # Critical decorator
│   ├── facade/
│   │   └── SimpleGameFacade.java  # Facade implementation
│   ├── adapter/
│   │   ├── LegacyPlayerSystem.java    # External legacy system
│   │   ├── LegacyPlayerAdapter.java   # Legacy adapter
│   │   ├── ThirdPartyAudioLib.java    # External audio system
│   │   ├── GameAudioManager.java      # Target interface
│   │   └── AudioSystemAdapter.java    # Audio adapter
│   └── domain/
│       └── GameSession.java       # Singleton session manager
```

## Execution Results

The demonstration successfully shows:

1. **Decorator Pattern:** Weapon damage increased from 50 → 95 through layered enhancements
2. **Facade Pattern:** Complex game operations simplified to single method calls
3. **Adapter Pattern:** Legacy player data and third-party audio successfully integrated

## Lessons Learned

### Pattern Benefits:
1. **Modularity:** Each pattern solves specific structural problems
2. **Flexibility:** Runtime composition and configuration
3. **Maintainability:** Changes localized to specific decorators/adapters
4. **Integration:** Patterns work together seamlessly

### Best Practices Applied:
1. **Composition over Inheritance:** Decorators use composition
2. **Interface Segregation:** Small, focused interfaces
3. **Encapsulation:** Facade hides internal complexity
4. **Loose Coupling:** Adapters enable system integration without tight coupling

## Conclusion

The structural patterns successfully demonstrate how to organize and enhance object relationships. The Decorator pattern provides flexible enhancement, the Facade pattern simplifies complex operations, and the Adapter pattern enables seamless integration with external systems.

These patterns complement the creational patterns from Lab 2, creating a robust and extensible game architecture that follows SOLID principles and design pattern best practices.

## Compilation & Execution

```bash
# Compile
cd lab3/src
javac --release 17 -cp . Lab3Client.java

# Run
java -cp . Lab3Client
```

**Status:** ✅ All patterns implemented and tested successfully