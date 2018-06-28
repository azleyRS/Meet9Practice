package com.example.khomyakovruslan.meet9practice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    //testing!!! remove later
    //List<String> mData = new ArrayList<>();
    //this one is for bd
    private List<Note> mNotes;

    public MyAdapter(List<Note> notes) {
        mNotes = notes;
    }

    public MyAdapter() {
 /*       mData.add("1");
        mData.add("2");
        mData.add("3");*/
    // also testing
/*        mNotes = new ArrayList<>();
        mNotes.add(new Note());
        mNotes.add(new Note());
        mNotes.add(new Note());*/
    }
    //test
    public void setNotes(List<Note> notes){
        mNotes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_rv,viewGroup,false);
        //UUID id = mNotes.get(i).getId();
        return new MyViewHolder(view/*,id*/);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Note note = mNotes.get(i);
        myViewHolder.bind(note);
        myViewHolder.mName.setText(mNotes.get(i).getName());
        myViewHolder.mContent.setText(mNotes.get(i).getContent());
        myViewHolder.mTime.setText(mNotes.get(i).getTime().toString());
    }

    @Override
    public int getItemCount() {

        //remove this later
        //return mData.size();
        //this is that you need
        return mNotes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Note mHolderNote;
        TextView mName;
        TextView mTime;
        TextView mContent;

        public MyViewHolder(@NonNull final View itemView/*, final UUID id*/) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = Activity2ForFragment.newIntent(itemView.getContext());
                    //replace to newIntent later
                    intent.putExtra("uuid",mHolderNote.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
            mName = itemView.findViewById(R.id.name);
            mTime = itemView.findViewById(R.id.time);
            mContent = itemView.findViewById(R.id.content);
        }

        public void bind(Note note) {
            mHolderNote = note;
        }
    }
}
