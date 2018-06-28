package com.example.khomyakovruslan.meet9practice;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import static com.example.khomyakovruslan.meet9practice.NotepadDbSchema.NotepadTable.*;

public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String noteName = getString(getColumnIndex(Cols.NOTE_NAME));
        long time = getLong(getColumnIndex(Cols.TIME));
        String content = getString(getColumnIndex(Cols.CONTENT));

        Note note = new Note(UUID.fromString(uuidString));
        note.setName(noteName);
        note.setTime(new Date(time));
        note.setContent(content);

        return note;
    }
}
