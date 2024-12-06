package com.example.groupproject;

public class Animal {
    private String animalName = "";
    private int hp = 100;
    private  int attackPwr = 25;
    private double accuracy = 0.35;
    private double atkSpeed = 1.0;


    Animal(){}

    Animal(String animalName, double accuracy, double atkSpeed){
        this.animalName = animalName;
        this.accuracy = accuracy;
        this.atkSpeed = atkSpeed;
    }

    public boolean attack(Animal target, int roll) {
        return false;
    }

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
