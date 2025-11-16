package adapter;

import models.GameCharacter;

/**
 * Target interface for player creation in our system
 */
public interface PlayerCreator {
    GameCharacter createPlayer(String name, String type, int health, int mana);
}