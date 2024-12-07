package com.example.groupproject.database.factories;

import com.example.groupproject.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalFactory {

    // Method to create hard-coded creatures
    public static List<Animal> getCreatures() {
        List<Animal> creatures = new ArrayList<>();

        creatures.add(new Animal("Lion", 120, 30, 0.4, 1.2, 2));
        creatures.add(new Animal("Snake", 80, 15, 0.6, 1.5, 2));
        creatures.add(new Animal("Eagle", 100, 20, 0.5, 1.3, 2));
        creatures.add(new Animal("Tiger", 150, 40, 0.3, 1.1, 2));
        creatures.add(new Animal("Elephant", 200, 50, 0.2, 1.0, 2));
        creatures.add(new Animal("Crocodile", 180, 35, 0.7, 1.4, 1));
        creatures.add(new Animal("Zebra", 110, 25, 0.5, 1.2, 2));
        creatures.add(new Animal("Giraffe", 160, 45, 0.4, 1.3, 2));
        creatures.add(new Animal("Hippo", 130, 30, 0.6, 1.1, 2));
        creatures.add(new Animal("Panda", 170, 40, 0.3, 1.0, 1));
        creatures.add(new Animal("Penguin", 90, 20, 0.7, 1.2, 3));
        creatures.add(new Animal("Kangaroo", 140, 35, 0.5, 1.3, 2));
        creatures.add(new Animal("Koala", 120, 25, 0.6, 1.1, 1));
        creatures.add(new Animal("Rhino", 190, 50, 0.4, 1.0, 2));
        creatures.add(new Animal("Shark", 220, 60, 0.3, 1.2, 3));
        creatures.add(new Animal("Turtle", 70, 10, 0.8, 1.5, 3));
        creatures.add(new Animal("Walrus", 180, 40, 0.2, 1.3, 3));
        creatures.add(new Animal("Horse", 110, 25, 0.5, 1.2, 4));
        creatures.add(new Animal("Deer", 160, 45, 0.4, 1.3, 1));
        creatures.add(new Animal("Alligator", 130, 30, 0.6, 1.1, 1));
        creatures.add(new Animal("Red-Panda", 170, 40, 0.3, 1.0, 4));
        creatures.add(new Animal("Puffin", 90, 20, 0.7, 1.2, 1));
        creatures.add(new Animal("Human", 140, 35, 0.5, 1.3, 4));
        creatures.add(new Animal("Poacher", 300, 25, 0.2, 1.0, 2));

        return creatures;
    }

    // Method to randomly select a creature
    public static Animal getRandomCreature() {
        List<Animal> creatures = getCreatures();
        Random random = new Random();
        return creatures.get(random.nextInt(creatures.size()));
    }
}