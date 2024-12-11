package com.example.groupproject;

import java.io.Serializable;
import java.util.Random;

public class Animal implements Serializable {
    private String animalName = "";
    private int hp = 100;
    private int maxHp = 100;
    private int attackPwr = 25;
    private double accuracy = 0.35;
    private int imageResId; // Drawable resource ID for the image
    private int animalID = 0;

    // Updated constructor to match the fields
    public Animal(String animalName, int hp, int maxHp, int attackPwr, double accuracy, int imageResId, int animalID) {
        this.animalName = animalName;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attackPwr = attackPwr;
        this.accuracy = accuracy;
        this.imageResId = imageResId;
        this.animalID = animalID;
    }

    // Getter and Setter for Drawable Resource ID
    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    // Getter for Animal ID Number
    public int getAnimalID() {
        return animalID;
    }

    // Attack Method
    public String attack(Animal target) {
        Random random = new Random();
        if (random.nextInt(100) <= this.getAccuracy()) {
            target.setHp(target.getHp() - this.getAttackPwr());
            return this.getAnimalName() + " hit " + target.getAnimalName() + " for " + this.getAttackPwr() + " damage!";
        }
        return this.getAnimalName() + " missed the attack!";
    }

    // Getters and Setters
    public String getAnimalName() {
        return animalName;
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

    public boolean isAlive() {
        return this.hp > 0; // Check if the animal is still alive
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public String getStats() {
        return ("HP: " + hp + "/" + maxHp + " Attack Power: " + attackPwr + " Accuracy: " + (accuracy) + "%");
    }

}