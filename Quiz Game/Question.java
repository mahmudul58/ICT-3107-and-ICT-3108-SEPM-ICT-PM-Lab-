package com.example.quiz_game;
public class Question {
    private String question;
    private String a, b, c, d;
    private String correct;

    public Question(String question, String a, String b, String c, String d, String correct) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;
    }

    public String getQuestion() { return question; }
    public String getA() { return a; }
    public String getB() { return b; }
    public String getC() { return c; }
    public String getD() { return d; }
    public String getCorrect() { return correct; }
}
