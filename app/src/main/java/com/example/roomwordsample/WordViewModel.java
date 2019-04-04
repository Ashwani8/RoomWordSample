package com.example.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

// A viewModel is a class whose role is to provide data to the UI and survive
// configuration changes. it separate the app's data from Activity and fragment.
public class WordViewModel extends AndroidViewModel {

    // add a private member variable to hold a reference to the Repository
    private WordRepository mRepository;

    // add a private LiveData member variable to cache the list of words.
    private LiveData<List<Word>> mAllWords;

    // add constructor that gets a reference to the WordRepository and gets
    // the list of all words from the WordRepository
    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    // add a getter method that gets all the words. This completely hides
    // the implementation from the UI.
    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    // Create a wrapper insert() method that calls the Repository's insert() method.
    // in this way, the implementation of insert() is completely hidden from the UI.

    public void insert(Word word){
        mRepository.insert(word);
    }
}
