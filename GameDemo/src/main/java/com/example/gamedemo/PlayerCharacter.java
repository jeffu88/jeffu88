package com.example.gamedemo;

import java.util.Random;

public class PlayerCharacter {
    private int hitPoints = 20;
    private int strength = roll3d6();
    private int dexterity = roll3d6();
    private int intelligence = roll3d6();
    private int totalGold = 0;

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void addGold(int gold) {
        totalGold += gold;
    }

    public void takeDamage(int damage) {
        hitPoints -= damage;
        if (hitPoints <= 0) {
            System.out.println("Game Over! You died.");
            System.exit(0);
        }
    }

    private static int roll3d6() {
        Random rand = new Random();
        return rand.nextInt(6) + rand.nextInt(6) + rand.nextInt(6) + 3;
    }
}
