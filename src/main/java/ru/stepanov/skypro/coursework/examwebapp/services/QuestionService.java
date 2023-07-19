package ru.stepanov.skypro.coursework.examwebapp.services;

import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String q, String a);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
