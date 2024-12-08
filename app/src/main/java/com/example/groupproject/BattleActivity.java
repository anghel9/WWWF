package com.example.groupproject;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groupproject.database.factories.AnimalFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleActivity extends AppCompatActivity {

    // UI Elements
    private TextView opponentCreatureName, opponentCreatureHealth, playerCreatureName, playerCreatureHealth;
    private ProgressBar opponentHealthBar, playerHealthBar;
    private RecyclerView combatLogRecyclerView;
    private CombatLogAdapter adapter;
    private List<String> combatLogs;
    private Button attackButton, swapCreatureButton;
    private ImageView opponentCreatureView, playerCreatureView;
    private Handler handler;
    private Random random;

    // Game Elements
    private Animal player;
    private Animal opponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        // Initialize UI elements
        opponentCreatureName = findViewById(R.id.opponentCreatureName);
        opponentCreatureHealth = findViewById(R.id.opponentCreatureHealth);
        opponentHealthBar = findViewById(R.id.opponentHealthBar);
        playerCreatureName = findViewById(R.id.playerCreatureName);
        playerCreatureHealth = findViewById(R.id.playerCreatureHealth);
        playerHealthBar = findViewById(R.id.playerHealthBar);
        combatLogRecyclerView = findViewById(R.id.combatLog);
        attackButton = findViewById(R.id.attackButton); // Add attack button reference

        // Combat Log Setup
        combatLogs = new ArrayList<>();
        adapter = new CombatLogAdapter(combatLogs);
        combatLogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        combatLogRecyclerView.setAdapter(adapter);

        handler = new Handler();
        random = new Random();

        // Initialize Creatures
        player = AnimalFactory.getRandomCreature();
        opponent = AnimalFactory.getRandomCreature();

        // Set Initial UI
        updateUI();

        // Add Starting Log
        adapter.addLog("Battle Start: " + player.getAnimalName() + " vs " + opponent.getAnimalName());

        // Disable attack button initially
        attackButton.setEnabled(true);

        // Set up click listener for the attack button
        attackButton.setOnClickListener(v -> {
            playerTurn();
            attackButton.setEnabled(false); // Disable button temporarily to prevent spamming
        });
    }

    // Player's turn logic triggered by button click
    private void playerTurn() {
        if (!player.isAlive() || !opponent.isAlive()) {
            endBattle();
            return;
        }

        int roll = random.nextInt(100) + 1;
        String result = player.attack(opponent, roll);
        adapter.addLog(result);

        // Update UI after Player's Turn
        updateUI();

        if (!opponent.isAlive()) {
            adapter.addLog(opponent.getAnimalName() + " fainted! " + player.getAnimalName() + " wins!");
            endBattle();
        } else {
            // Delay opponent's turn to simulate real-time action
            handler.postDelayed(() -> {
                enemyTurn();
                attackButton.setEnabled(true); // Re-enable attack button for the player's next turn
            }, 1000);
        }
    }

    private void enemyTurn() {
        if (!player.isAlive() || !opponent.isAlive()) {
            endBattle();
            return;
        }

        int roll = random.nextInt(100) + 1;
        String result = opponent.attack(player, roll);
        adapter.addLog(result);

        // Update UI after Opponent's Turn
        updateUI();

        if (!player.isAlive()) {
            adapter.addLog(player.getAnimalName() + " fainted! " + opponent.getAnimalName() + " wins!");
            endBattle();
        }
    }

    private void startBattle() {
        handler.postDelayed(this::playerTurn, 1000);
    }

    private void endBattle() {
        adapter.addLog("Battle Over!");
        // Optional: Display buttons or other end-of-battle UI elements
    }

    private void updateUI() {
        // Update player info
        playerCreatureName.setText(player.getAnimalName());
        playerCreatureHealth.setText("HP: " + player.getHp() + "/" + player.getMaxHp());
        playerHealthBar.setMax(player.getMaxHp());
        playerHealthBar.setProgress(player.getHp());
        playerCreatureView.setImageResource(player.getImageResId()); // Set player image

        // Update opponent info
        opponentCreatureName.setText(opponent.getAnimalName());
        opponentCreatureHealth.setText("HP: " + opponent.getHp() + "/" + opponent.getMaxHp());
        opponentHealthBar.setMax(opponent.getMaxHp());
        opponentHealthBar.setProgress(opponent.getHp());
        opponentCreatureView.setImageResource(opponent.getImageResId()); // Set opponent image

        // Scroll to the latest combat log
        combatLogRecyclerView.smoothScrollToPosition(combatLogs.size() - 1);
    }
}