package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.database.factories.AnimalFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossBattleActivity extends AppCompatActivity {

    private TextView opponentCreatureName, opponentCreatureHealth, playerCreatureName, playerCreatureHealth;
    private ProgressBar opponentHealthBar, playerHealthBar;
    private RecyclerView combatLogRecyclerView;
    private CombatLogAdapter adapter;
    private List<String> combatLogs;
    private Button attackButton, exitBattleButton;
    private ImageView opponentCreatureView, playerCreatureView;
    private Handler handler;
    private Random random;

    // Game Elements
    private Animal player = AnimalFactory.getRandomCreature();
    private Animal opponent = AnimalFactory.createBossAnimal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_boss);

        int userId = getIntent().getIntExtra("USER_ID", -1);
        if (userId == -1) {
            Toast.makeText(this, "No user logged in. Redirecting to login.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        AppRepository repository = AppRepository.getRepository(getApplication());
        repository.getUserById(userId).observe(this, user -> {
            if (user != null) {
                // Use the User object
                String username = user.getUsername();
                int highestArenaScore = user.getHighestArena();
                Toast.makeText(this, "Welcome, " + username, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error fetching user details. Redirecting to login.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        repository = AppRepository.getRepository(getApplication());
        LiveData<User> userObserver = repository.getUserById(userId);
        userObserver.observe(this, user -> {
            int creatureId = user.getCurrentCreatureId();
            player = AnimalFactory.getAnimalById(creatureId);
            updateUI();
        });


        playerCreatureView = findViewById(R.id.playerCreatureView);
        opponentCreatureView = findViewById(R.id.opponentCreatureView);


        if (player.getHp() <= 0 || opponent.getHp() <= 0) {
            Toast.makeText(this, "Invalid battle state. Restarting battle!", Toast.LENGTH_SHORT).show();
            recreate(); // Restart activity
            return;
        }

        opponentCreatureName = findViewById(R.id.opponentCreatureName);
        opponentCreatureHealth = findViewById(R.id.opponentCreatureHealth);
        opponentHealthBar = findViewById(R.id.opponentHealthBar);
        playerCreatureName = findViewById(R.id.playerCreatureName);
        playerCreatureHealth = findViewById(R.id.playerCreatureHealth);
        playerHealthBar = findViewById(R.id.playerHealthBar);
        combatLogRecyclerView = findViewById(R.id.combatLog);
        attackButton = findViewById(R.id.attackButton);
        exitBattleButton = findViewById(R.id.exitBattleButton);

        combatLogs = new ArrayList<>();
        adapter = new CombatLogAdapter(combatLogs);
        combatLogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        combatLogRecyclerView.setAdapter(adapter);

        handler = new Handler();
        random = new Random();

        updateUI();

        exitBattleButton.setOnClickListener(v -> {
            exitBattleButton.setVisibility(View.GONE);
            attackButton.setVisibility(View.GONE);
            exitBattleButton.setEnabled(false);
            endBattle();
        });

        attackButton.setEnabled(true);
        attackButton.setOnClickListener(v -> {
            playerTurn();
            attackButton.setEnabled(false);
        });
    }

    private void playerTurn() {
        if (!player.isAlive() || !opponent.isAlive()) {
            endBattle();
            return;
        }

        int roll = random.nextInt(100);
        String result = player.attack(opponent);
        adapter.addLog(result);

        updateUI();

        if (!opponent.isAlive()) {
            adapter.addLog(opponent.getAnimalName() + " fainted! " + player.getAnimalName() + " wins!");
            endBattle();
        } else {
            handler.postDelayed(this::enemyTurn, 1000);
        }
    }

    private void enemyTurn() {
        attackButton.setEnabled(false);
        if (!player.isAlive() || !opponent.isAlive()) {
            endBattle();
            return;
        }

        int roll = random.nextInt(100) + 1;
        String result = opponent.attack(player);
        adapter.addLog(result);

        updateUI();

        if (!player.isAlive()) {
            adapter.addLog(player.getAnimalName() + " fainted! " + opponent.getAnimalName() + " wins!");
            endBattle();
        } else {
            attackButton.setEnabled(true);
        }
    }

    private void endBattle() {
        adapter.addLog("Battle Over!");
        if (player.isAlive()) {

            //currentUser.setHighestArena(currentUser.getHighestArena() + 1);
            //userDAO.update(currentUser);
        }

        handler.postDelayed(() -> {
            int userId = getIntent().getIntExtra("USER_ID", -1);
            Intent intent = WorldActivity.worldActivityIntentFactory(this, userId);
            startActivity(intent);
            finish();
            Toast.makeText(this, player.isAlive() ? "Congratulations on winning the battle!" : "You lost the battle.", Toast.LENGTH_LONG).show();
        }, 2000);
    }

    private void updateUI() {
        playerCreatureName.setText(player.getAnimalName());
        playerCreatureHealth.setText("HP: " + player.getHp() + "/" + player.getMaxHp());
        playerHealthBar.setMax(player.getMaxHp());
        playerHealthBar.setProgress(player.getHp());
        playerCreatureView.setImageResource(player.getImageResId());

        opponentCreatureName.setText(opponent.getAnimalName());
        opponentCreatureHealth.setText("HP: " + opponent.getHp() + "/" + opponent.getMaxHp());
        opponentHealthBar.setMax(opponent.getMaxHp());
        opponentHealthBar.setProgress(opponent.getHp());
        opponentCreatureView.setImageResource(opponent.getImageResId());

        if (!combatLogs.isEmpty()) {
            combatLogRecyclerView.smoothScrollToPosition(combatLogs.size() - 1);
        }
    }

    static Intent bossBattleIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, BossBattleActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }

}