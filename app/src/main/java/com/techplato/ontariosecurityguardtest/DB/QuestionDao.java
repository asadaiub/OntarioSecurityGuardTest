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

    @Query("UPDATE question_bank SET isAnswered = 1 , isRight=:ans WHERE ID =:id")
    void setAnswered(int id, int ans);

    @Query("UPDATE question_bank SET specialExam=1,specialExamId=:sId WHERE id=:id")
    void updateSpecialExam(int id, int sId);

    @Query("UPDATE question_bank SET isAnswered=0, isRight=3")
    void resetTable();

    /*
    @Query("Select distinct(sectionId) FROM  question_bank where difficultType=:difType")
    LiveData<List<Integer>> getSubcategoryList(int difType);*/


    @Query("Select sectionId, COUNT(answer) AS answer, SUM(CASE isAnswered WHEN 1 THEN 1 ELSE 0 END)" +
            " as isAnswered, SUM(CASE isRight WHEN 1 THEN 1 ELSE 0 END) as isRight " +
            "FROM question_bank where difficultType=:difType  group by sectionId")
    LiveData<List<SubcategoryModel>> getSubcategoryList(int difType);


    @Query("Select * from question_bank where difficultType=:type and sectionId=:sectionId ORDER BY isRight Desc")
    LiveData<List<Question>> getTestQuestion(int type, int sectionId);


    @Query("SELECT * FROM question_bank where specialExam=0 and specialExamId=0 ORDER BY RANDOM() LIMIT 5")
    LiveData<List<Question>> getMainExamQuestion();


    @Query("select * from question_bank WHERE specialExamId=:sId")
    LiveData<List<Question>> getMainExamScore(int sId);


    @Query("UPDATE question_bank SET specialExam=0,specialExamId=0 WHERE specialExam=1")
    void resetMainExam();












   /* @Query("Select * from question_bank where difficultType=:type and isAnswered=1 and sectionId=:section")
    List<Question> getTest(int type,int section);

    @Query("Select * from question_bank where specialExam=0")
    List<Question> getExam();*/


    //Select sectionId, sum(isAnswered) as isAnswered FROM question_bank where difficultType=1 group by sectionId


}
