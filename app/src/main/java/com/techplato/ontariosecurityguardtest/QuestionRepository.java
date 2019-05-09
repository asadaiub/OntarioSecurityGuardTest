package com.techplato.ontariosecurityguardtest;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class QuestionRepository {
    private QuestionDao questionDao;
    private LiveData<List<Question>> allQuestions;
    private LiveData<List<Question>> easyAnsweredList;


    public QuestionRepository(Application application) {
        QuestionDatabase questionDatabase= QuestionDatabase.getInstance(application);
        questionDao=questionDatabase.questionDao();
        allQuestions=questionDao.getAllQuestions();
        easyAnsweredList=questionDao.easyProgress();

    }

    public void insert(Question question){
        new InsertQuestionAsyncTask(questionDao).execute(question);
    }

    public LiveData<List<Question>> getAllQuestion(){
        return allQuestions;
    }

    public LiveData<List<Question>> getEasyAnsweredList(){
        return easyAnsweredList;
    }






    private static class InsertQuestionAsyncTask extends AsyncTask<Question,Void,Void>{
        private QuestionDao questionDao;

        public InsertQuestionAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.insert(questions[0]);
            return null;
        }
    }
}
