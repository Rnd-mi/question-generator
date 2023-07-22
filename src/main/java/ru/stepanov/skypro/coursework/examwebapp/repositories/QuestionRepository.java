package ru.stepanov.skypro.coursework.examwebapp.repositories;

import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
}
