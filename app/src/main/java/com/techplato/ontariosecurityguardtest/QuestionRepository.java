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



    public List<Question> mGetProgress(int type){
        //return new GetProgressAsyncTask(questionDao).execute(type);
        return null;
    }

    private static class GetProgressAsyncTask extends AsyncTask<Integer, Void, List<Question>> {
        private QuestionDao questionDao;

        public GetProgressAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected List<Question> doInBackground(Integer... integers) {
            return questionDao.mGetProgress(integers[0]);
        }
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
