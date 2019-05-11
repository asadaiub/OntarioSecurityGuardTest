package com.techplato.ontariosecurityguardtest.DB;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class QuestionRepository {
    private QuestionDao questionDao;
    private LiveData<List<Question>> allQuestions;


    public QuestionRepository(Application application) {
        QuestionDatabase questionDatabase= QuestionDatabase.getInstance(application);
        questionDao=questionDatabase.questionDao();
        allQuestions=questionDao.getAllQuestions();


    }

    public void insert(Question question){
        new InsertQuestionAsyncTask(questionDao).execute(question);
    }

    public LiveData<List<Question>> getMProgress(int type){
        return questionDao.mGetProgress(type);
    }

    public void setAnswered(int id){
        new SetAnsweredAsyncTask(questionDao).execute(id);
    }

    LiveData<List<Integer>> getSubcategoryList(int difType){
       return questionDao.getSubcategoryList(difType);
    }

    /*LiveData<List<Question>> getSubcategoryProgressList(int difType, int secID){
        return questionDao.getSubcategoryProgressList(difType,secID);
    }
*/




    public LiveData<List<Question>> getAllQuestion(){
        return allQuestions;
    }


    private static class SetAnsweredAsyncTask extends AsyncTask<Integer,Void,Void>{
        private QuestionDao questionDao;

        public SetAnsweredAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            questionDao.setAnswered(integers[0]);
            return null;
        }

        /* public InsertQuestionAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.insert(questions[0]);
            return null;
        }*/
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
