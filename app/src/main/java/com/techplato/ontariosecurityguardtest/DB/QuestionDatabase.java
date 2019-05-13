package com.techplato.ontariosecurityguardtest.DB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {Question.class}, version = 11, exportSchema = false)
public abstract class QuestionDatabase extends RoomDatabase {
    private static QuestionDatabase instance;
    private static RoomDatabase.Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    public static synchronized QuestionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QuestionDatabase.class, "question_bank")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    public abstract QuestionDao questionDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuestionDao questionDao;

        public PopulateDBAsyncTask(QuestionDatabase db) {
            this.questionDao = db.questionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loadData();

            return null;
        }

        private void loadData() {

            questionDao.insert(new Question(1, "What is Your Name?",
                    "Amit", "Rana", "Jhon", "Rick", "Amit",
                    1, 1,1, 0, 4));

            questionDao.insert(new Question(1, "Who Are You?",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 1,1, 0, 4));

            questionDao.insert(new Question(2, "What is Your Age?",
                    "1", "2", "3", "4", "4",
                    1, 1,0, 0, 4));

            questionDao.insert(new Question(3, "is it day or night?",
                    "day", "night", null, null, "day",
                    1, 0,-1, 0, 2));

            questionDao.insert(new Question(1, "What is Your Name?",
                    "Amit", "Rana", "Jhon", "Rick", "Amit",
                    1, 0,-1, 0, 4));

            questionDao.insert(new Question(1, "Who Are You?",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 1, 0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?",
                    "1", "2", "3", "4", "4",
                    2, 1,1, 0, 4));

            questionDao.insert(new Question(3, "is it day or night?",
                    "day", "night", null, null, "day",
                    2, 1,0, 0, 2));

            questionDao.insert(new Question(1, "Who Are You?",
                    "actor", "dancer", "singer", "killer", "actor",
                    2, 1, 0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?",
                    "1", "2", "3", "4", "4",
                    2, 1,0, 0, 4));

            questionDao.insert(new Question(3, "is it day or night?",
                    "day", "night", null, null, "day",
                    1, 1,0, 0, 2));

            questionDao.insert(new Question(1, "What is Your Name?",
                    "Amit", "Rana", "Jhon", "Rick", "Amit",
                    3, 0,-1, 0, 4));

            questionDao.insert(new Question(1, "Who Are You?",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 1, 0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?",
                    "1", "2", "3", "4", "4",
                    2, 0,-1, 0, 4));

            questionDao.insert(new Question(3, "is it day or night?",
                    "day", "night", null, null, "day",
                    3, 1, 0,0, 2));

            questionDao.insert(new Question(1, "Who Are You?",
                    "actor", "dancer", "singer", "killer", "actor",
                    2, 1, 1,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?",
                    "1", "2", "3", "4", "4",
                    2, 1,1, 0, 4));

            questionDao.insert(new Question(3, "is it day or night?",
                    "day", "night", null, null, "day",
                    1, 1,0, 0, 2));

            questionDao.insert(new Question(1, "What is Your Name?",
                    "Amit", "Rana", "Jhon", "Rick", "Amit",
                    3, 1,1, 0, 4));

            questionDao.insert(new Question(1, "Who Are You?",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 1, 0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?",
                    "1", "2", "3", "4", "4",
                    2, 0,-1, 0, 4));

            questionDao.insert(new Question(3, "is it day or night?",
                    "day", "night", null, null, "day",
                    3, 1, 0,0, 2));
        }
    }

}
