package com.example.gamedemo;

import java.util.Random;

public class NPC {
    private int hitPoints;
    private int strength;
    private int dexterity;
    private int intelligence;

    public NPC() {
        this.hitPoints = new Random().nextInt(6) + 1;
        this.strength = (new Random().nextInt(6) + 1) * 2;
        this.dexterity = (new Random().nextInt(6) + 1) * 2;
        this.intelligence = (new Random().nextInt(6) + 1) * 2;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public void takeDamage(int damage) {
        hitPoints -= damage;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }
}