package com.example.roomwordsample;

// A Repository is a class that abstracts access to multiple data sources. It provides
// clean API to the rest of the app for app data by managing query threads while allowing
// your to use multiple backend e.g. data from network or cashed in the local database.

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {

    // Add member variables for the DAO and the list of words
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // Add a constructor that gets a handle to the database and
    // initializes the member variables.
    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();

    }

    // Add a wrapper method called getAllWords() that returns the cashed words
    // as Live data. Room executes all quarries on a separate thread.
    // Observed LiveData notifies the observer when the data changes.
    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    // Add a wrapper for the insert() method. Use and Async Task to call insert()
    // on a non-UI thread, or your app will crash.
    public void insert(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    // Create the insertAsyncTask as an inner class.
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;
        insertAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
