package com.techplato.ontariosecurityguardtest.Model;

public class SubcategoryModel {
   int sectionId;
   int answer;
   int isAnswered;
   int isRight;

    public SubcategoryModel(int sectionId, int answer, int isAnswered, int isRight) {
        this.sectionId = sectionId;
        this.answer = answer;
        this.isAnswered = isAnswered;
        this.isRight = isRight;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(int isAnswered) {
        this.isAnswered = isAnswered;
    }

    public int getIsRight() {
        return isRight;
    }

    public void setIsRight(int isRight) {
        this.isRight = isRight;
    }
}
