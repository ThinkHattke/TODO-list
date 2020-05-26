package com.gaurav.mycaptaintest.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Note {

    private String note_id;

    private String content;

    public Note(String note_id, String content) {
        this.note_id = note_id;
        this.content = content;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
