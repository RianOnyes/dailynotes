package com.example.dailynotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private static List<String> notes;
    private static List<String> dates;
    private static List<String> ids;
    private static Context context;

    MainRVAdapter(LayoutInflater layoutInflater, List<String> data1, List<String> data2, List<String> data3) {
        this.layoutInflater = layoutInflater;
        this.notes = data1;
        this.dates = data2;
        this.ids = data3;
        this.context = layoutInflater.getContext();
    }

    @NonNull
    @Override
    public MainRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRVAdapter.ViewHolder holder, int position) {
        String note = notes.get(position);
        ViewHolder.noteNote.setText(note);

        String date = dates.get(position);
        ViewHolder.noteDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        static TextView noteDate, noteNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteNote = itemView.findViewById(R.id.noteNote);
            noteDate = itemView.findViewById(R.id.noteDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NoteViewActivity.class);
                    intent.putExtra("note", notes.get(getAdapterPosition()));
                    intent.putExtra("date", dates.get(getAdapterPosition()));
                    intent.putExtra("id", ids.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}