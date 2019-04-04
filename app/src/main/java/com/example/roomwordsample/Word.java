package com.example.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

// Annotate class Word to be useful to Roomdata base

@Entity(tableName = "word_table")
// if we did not provide table name, class name will be used as default
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word") // specify the name of the column
    private String mWord;

    // Constructor
    public Word(@NonNull String word){
        this.mWord = word;
    }

    // Getter method
    public String getWord(){
        return this.mWord;
    }
}
