package com.techplato.ontariosecurityguardtest.DB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {Question.class}, version = 12, exportSchema = false)
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

            questionDao.insert(new Question(1, "What is Your Name?1",
                    "Amit", "Rana", "Jhon", "Rick", "Amit",
                    1, 0,3, 0,0, 4));

            questionDao.insert(new Question(1, "Who Are You?2",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 0,-1, 0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?3",
                    "1", "2", "3", "4", "4",
                    1, 0,3, 0,0, 4));


            questionDao.insert(new Question(1, "What is Your Name?4",
                    "Amit", "Rana", "Jhon", "Rick", "Amit",
                    1, 0,3, 0,0, 4));

            questionDao.insert(new Question(1, "Who Are You?5",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 0, 3,0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?6",
                    "1", "2", "3", "4", "4",
                    2, 0,3, 0,0, 4));


            questionDao.insert(new Question(1, "Who Are You?7",
                    "actor", "dancer", "singer", "killer", "actor",
                    2, 0, 3,0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?8",
                    "1", "2", "3", "4", "4",
                    2, 0,3, 0,0, 4));


            questionDao.insert(new Question(1, "What is Your Name?9",
                    "Amit", "Rana", "Jhon", "Rick", "Amit",
                    3, 0,3, 0,0, 4));

            questionDao.insert(new Question(1, "Who Are You?10",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 0, 3,0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?11",
                    "1", "2", "3", "4", "4",
                    2, 0,3, 0,0, 4));

            questionDao.insert(new Question(1, "Who Are You?12",
                    "actor", "dancer", "singer", "killer", "actor",
                    2, 0, 3,0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?13",
                    "1", "2", "3", "4", "4",
                    2, 0,3, 0,0, 4));


            questionDao.insert(new Question(1, "What is Your Name?14",
                    "Amit", "Rana", "Jhon", "Rick", "Amit",
                    3, 0,3, 0,0, 4));

            questionDao.insert(new Question(1, "Who Are You?15",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 0, 3,0,0, 4));

            questionDao.insert(new Question(2, "What is Your Age?16",
                    "1", "2", "3", "4", "4",
                    2, 0,3, 0,0, 4));


            questionDao.insert(new Question(3, "is it day or night?17",
                    "day", "night", "NO", "Yes", "day",
                    3, 0, 3,0,0, 4));


            questionDao.insert(new Question(1, "Who Are You?18",
                    "actor", "dancer", "singer", "killer", "actor",
                    1, 0, 3,0,0, 4));

            questionDao.insert(new Question(3, "What is Your Age?19",
                    "1", "2", "3", "4", "4",
                    2, 0,-1, 0,0, 4));


            questionDao.insert(new Question(3, "is it day or night?2",
                    "day", "night", "NO", "Yes", "day",
                    1, 0, 3,0,0, 4));

            questionDao.insert(new Question(3, "is it a day or night?3",
                    "day", "night", "NOo", "yesss", "day",
                    2, 0, 3,0,0, 4));

        }
    }

}
