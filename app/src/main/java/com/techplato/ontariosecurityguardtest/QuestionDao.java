package com.techplato.ontariosecurityguardtest;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question question);

  /*  @Query("Select * from question_bank order by id")
    LiveData<List<Question>> getAllQuestions();*/

    @Query("Select * from question_bank where difficultType=1 and isAnswered=1")
    LiveData<List<Question>> easyProgress();

    @Query("Select * from question_bank where difficultType=2 and isAnswered=1")
    LiveData<List<Question>> mediumProgress();

    @Query("Select * from question_bank where difficultType=3 and isAnswered=1")
    LiveData<List<Question>> hardProgress();

    @Query("Select * from question_bank where difficultType=:type and isAnswered=1")
    List<Question> mGetProgress(int type);

    @Query("Select * from question_bank where difficultType=:type and isAnswered=1 and sectionId=:section")
    List<Question> getTest(int type,int section);

    @Query("Select * from question_bank where specialExam=0")
    List<Question> getExam();







    @Query("Select * from question_bank where difficultType=1 and isAnswered=1")
    LiveData<List<Question>> getAllSection();

    @Query("Select * from question_bank where difficultType=1 and isAnswered=1")
    LiveData<List<Question>> getAllQuestions();








}
