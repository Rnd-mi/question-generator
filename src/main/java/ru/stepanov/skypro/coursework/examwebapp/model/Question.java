package ru.stepanov.skypro.coursework.examwebapp.model;

import java.util.Objects;

public class Question {
    private final String q;
    private final String a;

    public Question(String question, String answer) {
        this.q = question;
        this.a = answer;
    }

    public String getQuestion() {
        return q;
    }

    public String getAnswer() {
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(q, question.q);
    }

    @Override
    public int hashCode() {
        return Objects.hash(q);
    }

    @Override
    public String toString() {
        return "Question: " + q + ", answer: " + a;
    }
}
