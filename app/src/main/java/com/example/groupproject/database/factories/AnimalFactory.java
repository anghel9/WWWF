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
        creatures.add(new Animal("The Wizard", 300, 300, 80, 10, R.drawable.wizard, 2));
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
        switch (id) {
            case 1:
                return new Animal("Deer", 120, 120, 35, 50, R.drawable.deer, 1);
            case 2:
                return new Animal("Cabbage", 300, 300, 80, 25, R.drawable.wizard, 2);
            case 3:
                return new Animal("Bass", 120, 120, 35, 50, R.drawable.bass, 3);
            case 4:
                return new Animal("Salmon", 120, 120, 35, 50, R.drawable.salmon, 4);
            case 5:
                return new Animal("PitBull", 120, 120, 35, 50, R.drawable.pitbull, 5);

                case 6:
                return new Animal("Panda", 120, 120, 35, 50, R.drawable.panda, 6);

                case 7:
                return new Animal("Geico", 120, 120, 35, 50, R.drawable.geico, 7);

                case 8:
                return new Animal("Dragon", 120, 120, 35, 50, R.drawable.dragon, 8);

                case 9:
                return new Animal("Otter", 120, 120, 35, 50, R.drawable.ottery, 9);

            default:
                return new Animal("Deer", 120, 120, 35, 50, R.drawable.deer, 1);
            }
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