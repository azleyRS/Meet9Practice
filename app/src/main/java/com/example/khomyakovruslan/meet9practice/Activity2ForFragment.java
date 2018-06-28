package com.example.khomyakovruslan.meet9practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

public class Activity2ForFragment extends AppCompatActivity {
/*    Note mNote;
    Bundle bundle;
    FragmentManager fragmentManager;
    Fragment2 fragment2;*/



    private Note mNote;
    private EditText mName;
    private EditText mContent;
    private TextView mTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        UUID noteId = (UUID)getIntent().getSerializableExtra("uuid");
        mNote = Notepad.get(this).getNote(noteId);

        mName = findViewById(R.id.activity2_name);
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNote.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mName.setText(mNote.getName());
        mContent = findViewById(R.id.activity2_content);
        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNote.setContent(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mContent.setText(mNote.getContent());
        mTime = findViewById(R.id.activity2_time);
        mTime.setText(mNote.getTime().toString());
        //setContentView(R.layout.activity_for_fragment);

           /* UUID mId = (UUID) getIntent().getSerializableExtra("uuid");

            mNote = Notepad.get(this).getNote(mId);
            String name = mNote.getName();
            String content = mNote.getContent();
            String time = mNote.getTime().toString();

            fragmentManager = getSupportFragmentManager();
            Fragment1 fragment1 = new Fragment1();
            bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("content", content);
            bundle.putString("time", time);
            fragment1.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment1).commit();
*/
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, Activity2ForFragment.class);
        return intent;
    }

    @Override
    protected void onPause() {
        super.onPause();

        Notepad.get(this).updateNote(mNote);
    }

    /* public void replaceWithSecondFragment(){
        fragment2 = new Fragment2();
        fragment2.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment2).commit();
    }*/

    /*@Override
    protected void onPause() {
        super.onPause();
        if (fragment2!=null){
            mNote.setName(String.valueOf(fragment2.mName.getText()));
            mNote.setContent(String.valueOf(fragment2.mContent.getText()));
        }
        Notepad.get(this).updateNote(mNote);
    }*/

}
