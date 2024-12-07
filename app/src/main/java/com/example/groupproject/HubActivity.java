package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.groupproject.databinding.ActivityHubBinding;

public class HubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActivityHubBinding binding;


        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_hub);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        binding = ActivityHubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), 0));
            }
        });
//        binding.editPartyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(StatsActivity);
//            }
//        });
//        binding.worldSelectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(WorldActivity);
//            }
//        });
    }

    static Intent hubActivityIntentFactory(Context context){
        return new Intent(context, HubActivity.class);
    }

}