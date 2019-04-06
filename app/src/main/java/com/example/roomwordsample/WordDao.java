package com.example.roomwordsample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

// 1 annotate the class deceleration with @Dao
@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // 3 annotate the insert method
        // and ignore in case of conflict
    void insert(Word word); // 2declare a method to insert one word

// declare the method to delete all and annotate (there is no method to delete all)
    @Query("DELETE FROM word_table")
    void deleteAll();

// Create a method that returns a List of Words and Annotate with SQL query that gets all
// the words from the word_table, sort alphabetically
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords(); // wrapped with live data
    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.


}
