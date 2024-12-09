package com.example.groupproject;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.database.entities.Animal;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>{

    private final List<Animal> animals;
    private final OnAnimalClickListener onClickListener;

    public interface OnAnimalClickListener {
        void onAnimalClick(Animal animal);
    }

    public AnimalAdapter(List<Animal> animals, OnAnimalClickListener onClickListener){
        this.animals = animals;
        this.onClickListener = onClickListener;
    }


}
