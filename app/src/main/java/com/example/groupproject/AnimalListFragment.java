package com.example.groupproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AnimalListFragment extends DialogFragment {

    public interface OnAnimalSelectedListener{
        void onAnimalSelected(String name, int imageResId, String stats);
    }


}
