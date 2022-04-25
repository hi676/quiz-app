package com.example.quiz_app;

public class QuestionModel {

    private String question, opt1, opt2, opt3, opt4;
    private int correctAnsNum;

    /**
     * Constructor - special method that initializes instances of the Question Model
     * @param question
     * @param opt1
     * @param opt2
     * @param opt3
     * @param opt4
     * @param correctAnsNum
     */
    public QuestionModel(String question, String opt1, String opt2, String opt3, String opt4, int correctAnsNum) {
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.correctAnsNum = correctAnsNum;
    }

    /**
     * Getters allow us to access private variables from other classes
     * @return
     */
    public String getQuestion() {
        return question;
    }

    public String getOpt1() {
        return opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    // button indexes go 0, 1, 2, 3... so we must subtract one here
    public int getCorrectAnsNum() {
        return correctAnsNum - 1;
    }
}