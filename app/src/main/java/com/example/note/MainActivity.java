package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NOTES="NotePrefs";
    private static final String KEY_NOTE_COUNT="NoteCount";

    private LinearLayout noteContainer;
    private List<Note>noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteContainer =findViewById(R.id.notesContainer);
        Button saveButton=findViewById(R.id.saveButton);

        noteList=new ArrayList<>();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }
    private void saveNote() {
        EditText titleEditText=findViewById(R.id.titleEditText);
        EditText contentEditText=findViewById(R.id.contentEdit);

        String title=titleEditText.getText().toString();
        String content=contentEditText.getText().toString();

        if (!title.isEmpty()&& !content.isEmpty()){
            Note note=new Note();
            note.setTitle();
            note.setContent();

            noteList.add(note);
            saveNoteToPreference();
            createNoteView();
        }
    }

    private void createNoteView() {
        View noteView=getLayoutInflater().inflate(R.layout.note_item.xml);
    }

    private void saveNoteToPreference(){
        SharedPreferences sharedPreferences=getSharedPreferences(PREFS_NOTES,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt(KEY_NOTE_COUNT,noteList.size());
        for(int i=0;i<noteList.size();i++){
            Note note=noteList.get(i);
            editor.putString("note_content_"+i,note.getContent());
            editor.putString("note_title_"+i,note.getTitle());
        }
        editor.apply();
    }
}