package com.example.dailynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;

    MainRVAdapter(LayoutInflater layoutInflater, List<String> data) {
        this.layoutInflater = layoutInflater;
        this.data = data;
    }

    @NonNull
    @Override
    public MainRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRVAdapter.ViewHolder holder, int position) {
        String note = data.get(position);
        ViewHolder.noteNote.setText(note);

        String date = data.get(position);
        ViewHolder.noteDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        static TextView noteDate, noteNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteNote = itemView.findViewById(R.id.noteNote);
            noteDate = itemView.findViewById(R.id.noteDate);
        }
    }
}
