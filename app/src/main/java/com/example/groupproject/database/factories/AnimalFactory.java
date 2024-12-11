package com.example.groupproject.database.factories;

import com.example.groupproject.Animal;
import com.example.groupproject.R;
import com.example.groupproject.database.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalFactory {

    // Method to create hard-coded creatures
    public static List<Animal> getCreatures() {
        List<Animal> creatures = new ArrayList<>();

        creatures.add(new Animal("Deer", 200, 200, 40, 80, R.drawable.deer, 1));
        creatures.add(new Animal("Cabbage", 300, 300, 80, 10, R.drawable.wizard, 2));
        creatures.add(new Animal("Bass", 250, 250, 35, 65, R.drawable.bass, 3));
        creatures.add(new Animal("Salmon",120, 120, 70, 80, R.drawable.salmon, 4));
        creatures.add(new Animal("PitBull", 200, 200, 55, 80, R.drawable.pitbull, 5));
        creatures.add(new Animal("Panda", 500, 500, 15, 45, R.drawable.panda, 6));
        creatures.add(new Animal("Geico", 70, 70, 70, 85, R.drawable.geico, 7));
        creatures.add(new Animal("Dragon", 300, 300, 100, 10, R.drawable.dragon, 8));
        creatures.add(new Animal("Otter", 120, 120, 35, 90, R.drawable.ottery, 9));
        return creatures;
    }


    public static Animal getAnimalById(int id) {
        if(id >= getCreatures().size()){
            return getCreatures().get(1);
        }
        return getCreatures().get(id);
    }

    // Method to randomly select a creature
    public static Animal getRandomCreature() {
        List<Animal> creatures = getCreatures();
        Random random = new Random();
        return creatures.get(random.nextInt(creatures.size()));
    }

    public static Animal createBossAnimal() {
        return new Animal("Boss", 300, 300, 80, 15, R.drawable.boss, getCreatures().size() + 1);
    }

}