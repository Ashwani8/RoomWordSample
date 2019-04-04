package com.example.roomwordsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

// Room is a database layer on top of an SQLite database. it must be abstract and extended RoomDatabse

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao(); // provide getter method for each Dao

    // Create a WordRoomDatabase as a singleton to prevent having multiple instances
    // of the data base opened at the same time
    private static WordRoomDatabase INSTANCE;
    public static WordRoomDatabase getDatabase(final Context context){

        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    // Create database here using Room's database builder to create
                    // a Room database object named "word_database" in the application context
                    // from the WordRoomDatabase class
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
