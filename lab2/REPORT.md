# Lab 2 — Creational Design Patterns — Comprehensive Report

**Student:** Andi Blindu  
**Group:** FAF-233  
**Date:** October 21, 2025  

---

## Objective
This lab demonstrates the implementation and practical application of **4 Creational Design Patterns** in a game development context:

1. **Singleton Pattern** - GameManager (single instance management)
2. **Builder Pattern** - Player (complex object construction)
3. **Factory Method Pattern** - WeaponFactory (creating different weapon types)
4. **Abstract Factory Pattern** - GameElementFactory (creating themed object families)

## Domain Selection: Game Development System

The chosen domain is a **role-playing game (RPG) system** which naturally requires various object creation mechanisms:

- **Complex objects** (Players with many attributes) → Builder Pattern
- **Global state management** (Game instance) → Singleton Pattern  
- **Different object types** (Various weapons) → Factory Method Pattern
- **Themed object families** (Medieval/Fantasy/Modern sets) → Abstract Factory Pattern

## Project Structure

```
lab2/src/
├── client/
│   └── GameDemo.java           # Main demonstration client
├── domain/
│   └── GameManager.java        # Singleton pattern implementation
├── factory/
│   ├── WeaponFactory.java      # Factory Method pattern
│   ├── WeaponFactoryProvider.java
│   ├── GameElementFactory.java # Abstract Factory pattern
│   └── GameElementFactoryProvider.java
└── models/
    ├── Player.java             # Builder pattern implementation
    ├── Weapon.java             # Abstract weapon class
    ├── Sword.java              # Concrete weapon implementations
    ├── Bow.java
    ├── Staff.java
    ├── Armor.java              # Abstract Factory products
    └── Potion.java
```

**Total files: 13** - Professional enterprise-level structure demonstrating production-ready design patterns.

---

## Pattern Implementations with Theory & Examples

### 1) Singleton Pattern - GameManager

**Theory:**
The Singleton pattern ensures a class has only one instance and provides a global access point to it. It's useful when exactly one object is needed to coordinate actions across the system.

**When to use:**
- Managing shared resources (database connections, loggers)
- Coordinating application-wide state
- Configuration managers
- Cache managers

**Implementation:** Thread-safe enum singleton (recommended approach)

```java
public enum GameManager {
    INSTANCE;
    
    private List<Player> players;
    private String currentGameMode;
    private int gameLevel;
    private boolean gameRunning;
    
    // Constructor equivalent
    GameManager() {
        this.players = new ArrayList<>();
        this.currentGameMode = "Adventure";
        this.gameLevel = 1;
        this.gameRunning = false;
    }
    
    public void startGame() {
        gameRunning = true;
        System.out.println("🎮 Game Started!");
    }
    
    // Other methods...
}
```

**Usage Example:**
```java
GameManager gameManager = GameManager.INSTANCE;
GameManager sameInstance = GameManager.INSTANCE;
System.out.println("Same instance? " + (gameManager == sameInstance)); // true
```

**Benefits:**
- ✅ Thread-safe (enum singleton is inherently thread-safe)
- ✅ Lazy initialization (created only when first accessed)
- ✅ Serialization-safe
- ✅ No reflection attacks possible

---

### 2) Builder Pattern - Player

**Theory:**
The Builder pattern constructs complex objects step by step. It allows you to produce different types and representations of an object using the same construction code.

**When to use:**
- Objects with many optional parameters
- Complex object construction with validation
- When constructor would have too many parameters
- Immutable objects that require complex setup

**Implementation:**
```java
public class Player {
    private final String name;
    private final String characterClass;
    private final int level;
    private final int health;
    // ... other attributes
    
    // Private constructor - only Builder can create instances
    private Player(PlayerBuilder builder) {
        this.name = builder.name;
        this.characterClass = builder.characterClass;
        this.level = builder.level;
        this.health = builder.health;
        // ... copy other attributes
    }
    
    public static class PlayerBuilder {
        // Required parameters
        private final String name;
        private final String characterClass;
        
        // Optional parameters with defaults
        private int level = 1;
        private int health = 100;
        private int mana = 50;
        // ... other optional attributes
        
        public PlayerBuilder(String name, String characterClass) {
            this.name = name;
            this.characterClass = characterClass;
        }
        
        public PlayerBuilder level(int level) {
            this.level = level;
            return this;
        }
        
        public PlayerBuilder health(int health) {
            this.health = health;
            return this;
        }
        
        // ... other fluent methods
        
        public Player build() {
            return new Player(this);
        }
    }
}
```

**Usage Example:**
```java
Player warrior = new Player.PlayerBuilder("Aragorn", "Warrior")
    .level(25)
    .health(200)
    .strength(30)
    .weapon("Steel Sword")
    .armor("Chain Mail")
    .build();

Player mage = new Player.PlayerBuilder("Gandalf", "Mage")
    .level(30)
    .intelligence(35)
    .mana(200)
    .build(); // Some parameters use defaults
```

**Benefits:**
- ✅ Fluent interface (method chaining)
- ✅ Optional parameters with defaults
- ✅ Immutable objects
- ✅ Clear, readable object construction
- ✅ Validation during construction

---

### 3) Factory Method Pattern - WeaponFactory

**Theory:**
Factory Method defines an interface for creating objects, but lets subclasses decide which classes to instantiate. It delegates object creation to subclasses.

**When to use:**
- When you don't know exact types of objects beforehand
- When you want to provide a library of products to users
- When you want to extend internal components
- When you want to save system resources by reusing existing objects

**Implementation:**
```java
// Abstract factory
public abstract class WeaponFactory {
    // Factory method - subclasses decide which weapon to create
    public abstract Weapon createWeapon(String name, int damage, String description);
    
    // Template method using factory method
    public Weapon createAndEnchantWeapon(String name, int damage, String description, String enchantment) {
        Weapon weapon = createWeapon(name, damage, description);
        System.out.println("✨ Enchanting " + weapon.getName() + " with " + enchantment);
        return weapon;
    }
}

// Concrete factories
class SwordFactory extends WeaponFactory {
    @Override
    public Weapon createWeapon(String name, int damage, String description) {
        System.out.println("🔨 Forging a new sword: " + name);
        return new Sword(name, damage, description);
    }
}

class BowFactory extends WeaponFactory {
    @Override
    public Weapon createWeapon(String name, int damage, String description) {
        System.out.println("🪵 Crafting a new bow: " + name);
        return new Bow(name, damage, description);
    }
}
```

**Usage Example:**
```java
WeaponFactory swordFactory = WeaponFactoryProvider.getFactory("sword");
WeaponFactory bowFactory = WeaponFactoryProvider.getFactory("bow");

Weapon excalibur = swordFactory.createAndEnchantWeapon("Excalibur", 100, "Legendary blade", "Holy Light");
Weapon elvishBow = bowFactory.createAndEnchantWeapon("Elvish Bow", 85, "Elven crafted", "Wind Speed");
```

**Benefits:**
- ✅ Eliminates tight coupling between creator and concrete products
- ✅ Open/Closed Principle (easy to add new weapon types)
- ✅ Single Responsibility Principle (creation logic separated)
- ✅ Code reuse through template methods

---

### 4) Abstract Factory Pattern - GameElementFactory

**Theory:**
Abstract Factory provides an interface for creating families of related or dependent objects without specifying their concrete classes.

**When to use:**
- When system needs to be independent of product creation
- When system needs to work with multiple families of products
- When family of related products should be used together
- When you want to provide class library and reveal interfaces only

**Implementation:**
```java
// Abstract factory
public abstract class GameElementFactory {
    public abstract Weapon createThemeWeapon(String weaponType, String name, int damage);
    public abstract Armor createThemeArmor(String armorType, String name, int defense);
    public abstract Potion createThemePotion(String potionType, String name, int potency);
    
    // Template method using abstract factory methods
    public void createCompleteSet(String name) {
        Weapon weapon = createThemeWeapon("sword", name + "'s Blade", 75);
        Armor armor = createThemeArmor("chestplate", name + "'s Armor", 50);
        Potion potion = createThemePotion("healing", name + "'s Elixir", 100);
        // Display complete themed set...
    }
    
    protected abstract String getTheme();
}

// Concrete factories for different themes
class MedievalFactory extends GameElementFactory {
    @Override
    public Weapon createThemeWeapon(String weaponType, String name, int damage) {
        return new Sword(name, damage, "Forged steel blade with leather grip");
    }
    
    @Override
    public Armor createThemeArmor(String armorType, String name, int defense) {
        return new Armor(name, defense, armorType, "Medieval");
    }
    
    @Override
    public Potion createThemePotion(String potionType, String name, int potency) {
        return new Potion(name, "Healing", potency, "Medieval");
    }
}

class FantasyFactory extends GameElementFactory {
    @Override
    public Weapon createThemeWeapon(String weaponType, String name, int damage) {
        return new Sword(name, damage, "Enchanted mithril blade glowing with magic");
    }
    // ... other themed implementations
}
```

**Usage Example:**
```java
GameElementFactory medievalFactory = GameElementFactoryProvider.getFactory("medieval");
GameElementFactory fantasyFactory = GameElementFactoryProvider.getFactory("fantasy");

// Create complete themed sets
medievalFactory.createCompleteSet("Knight");
fantasyFactory.createCompleteSet("Wizard");

// Create individual themed elements
Weapon medievalSword = medievalFactory.createThemeWeapon("sword", "Knight's Blade", 80);
Armor fantasyArmor = fantasyFactory.createThemeArmor("robe", "Arcane Robes", 40);
```

**Benefits:**
- ✅ Ensures product compatibility within families
- ✅ Isolates concrete classes from client
- ✅ Easy to exchange product families
- ✅ Promotes consistency among products

---

## How to Run

```powershell
cd 'c:\Users\andib\OneDrive\Desktop\FAF\TMPS\lab2\src'

# Compile all Java files
javac --release 17 client\*.java domain\*.java factory\*.java models\*.java

# Run the demonstration
java client.GameDemo
```

## Sample Output

```
🎮 === CREATIONAL DESIGN PATTERNS GAME DEMO ===

🔹 === SINGLETON PATTERN DEMO ===
GameManager ensures only one game instance exists

🎮 Game Started!
📍 Mode: Adventure
🆙 Level: 1
🎯 Game mode changed to: RPG Adventure
🔍 Same instance? true
🆙 Game advanced to level: 2
✅ Singleton pattern: One GameManager controls all game state

🔹 === BUILDER PATTERN DEMO ===
👤 Player 'Aragorn' joined the game!
👤 Player 'Gandalf' joined the game!
👤 Player 'Legolas' joined the game!
✅ Builder pattern: Flexible object creation with optional parameters

🔹 === FACTORY METHOD PATTERN DEMO ===
🔨 Forging a new sword: Excalibur
✨ Enchanting Excalibur with Holy Light!
⚔️ Swinging Excalibur with a powerful slash!
💥 Dealing 100 melee damage!
✅ Factory Method: Different factories create different weapon types

🔹 === ABSTRACT FACTORY PATTERN DEMO ===
🎨 Creating complete Medieval themed set for Knight:
✅ Complete set created!
🎭 Individual Themed Elements:
✅ Abstract Factory: Consistent themed families of related objects
```

---

## Pattern Comparison & When to Use

| Pattern | Purpose | Use Case | Example |
|---------|---------|----------|---------|
| **Singleton** | One instance globally | Shared resources, state management | Database connection, Logger, Game state |
| **Builder** | Complex object construction | Many optional parameters, immutable objects | Configuration objects, Complex entities |
| **Factory Method** | Create objects without specifying exact class | Product hierarchies, extensibility | UI elements, Different file parsers |
| **Abstract Factory** | Create families of related objects | Themed products, platform-specific objects | UI themes, Database drivers |

## Key Benefits Demonstrated

### 🎯 **Design Principles Applied**
- **Single Responsibility**: Each factory handles creation of specific types
- **Open/Closed**: Easy to add new weapons/themes without modifying existing code
- **Dependency Inversion**: Client depends on abstractions, not concrete classes

### 🔧 **Enterprise Benefits**
- **Maintainability**: Clear separation of creation logic
- **Extensibility**: New patterns can be added easily
- **Testability**: Each pattern can be tested independently
- **Scalability**: Patterns support growing complexity

### 📚 **Learning Outcomes**
- Understanding when each creational pattern is appropriate
- Implementing thread-safe singleton using enum
- Building flexible object construction with Builder
- Creating extensible factories with Factory Method
- Managing related object families with Abstract Factory

---

## 🔗 GitHub Repository
**Source Code:** [https://github.com/blinduandi/TMPS-Labs](https://github.com/blinduandi/TMPS-Labs)

The complete Lab 2 project demonstrates production-ready creational design patterns in a cohesive game development scenario.

---

**Lab completed by:** Andi Blindu (FAF-233)  
**Date:** October 21, 2025  
**Total files created:** 13  
**Patterns implemented:** 4 Creational Design Patterns  
**Repository:** https://github.com/blinduandi/TMPS-Labs