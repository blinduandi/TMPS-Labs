package models;

/**
 * Simple component interface for Decorator Pattern
 */
public interface WeaponInterface {
    String getDescription();
    int getDamage();
    void use();
}