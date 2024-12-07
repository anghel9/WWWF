package com.example.groupproject;

public class Animal {
    private String animalName;
    private int hp = 100;
    private int attackPwr = 25;
    private double accuracy = 0.35; // Represents the chance to hit (35%)
    private double atkSpeed = 1.0; // How fast the animal attacks;
    private int animalArena = 1; //The arena the animal is found in

    // Default Constructor
    public Animal() {}

    // Parameterized Constructor
    public Animal(String animalName, int hp, int attackPwr, double accuracy, double atkSpeed, int animalArena) {
        this.animalName = animalName;
        this.hp = hp;
        this.attackPwr = attackPwr;
        this.accuracy = accuracy;
        this.atkSpeed = atkSpeed;
        this.animalArena = animalArena;
    }

    // Attack Method
    // Modify the attack method to return a result string
    public String attack(Animal target, int roll) {
        if (roll <= this.getAccuracy() * 100) {
            target.setHp(target.getHp() - this.getAttackPwr());
            return this.getAnimalName() + " hit " + target.getAnimalName() + " for " + this.getAttackPwr() + " damage!";
        }
        return this.getAnimalName() + " missed the attack!";
    }

    // Getters and Setters
    public String getAnimalName() {
        return animalName;
    }
    public int getAnimalArena() {
        return animalArena;
    }
    public void setAnimalArena(int animalArena) {
        this.animalArena = animalArena;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0); // Prevent negative HP
    }

    public int getAttackPwr() {
        return attackPwr;
    }

    public void setAttackPwr(int attackPwr) {
        this.attackPwr = attackPwr;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getAtkSpeed() {
        return atkSpeed;
    }

    public void setAtkSpeed(double atkSpeed) {
        this.atkSpeed = atkSpeed;
    }

    public boolean isAlive() {
        return this.hp > 0; // Check if the animal is still alive
    }
}