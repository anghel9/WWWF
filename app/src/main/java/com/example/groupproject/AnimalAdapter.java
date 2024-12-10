package com.example.groupproject;

import static com.example.groupproject.database.factories.AnimalFactory.getCreatures;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.groupproject.Animal;
import com.example.groupproject.R;
import com.example.groupproject.database.factories.AnimalFactory;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>{

    private List<Animal> animals = AnimalFactory.getCreatures();
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
        Animal animal = animals.get(position);
        holder.bind(animal, onClickListener);
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

        public void bind(Animal animal, OnAnimalClickListener listener) {
            animalNameTextView.setText(animal.getAnimalName());
            animalImageView.setImageResource(animal.getImageResId());
            itemView.setOnClickListener(v -> listener.onAnimalClick(animal));
        }
    }
}