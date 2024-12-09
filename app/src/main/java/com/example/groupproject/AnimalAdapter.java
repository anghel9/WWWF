package com.example.groupproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position){
        Animal animal = animal.get(position);
        holder.animalNameTextView.setText(animal.getName());
        holder.animalImageView.setImageResource(animal.getImageResId());
        holder.itemView.setOnClickListener(view -> onClickListener.onAnimalClick(animal));
    }

    @Override
    public int getItemCount(){
        return animals.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder{
        TextView animalNameTextView;
        ImageView animalImageView;

        public AnimalViewHolder(View itemView){
            super(itemView);
            animalNameTextView = itemView.findViewById(R.id.animalName);
            animalImageView = itemView.findViewById(R.id.animalImage);
        }
    }
}
