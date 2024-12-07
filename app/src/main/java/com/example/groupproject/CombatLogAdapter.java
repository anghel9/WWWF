package com.example.groupproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CombatLogAdapter extends RecyclerView.Adapter<CombatLogAdapter.ViewHolder> {

    private List<String> combatLogs;

    // Constructor
    public CombatLogAdapter(List<String> combatLogs) {
        this.combatLogs = combatLogs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String log = combatLogs.get(position);
        holder.logText.setText(log);
    }

    @Override
    public int getItemCount() {
        return combatLogs.size();
    }

    // ViewHolder Class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView logText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            logText = itemView.findViewById(android.R.id.text1);
        }
    }

    // Method to add a new log and notify the RecyclerView
    public void addLog(String log) {
        combatLogs.add(log);
        notifyItemInserted(combatLogs.size() - 1); // Notify the RecyclerView
    }
}