package com.notes.iit.simplenotesmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class NotesListActivity extends AppCompatActivity {
    FloatingActionButton noteEditOpenButton;
    ListView listView;
    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        initalizeViews();
        noteEditOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotesListActivity.this, ListEditActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initalizeViews() {
        noteEditOpenButton=(FloatingActionButton)findViewById(R.id.fab);
        listView=(ListView)findViewById(R.id.list);
    }

    // I have shifted the bellow code from onCreate method Becasue we need to execute this code every time.
    // Every time means if we come from Login activity we need this code.
    // And if we come from Add Note activity after saving a new note we need to execute this code also.
    // In this time onActivity dont call. So we need to call this method from the onResume method.

    private void getAllNotesData(){
        sqliteHelper=new SqliteHelper(this);
        Cursor cursor= sqliteHelper.retriveAllNotesCursor();    // Sqlite method change
        CursorAdapter cursorAdapter=new NotesListAdapter(this,cursor);
        listView.setAdapter(cursorAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllNotesData();
    }
}
