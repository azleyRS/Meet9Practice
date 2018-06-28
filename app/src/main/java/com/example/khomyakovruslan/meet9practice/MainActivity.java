package com.example.khomyakovruslan.meet9practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    //check
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view_id);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        updateUI();
        /*mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);*/
    }

    private void updateUI() {
        Notepad notepad = Notepad.get(this);
        List<Note> notes = notepad.getNotes();
        if (mAdapter == null){
            mAdapter = new MyAdapter(notes);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setNotes(notes);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_note:

                //mb make this in new activity?
                Note note = new Note();
                Notepad.get(this).addNote(note);
                Intent intent = Activity2ForFragment.newIntent(this);
                intent.putExtra("uuid",note.getId());
                startActivity(intent);
                //remove later this is for test
                //updateUI();

                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateUI();
    }

    /*    //check full
    @Override
    protected void onPause() {
        super.onPause();

        Notepad.get(this).updateNote(mNote);
    }*/
}
