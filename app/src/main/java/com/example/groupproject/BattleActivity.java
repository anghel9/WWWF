package com.example.groupproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groupproject.database.factories.AnimalFactory;

public class BattleActivity extends AppCompatActivity {


    private TextView opponentCreatureName;
    private TextView opponentCreatureHealth;
    private TextView playerCreatureName;
    private TextView playerCreatureHealth;
    private TextView opponentRemainingCreatures;
    private TextView playerRemainingCreatures;
    private ProgressBar opponentHealthBar;
    private ProgressBar playerHealthBar;
    private ImageView opponentCreatureView;
    private ImageView playerCreatureView;
    private Button swapCreatureButton;
    private Button attackButton;
    private RecyclerView combatLog;
    private Handler handler;
    private Random random;
    private Animal player;
    private Animal opponent;
    private RecyclerView combatLogRecyclerView;
    private CombatLogAdapter adapter;
    private List<String> combatLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        combatLog = findViewById(R.id.combatLog);
        combatLogs = new ArrayList<>();
        adapter = new CombatLogAdapter(combatLogs);
        combatLog.setLayoutManager(new LinearLayoutManager(this));
        combatLog.setAdapter(adapter);
        handler = new Handler();
        random = new Random();

        // Initialize creatures
        //TODO: replace with player creature party
        player = AnimalFactory.getRandomCreature(); //Currently gives the player a random creature
        opponent = AnimalFactory.getRandomCreature();

        adapter.addLog("Battle Start: " + player.getAnimalName() + " vs " + opponent.getAnimalName()+"\n");

        // Start the battle
        startBattle();
    }

    private void startBattle() {
        handler.postDelayed(this::playerTurn, 1000); // Player starts first
    }

    private void playerTurn() {
        if (!player.isAlive() || !opponent.isAlive()) {
            endBattle();
            return;
        }

        int roll = random.nextInt(100) + 1;
        String result = player.attack(opponent, roll);
        adapter.addLog(result + "\n");

        if (!opponent.isAlive()) {
            adapter.addLog(opponent.getAnimalName() + " fainted! " + player.getAnimalName() + " wins!\n");
            endBattle();
        } else {
            handler.postDelayed(this::enemyTurn, 1000); // Enemy attacks after a delay
        }
    }

    private void enemyTurn() {
        if (!player.isAlive() || !opponent.isAlive()) {
            endBattle();
            return;
        }

        int roll = random.nextInt(100) + 1;
        String result = opponent.attack(player, roll);
        adapter.addLog(result + "\n");

        if (!player.isAlive()) {
            adapter.addLog(player.getAnimalName() + " fainted! " + opponent.getAnimalName() + " wins!\n");
            endBattle();
        } else {
            handler.postDelayed(this::playerTurn, 1000); // Player's next turn after a delay
        }
    }

    private void endBattle() {
        adapter.addLog("Battle Over!\n");
        // Optionally enable a "Back" button or restart option
    }
}