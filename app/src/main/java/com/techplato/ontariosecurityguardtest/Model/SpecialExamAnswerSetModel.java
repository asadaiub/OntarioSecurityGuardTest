package com.techplato.ontariosecurityguardtest.Model;

public class SpecialExamAnswerSetModel {
    private int id;
    private int specialExamId;

    public SpecialExamAnswerSetModel(int id, int specialExamId) {
        this.id = id;
        this.specialExamId = specialExamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpecialExamId() {
        return specialExamId;
    }

    public void setSpecialExamId(int specialExamId) {
        this.specialExamId = specialExamId;
    }
}
