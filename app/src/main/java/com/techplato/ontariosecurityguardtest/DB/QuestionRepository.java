package com.techplato.ontariosecurityguardtest.DB;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.techplato.ontariosecurityguardtest.Model.AnswerSetModel;
import com.techplato.ontariosecurityguardtest.Model.SpecialExamAnswerSetModel;
import com.techplato.ontariosecurityguardtest.Model.SubcategoryModel;

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

    public void setAnswered(int id,int ans){
        AnswerSetModel answerSetModel=new AnswerSetModel(id,ans);
        new SetAnsweredAsyncTask(questionDao).execute(answerSetModel);
    }

    public LiveData<List<SubcategoryModel>> getSubcategoryList(int difType){
       return questionDao.getSubcategoryList(difType);
    }
    public LiveData<List<Question>> getTestQuestion(int type,int sectionId){
        return questionDao.getTestQuestion(type,sectionId);
    }

    LiveData<List<Question>> getMainExamQuestion(){
        return questionDao.getMainExamQuestion();
    }

    void resetMainExam(){
        new ResetMainExamAsyncTask(questionDao).execute();
    }


    private static class ResetMainExamAsyncTask extends AsyncTask<Void,Void,Void> {
        private QuestionDao questionDao;

        public ResetMainExamAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            questionDao.resetMainExam();
            return null;
        }
    }

    public void updateSpecialExam(int id,int sId){
        SpecialExamAnswerSetModel specialExamAnswerSetModel=new SpecialExamAnswerSetModel(id,sId);
        new updateSpecialExamAsyncTask(questionDao).execute(specialExamAnswerSetModel);


    }

    private static class updateSpecialExamAsyncTask extends AsyncTask<SpecialExamAnswerSetModel,Void,Void> {
        private QuestionDao questionDao;

        public updateSpecialExamAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(SpecialExamAnswerSetModel... specialExamAnswerSetModels) {
            questionDao.updateSpecialExam(specialExamAnswerSetModels[0].getId(),specialExamAnswerSetModels[0].getSpecialExamId());
            return null;
        }
    }





    public LiveData<List<Question>> getAllQuestion(){
        return allQuestions;
    }


    private static class SetAnsweredAsyncTask extends AsyncTask<AnswerSetModel,Void,Void>{
        private QuestionDao questionDao;

        public SetAnsweredAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(AnswerSetModel... answerSetModels) {
            questionDao.setAnswered(answerSetModels[0].getID(),answerSetModels[0].getIsRight());
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


    public LiveData<List<Question>> getMainExamScore(int sId){
        return questionDao.getMainExamScore(sId);
    }


}
