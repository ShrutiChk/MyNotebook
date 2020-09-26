package com.example.lenovo.mynotebook;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.mynotebook.model.Note;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NewNoteActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText noteContent,noteTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        noteContent = findViewById(R.id.addNoteContent);
        noteTitle = findViewById(R.id.addNoteTitle);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nTitle = noteTitle.getText().toString();
                String nContent = noteContent.getText().toString();
                if (nTitle.equals(null) || nContent.equals(null))
                {
                    Toast.makeText(NewNoteActivity.this,"Can't save empty note",Toast.LENGTH_SHORT).show();
                }

                Note note = new Note(nTitle,nContent);

                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(note);
                final MediaPlayer mp = MediaPlayer.create(NewNoteActivity.this,R.raw.add_sound);
                mp.start();
                Toast.makeText(NewNoteActivity.this,"Note Saved",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }



}
