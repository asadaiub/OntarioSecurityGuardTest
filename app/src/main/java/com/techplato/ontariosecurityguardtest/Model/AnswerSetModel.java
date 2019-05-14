package com.techplato.ontariosecurityguardtest.Model;

public class AnswerSetModel {
    int ID;
    int isRight;

    public AnswerSetModel(int ID, int isRight) {
        this.ID = ID;
        this.isRight = isRight;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIsRight() {
        return isRight;
    }

    public void setIsRight(int isRight) {
        this.isRight = isRight;
    }
}
