package com.example.groupproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.database.entities.Animal;

import java.util.Arrays;
import java.util.List;

public class AnimalListFragment extends DialogFragment {

    public interface OnAnimalSelectedListener{
        void onAnimalSelected(String name, int imageResId, String stats);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
        return inflator.inflate(R.layout.fragment_animal_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        /*TODO:COME BACK WHEN WE HAVE SET ANIMALS
        List<Animal> animals = Arrays.asList(

        )
         */

        RecyclerView recyclerView = view.findViewById(R.id.animalRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AnimalAdapter(animals, animal -> {
            if (getActivity() instanceof OnAnimalSelectedListener){
                ((OnAnimalSelectedListener) getActivity()).onAnimalSelected(
                        animal.getName(),
                        animal.getImageResId(),
                        animal.getStats()
                );
            }
            dismiss();
        }));
    }
}
