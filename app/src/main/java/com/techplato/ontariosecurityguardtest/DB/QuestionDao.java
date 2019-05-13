package com.techplato.ontariosecurityguardtest.DB;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.techplato.ontariosecurityguardtest.Model.SubcategoryModel;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question question);

    @Query("Select * from question_bank order by id")
    LiveData<List<Question>> getAllQuestions();

    @Query("Select * from question_bank where difficultType=:type and isRight=1")
    LiveData<List<Question>> mGetProgress(int type);

    @Query("UPDATE question_bank SET isAnswered = 1 WHERE ID =:id")
    void setAnswered(int id);
/*
    @Query("Select distinct(sectionId) FROM  question_bank where difficultType=:difType")
    LiveData<List<Integer>> getSubcategoryList(int difType);*/


    @Query("Select sectionId, COUNT(answer) AS answer, SUM(CASE isAnswered WHEN 1 THEN 1 ELSE 0 END)" +
            "  as isAnswered, SUM(CASE isRight WHEN 1 THEN 1 ELSE 0 END) as isRight " +
            "FROM question_bank where difficultType=:difType  group by sectionId")
    LiveData<List<SubcategoryModel>> getSubcategoryList(int difType);

    @Query("Select * from question_bank where difficultType=:type and sectionId=:sectionId")
    LiveData<List<Question>> getTestQuestion(int type,int sectionId);






   /* @Query("Select * from question_bank where difficultType=:type and isAnswered=1 and sectionId=:section")
    List<Question> getTest(int type,int section);

    @Query("Select * from question_bank where specialExam=0")
    List<Question> getExam();*/




    //Select sectionId, sum(isAnswered) as isAnswered FROM question_bank where difficultType=1 group by sectionId










}
