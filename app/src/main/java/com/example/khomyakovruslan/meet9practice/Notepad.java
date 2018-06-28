package com.example.khomyakovruslan.meet9practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.khomyakovruslan.meet9practice.NotepadDbSchema.*;

public class Notepad {
    private static Notepad sNotepad;
    
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public Notepad(Context context) {
        mContext = context;
        mDatabase = new NotepadBaseHelper(mContext).getWritableDatabase();
    }

    public static Notepad get(Context context) {
        if (sNotepad == null){
            sNotepad = new Notepad(context);
        }
        return sNotepad;
    }

    public void addNote(Note note){
        ContentValues values = getContentValues(note);
        mDatabase.insert(NotepadTable.NAME, null, values);
    }

    public void updateNote(Note note){
        String uuidString = note.getId().toString();
        ContentValues values = getContentValues(note);
        //seems ok
        mDatabase.update(NotepadTable.NAME, values, NotepadTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public List<Note> getNotes(){

        List<Note> notes = new ArrayList<>();

        NoteCursorWrapper cursor = queryNotes(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return notes;
    }
/*
    public Note getNote(){
        return null;
    }*/

    private static ContentValues getContentValues(Note note){
        ContentValues values = new ContentValues();
        values.put(NotepadTable.Cols.UUID, note.getId().toString());
        values.put(NotepadTable.Cols.NOTE_NAME, note.getName());
        values.put(NotepadTable.Cols.TIME, note.getTime().getTime());
        values.put(NotepadTable.Cols.CONTENT, note.getContent());
        return values;
    }

    private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(NotepadTable.NAME, null, whereClause,whereArgs,null,null,null);
        return new NoteCursorWrapper(cursor);
    }

    public Note getNote(UUID id){
        NoteCursorWrapper cursor = queryNotes(NotepadTable.Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getNote();
        } finally {
            cursor.close();
        }
    }
}
