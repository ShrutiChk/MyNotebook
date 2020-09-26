package com.example.lenovo.mynotebook;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.mynotebook.model.Note;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditNoteActivity extends AppCompatActivity {

    Intent data;
    EditText editNoteTitle,editNoteContent;
    DatabaseReference databaseReference;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        data = getIntent();
        editNoteContent = findViewById(R.id.editNoteContent);
        editNoteTitle = findViewById(R.id.editNoteTitle);


        String noteTitle = data.getStringExtra("title");
        String noteContent = data.getStringExtra("content");
        key = data.getStringExtra("key");

        editNoteTitle.setText(noteTitle);
        editNoteContent.setText(noteContent);

        FloatingActionButton fab = findViewById(R.id.saveEditedNote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nTitle = editNoteTitle.getText().toString();
                String nContent = editNoteContent.getText().toString();

                if(nTitle.isEmpty() || nContent.isEmpty()){
                    Toast.makeText(EditNoteActivity.this, "Can not Save note with Empty Field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Note note = new Note(nTitle,nContent);

               // String key = databaseReference.getKey();
                databaseReference.child(key).setValue(note);
                final MediaPlayer mp = MediaPlayer.create(EditNoteActivity.this,R.raw.add_sound);
                mp.start();
              /*  Intent i = new Intent(EditNoteActivity.this,MainActivity.class);
                startActivity(i);*/
                Toast.makeText(EditNoteActivity.this,"Note Updated",Toast.LENGTH_SHORT).show();
                finish();


            }
        });


    }
}
