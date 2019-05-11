package com.techplato.ontariosecurityguardtest.DB;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question question);

    @Query("Select * from question_bank order by id")
    LiveData<List<Question>> getAllQuestions();

    @Query("Select * from question_bank where difficultType=:type and isAnswered=1")
    LiveData<List<Question>> mGetProgress(int type);

    @Query("UPDATE question_bank SET isAnswered = 1 WHERE ID =:id")
    void setAnswered(int id);

    @Query("Select distinct(sectionId) FROM  question_bank where difficultType=:difType")
    LiveData<List<Integer>> getSubcategoryList(int difType);

   /* @Query("Select * from question_bank where difficultType=:difType and isAnswered=1 and sectionId=:secID")
    LiveData<List<Question>> getSubcategoryProgressList(int difType, int secID);*/




   /* @Query("Select * from question_bank where difficultType=:type and isAnswered=1 and sectionId=:section")
    List<Question> getTest(int type,int section);

    @Query("Select * from question_bank where specialExam=0")
    List<Question> getExam();*/




    //Select sectionId, sum(isAnswered) as isAnswered FROM question_bank where difficultType=1 group by sectionId










}
