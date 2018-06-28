package com.example.khomyakovruslan.meet9practice;

import java.util.Date;
import java.util.UUID;

public class Note {
    private UUID mId;
    private String mName;
    private Date mTime;
    private String mContent;

    public Note() {
        this(UUID.randomUUID());
    }

    public Note(UUID id) {
        mId = id;
        mTime = new Date();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public UUID getId() {
        return mId;
    }
}
