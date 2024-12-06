package com.example.groupproject;

public abstract class Animal {
    String animalType = "";
    private int hp = 100;
    private  int attackPwr = 25;
    private double accuracy = 0.35;
    private double atkSpeed = 1.0;

    Animal(){}

    Animal(String animalType, double accuracy, double atkSpeed){
        this.animalType = animalType;
        this.accuracy = accuracy;
        this.atkSpeed = atkSpeed;
    }

    public boolean attack(Animal target, int roll) {
        return false;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
}
