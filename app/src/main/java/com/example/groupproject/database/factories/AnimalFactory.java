package com.example.groupproject.database.factories;

import com.example.groupproject.Animal;
import com.example.groupproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalFactory {

    // Method to create hard-coded creatures
    public static List<Animal> getCreatures() {
        List<Animal> creatures = new ArrayList<>();

        creatures.add(new Animal("Deer", 120, 120, 35, 50, R.drawable.deer));
        creatures.add(new Animal("The Wizard", 300, 300, 80, 25, R.drawable.wizard));
        creatures.add(new Animal("Bass", 120, 120, 35, 50, R.drawable.bass));
        creatures.add(new Animal("Salmon", 120, 120, 35, 50, R.drawable.salmon));
        creatures.add(new Animal("PitBull", 120, 120, 35, 50, R.drawable.pitbull));

        return creatures;
    }

    // Method to randomly select a creature
    public static Animal getRandomCreature() {
        List<Animal> creatures = getCreatures();
        Random random = new Random();
        return creatures.get(random.nextInt(creatures.size()));
    }
}