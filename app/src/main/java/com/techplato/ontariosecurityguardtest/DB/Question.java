package com.techplato.ontariosecurityguardtest.DB;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "question_bank")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int difficultType;
    private String singleQuestion;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String answer;
    private int sectionId;
    private int isAnswered;
    private int isRight;
    private int specialExam;
    private int specialExamId;

    public Question(int difficultType, String singleQuestion, String optionOne, String optionTwo,
                    String optionThree, String optionFour, String answer, int sectionId, int isAnswered,
                    int isRight, int specialExam, int specialExamId) {
        this.difficultType = difficultType;
        this.singleQuestion = singleQuestion;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.answer = answer;
        this.sectionId = sectionId;
        this.isAnswered = isAnswered;
        this.isRight = isRight;
        this.specialExam = specialExam;
        this.specialExamId = specialExamId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getDifficultType() {
        return difficultType;
    }

    public String getSingleQuestion() {
        return singleQuestion;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public String getAnswer() {
        return answer;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getIsAnswered() {
        return isAnswered;
    }

    public int getIsRight() {
        return isRight;
    }

    public int getSpecialExam() {
        return specialExam;
    }

    public int getSpecialExamId() {
        return specialExamId;
    }

}
