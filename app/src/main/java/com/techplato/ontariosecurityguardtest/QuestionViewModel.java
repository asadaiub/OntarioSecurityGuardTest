package com.techplato.ontariosecurityguardtest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private QuestionRepository repository;
    private LiveData<List<Question>> allQuestion;
    private LiveData<List<Question>> easyAnsweredList;


    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repository= new QuestionRepository(application);
        allQuestion=repository.getAllQuestion();
        easyAnsweredList=repository.getEasyAnsweredList();
    }


    public void insert(Question question){
        repository.insert(question);
    }

    public LiveData<List<Question>> getAllQuestion(){
        return allQuestion;
    }
    public LiveData<List<Question>> getEasyAnsweredList(){
        return easyAnsweredList;
    }
}
