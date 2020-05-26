package com.gaurav.mycaptaintest;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gaurav.mycaptaintest.DB.Note;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    public List<Note> notes;
    Context context;
    FirebaseFirestore db;

    NoteAdapter(List<Note> notes, Context context, FirebaseFirestore db) {
        this.notes = notes;
        this.context = context;
        this.db = db;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_note, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.note.setText(notes.get(position).getContent());

        holder.note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_update_note_dialog);

                final EditText newNote = dialog.findViewById(R.id.write_note);
                Button update = dialog.findViewById(R.id.update);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(!newNote.getText().toString().isEmpty()) {

                            Map<String, Object> updatedNote = new HashMap<>();
                            updatedNote.put("note", newNote.getText().toString());

                            db.collection("notes").document(notes.get(position).getNote_id()).update(updatedNote).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    notes.get(position).setContent(newNote.getText().toString());
                                    notifyDataSetChanged();
                                }
                            });

                        }

                    }
                });

                dialog.show();

            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView note;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.note);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
