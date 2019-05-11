package com.techplato.ontariosecurityguardtest.DB;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private QuestionRepository repository;
    private LiveData<List<Question>> allQuestion;


    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repository= new QuestionRepository(application);
        allQuestion=repository.getAllQuestion();
    }


    public void insert(Question question){
        repository.insert(question);
    }

    public LiveData<List<Question>> getAllQuestion(){
        return allQuestion;
    }
    public void setAnswered(int id){
       repository.setAnswered(id);
    }


    public LiveData<List<Question>> getMProgress(int type){
        return repository.getMProgress(type);
    }

    public LiveData<List<Integer>> getSubcategoryList(int difType){
        return repository.getSubcategoryList(difType);
    }

   /* public LiveData<List<Question>> getSubcategoryProgressList(int difType, int secID){
        return repository.getSubcategoryProgressList(difType,secID);
    }*/
}
